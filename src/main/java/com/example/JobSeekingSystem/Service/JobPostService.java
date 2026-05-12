package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Api.ApiException;
import com.example.JobSeekingSystem.Model.JobPost;
import com.example.JobSeekingSystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public void updateJobPost(Integer id, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.findJobPostById(id);
        if (oldJobPost == null){
            throw new ApiException("Job post not found");
        }

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setPostedDate(jobPost.getPostedDate());

        jobPostRepository.save(oldJobPost);
    }

    public void deleteJobPost(Integer id){
        JobPost oldJobPost = jobPostRepository.findJobPostById(id);
        if (oldJobPost == null){
            throw new ApiException("Job post not found");
        }

        jobPostRepository.delete(oldJobPost);
    }
}
