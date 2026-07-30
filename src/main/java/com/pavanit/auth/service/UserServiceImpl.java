package com.pavanit.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavanit.auth.dto.ApiResponse;
import com.pavanit.auth.dto.LoginRequest;
import com.pavanit.auth.dto.SignupRequest;
import com.pavanit.auth.entity.User;
import com.pavanit.auth.exception.UserNotFoundException;
import com.pavanit.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();	

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiResponse signup(SignupRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false,
                    "Email already exists");
        }

        if (repository.existsByMobile(request.getMobile())) {
            return new ApiResponse(false,
                    "Mobile number already exists");
        }

        if (!request.getPassword()
                .equals(request.getConfirmPassword())) {

            return new ApiResponse(false,
                    "Password and Confirm Password do not match");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setMobile(request.getMobile());

        user.setEmail(request.getEmail());

        user.setPassword(
                encoder.encode(request.getPassword()));

        user.setAge(request.getAge());

        user.setGender(request.getGender());

        repository.save(user);

        return new ApiResponse(true,
                "User Registered Successfully");

    }
    
    @Override
    public ApiResponse login(LoginRequest request) {

        Optional<User> optionalUser =
                repository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {

            return new ApiResponse(false,
                    "Email does not exist");
        }

        User user = optionalUser.get();

        boolean passwordMatched =
                encoder.matches(request.getPassword(),
                        user.getPassword());

        if (!passwordMatched) {

            return new ApiResponse(false,
                    "Invalid Password");
        }

        return new ApiResponse(true,
                "Login Successful");
    }

	
	
	@Override
    public ApiResponse logout() {

        return new ApiResponse(true,
                "Logout Successful");
    }

	@Override
	public List<User> getAllUsers() {

	    return repository.findAll();

	}

	@Override
	public User getUserById(Long id) {

	    return repository.findById(id)

	            .orElseThrow(() ->

	                    new UserNotFoundException(

	                            "User not found"));

	}

	@Override
	public ApiResponse deleteUser(Long id) {

	    User user = getUserById(id);

	    repository.delete(user);

	    return new ApiResponse(true,
	            "User Deleted Successfully");

	}

	@Override
	public User updateUser(Long id,
	                       SignupRequest request) {

	    User user = getUserById(id);

	    user.setFirstName(request.getFirstName());

	    user.setLastName(request.getLastName());

	    user.setMobile(request.getMobile());

	    user.setAge(request.getAge());

	    user.setGender(request.getGender());

	    repository.save(user);

	    return user;

	}

	
    
    

}