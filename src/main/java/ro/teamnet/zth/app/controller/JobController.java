package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Job;
import ro.teamnet.zth.app.service.JobService;
import ro.teamnet.zth.app.service.JobServiceImpl;

import java.util.List;

/**
 * Author: Ovidiu
 * Date:   5/6/2015
 */
@MyController(urlPath = "/jobs")
public class JobController {

    private JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneJob() {
        return "oneRandomJob";
    }

}
