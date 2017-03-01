package com.rapatao.intelie.job;

import com.rapatao.intelie.executor.JobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luizrapatao on 01/03/17.
 */
@Service
class JobService {

    private final JobRepository jobRepository;
    private final JobExecutor jobExecutor;

    @Autowired
    public JobService(final JobRepository jobRepository, final JobExecutor jobExecutor) {
        this.jobRepository = jobRepository;
        this.jobExecutor = jobExecutor;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public void insert(Job job) {
        jobRepository.insert(job);
        jobExecutor.schedule(job);
    }

    public void delete(String jobName) {
        jobRepository.delete(jobName);
        jobExecutor.unschedule(jobName);
    }

}
