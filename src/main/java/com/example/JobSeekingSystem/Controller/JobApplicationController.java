package com.example.JobSeekingSystem.Controller;

import com.example.JobSeekingSystem.Api.ApiResponse;
import com.example.JobSeekingSystem.Model.JobApplication;
import com.example.JobSeekingSystem.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-app")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobApplications(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyToJob(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        jobApplicationService.applyForJob(jobApplication);
        return ResponseEntity.status(200).body(new ApiResponse("Applied to job successfully"));
    }

    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawApplication(@PathVariable Integer id){
        jobApplicationService.withdrawJobApplication(id);
        return ResponseEntity.status(200).body(new ApiResponse("Application withdrawn successfully"));
    }
}
