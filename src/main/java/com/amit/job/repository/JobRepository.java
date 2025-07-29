package com.amit.job.repository;

import com.amit.job.model.Job;
import com.amit.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployer(User employer);
    List<Job> findByTitleContainingIgnoreCase(String keyword);
}
