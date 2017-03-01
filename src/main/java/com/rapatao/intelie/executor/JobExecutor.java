package com.rapatao.intelie.executor;

import com.rapatao.intelie.job.Job;
import io.vertx.core.Vertx;
import io.vertx.rx.java.RxHelper;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by luizrapatao on 01/03/17.
 */
@Service
public class JobExecutor {

    protected static final Map<String, Subscription> SUBSCRIPTIONS = new ConcurrentHashMap<>();

    private final Scheduler scheduler;

    protected JobExecutor() {
        this.scheduler = RxHelper.scheduler(Vertx.vertx());
    }

    private void execute(final Job job) {
        System.out.println(job.getMsg());
    }

    public void schedule(final Job job) {
        if (!SUBSCRIPTIONS.containsKey(job.getName())) {
            try {
                final Subscription subscription = Observable.just(new CronExpression(job.getCron() + " ? *"))
                        .map(c -> c.getNextValidTimeAfter(Calendar.getInstance().getTime()))
                        .map(nextRunDate -> nextRunDate.getTime() - new Date().getTime())
                        .flatMap(delay -> Observable.timer(delay, TimeUnit.MILLISECONDS, scheduler))
                        .timestamp()
                        .repeat()
                        .subscribe(t -> execute(job));
                SUBSCRIPTIONS.put(job.getName(), subscription);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void unschedule(final String jobName) {
        final Subscription subscription = SUBSCRIPTIONS.remove(jobName);
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

}
