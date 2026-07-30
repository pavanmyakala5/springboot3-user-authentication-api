package com.pavanit.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="First Name is Required")
	@Column(name="first_name", nullable = false, length = 100)
	private String firstName;
	
	@NotBlank(message="Last Name is Required")
	@Column(name="last_name", nullable = false, length = 100)
	private String lastName;
	
	@NotBlank(message="Mobile is Required")
	@Pattern(regexp = "[0-9]{10}$", message = "Mobile number must contain exactly 10 digits")
	@Column(nullable = false, unique = true, length = 10)
	private String mobile;
	
	@NotBlank(message="Email is Required")
	@Email(message="Enter Valid Email Please")	
	@Email(message = "Enter valid email")
    @Column(nullable = false, unique = true, length = 150)
	private String email;
	
	@NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must contain minimum 6 characters")
    @Column(nullable = false)
    private String password;	
	
	@Min(value = 18, message = "Age should be at least 18")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false, length = 20)
    private String gender;
    
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	
	

}








