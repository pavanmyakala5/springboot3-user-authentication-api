package com.pavanit.auth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.pavanit.auth.dto.ApiResponse;
import com.pavanit.auth.dto.LoginRequest;
import com.pavanit.auth.dto.SignupRequest;
import com.pavanit.auth.entity.User;
import com.pavanit.auth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(
            @Valid @RequestBody SignupRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            String error =
                    bindingResult
                            .getFieldError()
                            .getDefaultMessage();

            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(false, error));
        }

        ApiResponse response =
                service.signup(request);

        if (response.isSuccess()) {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody LoginRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            String error =
                    bindingResult
                            .getFieldError()
                            .getDefaultMessage();

            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(false, error));
        }

        ApiResponse response =
                service.login(request);

        if (response.isSuccess()) {

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout() {

        return ResponseEntity.ok(service.logout());

    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(service.getAllUsers());

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(

                service.getUserById(id));

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(

            @PathVariable Long id,

            @RequestBody SignupRequest request) {

        return ResponseEntity.ok(

                service.updateUser(id,
                        request));

    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(

            @PathVariable Long id) {

        return ResponseEntity.ok(

                service.deleteUser(id));

    }

}







