package com.rapatao.intelie.executor;

import com.rapatao.intelie.job.Job;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by luizrapatao on 01/03/17.
 */
public class JobExecutorTest {

    private static JobExecutor jobExecutor;

    @BeforeClass
    public static void setUp() {
        jobExecutor = new JobExecutor();
    }

    @Test
    public void shouldSchedule() {
        Assert.assertTrue(jobExecutor.SUBSCRIPTIONS.isEmpty());
        final Job job = Job.builder()
                .cron("* * * * * ")
                .name("job-name")
                .msg("hello test")
                .build();
        jobExecutor.schedule(job);
        Assert.assertFalse(jobExecutor.SUBSCRIPTIONS.isEmpty());
    }

    @Test
    public void shouldUnschedule() {
        Assert.assertFalse(jobExecutor.SUBSCRIPTIONS.isEmpty());
        jobExecutor.unschedule("job-name");
        Assert.assertTrue(jobExecutor.SUBSCRIPTIONS.isEmpty());
    }

}