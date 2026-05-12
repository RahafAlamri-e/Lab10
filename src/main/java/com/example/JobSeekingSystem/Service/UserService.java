package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Api.ApiException;
import com.example.JobSeekingSystem.Model.User;
import com.example.JobSeekingSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        if (userRepository.findUserByEmail(user.getEmail()) != null){
            throw new ApiException("Email already exist");
        }

        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null){
            throw new ApiException("User not found");
        }

        User checkEmail = userRepository.findUserByEmail(user.getEmail());
        if (checkEmail != null && !checkEmail.getId().equals(id)){
            throw new ApiException("Email already exist");
        }

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null){
            throw new ApiException("User not found");
        }

        userRepository.delete(oldUser);
    }

}
