package com.amit.job.controller;

import com.amit.job.model.Application;
import com.amit.job.model.Job;
import com.amit.job.model.User;
import com.amit.job.repository.ApplicationRepository;
import com.amit.job.repository.JobRepository;
import com.amit.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class EmployerController {

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private ApplicationRepository appRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/employer/employer_dashboard")
    public String employerDashboard(Model model, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        List<Job> jobs = jobRepo.findByEmployer(employer);
        model.addAttribute("jobList", jobs);
        model.addAttribute("employer", employer);
        return "employer_dashboard";
    }

    @GetMapping("/employer/jobs/post")
    public String showPostJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "post_job";
    }

    @PostMapping("/employer/jobs/post_job")
    public String postJob(@ModelAttribute Job job, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        job.setEmployer(employer);
        jobRepo.save(job);
        return "post_job_success";
    }

    @GetMapping("/employer/jobs/view_jobs_posted")
    public String viewJobsPosted(Model model, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        List<Job> jobs = jobRepo.findByEmployer(employer);
        model.addAttribute("jobList", jobs);
        return "view_jobs_posted";
    }

    @GetMapping("/employer/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        List<Application> applicants = appRepo.findByJobId(jobId);
        Job job = jobRepo.findById(jobId).orElse(null);
        model.addAttribute("job", job);
        model.addAttribute("applications", applicants);
        //  model.addAttribute("statuses", ApplicationStatus.values());
        return "view_applicants";
    }

    @PostMapping("/applications/{id}/update")
    public String updateApplicationStatus(@PathVariable Long id, @RequestParam String status) {
        Application app = appRepo.findById(id).orElse(null);
        if (app != null) {
            app.setStatus(status);
            appRepo.save(app);
        }
        return "redirect:/employer/applicants/view_applicants" + app.getJob().getId();
    }
}

/*package com.amit.job.controller;

import com.amit.job.model.Application;
import com.amit.job.model.Application.ApplicationStatus;
import com.amit.job.model.Job;
import com.amit.job.model.User;
import com.amit.job.repository.ApplicationRepository;
import com.amit.job.repository.JobRepository;
import com.amit.job.repository.UserRepository;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployerController {

    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private ApplicationRepository appRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/employer/employer_dashboard")
    public String employerDashboard(Model model, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        List<Job> jobs = jobRepo.findByEmployer(employer);
        model.addAttribute("jobList", jobs);
        return "employer_dashboard";
    }

    @GetMapping("/employer/jobs/post")
    public String showPostJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("jobTypes", Job.JobType.values());
        return "post_job";
    }

    @PostMapping("/employer/jobs/post")
    public String postJob(@ModelAttribute Job job, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        job.setEmployer(employer);
        jobRepo.save(job);
        return "post_job_success";
    }

    @GetMapping("/employer/jobs/view_jobs_posted")
    public String viewJobsPosted(Model model, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        List<Job> jobs = jobRepo.findByEmployer(employer);
        model.addAttribute("jobList", jobs);
        return "view_jobs_posted";
    }

    @GetMapping("/employer/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        List<Application> applicants = appRepo.findByJobId(jobId);
        Job job = jobRepo.findById(jobId).orElse(null);
        model.addAttribute("job", job);
        model.addAttribute("applications", applicants);
        model.addAttribute("statuses", Application.ApplicationStatus.values());
        return "view_applicants";
    }

    @PostMapping("/applications/{id}/update")
    public String updateApplicationStatus(@PathVariable Long id, @RequestParam String status) {
        Application app = appRepo.findById(id).orElse(null);
        if (app != null) {
            app.setStatus(ApplicationStatus.valueOf(status));
            appRepo.save(app);
            return "redirect:/employer/applicants/" + app.getJob().getId();
        }
        return "redirect:/employer/employer_dashboard";
    }

}
 */
