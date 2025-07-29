package com.amit.job.controller;

import com.amit.job.model.*;
import com.amit.job.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private ApplicationRepository appRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/jobs/apply_jobs")
    public String viewJobs(Model model) {
        model.addAttribute("jobList", jobRepo.findAll());
        return "apply_jobs";
    }

    @GetMapping("/jobs/post_job")
    public String postJobForm() {
        return "post_job";
    }

    @PostMapping("/jobs/post_job")
    public String postJob(@ModelAttribute Job job, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        job.setEmployer(employer);
        jobRepo.save(job);
        return "redirect:/employer/employer_dashboard";
    }

    @GetMapping("/jobs/apply/{id}")
    public String applyJob(@PathVariable Long id, Principal principal, Model model) {
        User applicant = userRepo.findByUsername(principal.getName()).orElse(null);
        Optional<Job> jobOpt = jobRepo.findById(id);

        if (jobOpt.isPresent() && applicant != null) {
            Job job = jobOpt.get();

            boolean alreadyApplied = appRepo.existsByJobAndApplicant(job, applicant);
            if (!alreadyApplied) {
                Application app = new Application();
                app.setApplicant(applicant);
                app.setJob(job);
                app.setStatus("APPLIED");
                appRepo.save(app);
                model.addAttribute("appliedSuccess", true);
            } else {
                model.addAttribute("alreadyApplied", true);
            }
        }
        model.addAttribute("jobList", jobRepo.findAll());
        return "apply_jobs"; 
    }

//    @GetMapping("/jobs/apply/{id}")
//    public String applyJob(@PathVariable Long id, Principal principal) {
//        User applicant = userRepo.findByUsername(principal.getName()).orElse(null);
//        Optional<Job> jobOpt = jobRepo.findById(id);
//        if (jobOpt.isPresent() && applicant != null) {
//            Job job = jobOpt.get();
//            Application app = new Application();
//            app.setApplicant(applicant);
//            app.setJob(job);
//            app.setStatus("APPLIED");
//            appRepo.save(app);
//        }
//        return "redirect:/applicant/applicant_dashboard";
//    }
    @GetMapping("/applications/track_status")
    public String applications(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("applications", appRepo.findByApplicant(user));
        return "track_status";
    }
}

/*package com.amit.job.controller;

import com.amit.job.model.*;
import com.amit.job.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private ApplicationRepository appRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/jobs/apply_jobs")
    public String viewJobs(Model model) {
        model.addAttribute("jobList", jobRepo.findAll());
        return "apply_jobs";
    }

    @GetMapping("/jobs/post_job")
    public String postJobForm() {
        return "post_job";
    }

    @PostMapping("/jobs/post_job")
    public String postJob(@ModelAttribute Job job, Principal principal) {
        User employer = userRepo.findByUsername(principal.getName()).orElse(null);
        job.setEmployer(employer);
        jobRepo.save(job);
        return "redirect:/employer/employer_dashboard";
    }

    @GetMapping("/jobs/apply/{id}")
    public String applyJob(@PathVariable Long id, Principal principal) {
        User applicant = userRepo.findByUsername(principal.getName()).orElse(null);
        Optional<Job> jobOpt = jobRepo.findById(id);

        if (jobOpt.isPresent() && applicant != null) {
            Job job = jobOpt.get();

            boolean alreadyApplied = appRepo.existsByJobAndApplicant(job, applicant);
            if (!alreadyApplied) {
                Application app = new Application();
                app.setApplicant(applicant);
                app.setJob(job);
                app.setStatus(Application.ApplicationStatus.APPLIED);
                appRepo.save(app);
            }
        }
        return "redirect:/applicant/applicant_dashboard";
    }

    @GetMapping("/applications/track_status")
    public String applications(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("applications", appRepo.findByApplicant(user));
        return "track_status";
    }
}
 */
