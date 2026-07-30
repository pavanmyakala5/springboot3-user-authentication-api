package com.pavanit.auth.service;

import java.util.List;

import com.pavanit.auth.dto.ApiResponse;
import com.pavanit.auth.dto.LoginRequest;
import com.pavanit.auth.dto.SignupRequest;
import com.pavanit.auth.entity.User;

public interface UserService {
	ApiResponse signup(SignupRequest request);

	ApiResponse login(LoginRequest request);

	ApiResponse logout();

	List<User> getAllUsers();

	User getUserById(Long id);

	ApiResponse deleteUser(Long id);

	User updateUser(Long id, SignupRequest request);
}
