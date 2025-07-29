/*package com.amit.job.service;

import com.amit.job.model.Job;
import com.amit.job.model.User;
import com.amit.job.repository.JobRepository;
import com.amit.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    @Autowired private JobRepository jobRepo;
    @Autowired private UserRepository userRepo;

    public void saveJob(Job job, String employerUsername) {
        User employer = userRepo.findByUsername(employerUsername).orElseThrow();
        job.setEmployer(employer);
        jobRepo.save(job);
    }

    public List<Job> findByEmployer(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        return jobRepo.findByEmployer(user);
    }

    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    public List<Job> searchJobs(String keyword) {
        return jobRepo.findByTitleContainingIgnoreCase(keyword);
    }
}
*/