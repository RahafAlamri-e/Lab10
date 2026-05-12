package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Api.ApiException;
import com.example.JobSeekingSystem.Model.JobApplication;
import com.example.JobSeekingSystem.Repository.JobApplicationRepository;
import com.example.JobSeekingSystem.Repository.JobPostRepository;
import com.example.JobSeekingSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public void applyForJob(JobApplication jobApplication){
        if (userRepository.findUserById(jobApplication.getUserId()) == null){
            throw new ApiException("User not found");
        }

        if (jobPostRepository.findJobPostById(jobApplication.getJobPostId()) == null){
            throw new ApiException("Job Post not found");
        }

        jobApplicationRepository.save(jobApplication);
    }

    public void withdrawJobApplication(Integer id){
        JobApplication oldJobApplication = jobApplicationRepository.findJobApplicationById(id);
        if (oldJobApplication == null){
            throw new ApiException("Job application not found");
        }

        jobApplicationRepository.delete(oldJobApplication);
    }
}
