package com.example.JobSeekingSystem.Controller;

import com.example.JobSeekingSystem.Api.ApiResponse;
import com.example.JobSeekingSystem.Model.JobPost;
import com.example.JobSeekingSystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        jobPostService.updateJobPost(id, jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer id){
        jobPostService.deleteJobPost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully"));
    }
}
