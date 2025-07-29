package com.amit.job.repository;

import com.amit.job.model.Application;
import com.amit.job.model.Job;
import com.amit.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByApplicant(User user);

    List<Application> findByJobId(Long jobId);

  //  List<Application> findByStatus(ApplicationStatus status);

  //  List<Application> findByJobIdAndStatus(Long jobId, ApplicationStatus status);

    boolean existsByJobAndApplicant(Job job, User applicant);
}
