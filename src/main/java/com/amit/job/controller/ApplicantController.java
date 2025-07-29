package com.amit.job.controller;

import com.amit.job.model.User;
import com.amit.job.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ApplicantController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/applicant/applicant_dashboard")
    public String applicantDashboard(Model model, Principal principal) {
        User applicant = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("applicant", applicant);
        return "applicant_dashboard";
    }

    @GetMapping("/applicant/profile")
    public String viewProfile(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/applicant/edit_profile")
    public String editProfile(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping("/applicant/update_profile")
    public String updateProfile(@ModelAttribute User user,
            @RequestParam("profileImage") MultipartFile file,
            Principal principal) throws IOException {

        User existingUser = userRepo.findByUsername(principal.getName()).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());

            if (!user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            if (!file.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                Files.createDirectories(uploadPath);
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                existingUser.setProfilePicture("/uploads/" + fileName);
            }

            userRepo.save(existingUser);
        }

        return "redirect:/applicant/profile?updated";
    }
}

/*package com.amit.job.controller;

import com.amit.job.model.Application;
import com.amit.job.model.Job;
import com.amit.job.model.User;
import com.amit.job.repository.ApplicationRepository;
import com.amit.job.repository.JobRepository;
import com.amit.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicantController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private ApplicationRepository appRepo;

    @GetMapping("/applicant/applicant_dashboard")
    public String applicantDashboard(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("username", user != null ? user.getName() : "Applicant");
        return "applicant_dashboard";
    }

    @GetMapping("/jobs/apply_jobs")
    public String viewJobs(Model model) {
        List<Job> jobList = jobRepo.findAll();
        model.addAttribute("jobList", jobList);
        return "apply_jobs";
    }

    @GetMapping("/applications/track_status")
    public String applications(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        List<Application> applications = appRepo.findByApplicant(user);
        model.addAttribute("applications", applications);
        return "track_status";
    }

    @GetMapping("/jobs/apply/{id}")
    public String applyJob(@PathVariable Long id, Principal principal) {
        User applicant = userRepo.findByUsername(principal.getName()).orElse(null);
        Job job = jobRepo.findById(id).orElse(null);

        if (applicant != null && job != null) {
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
}
*/



