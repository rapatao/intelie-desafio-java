package com.rapatao.intelie.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luizrapatao on 01/03/17.
 */
@Controller
@RequestMapping(value = "/jobs", produces = "application/json")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody final Job job) {
        try {
            jobService.insert(job);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{jobName}")
    public ResponseEntity delete(@PathVariable(value = "jobName") final String jobName) {
        try {
            jobService.delete(jobName);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
