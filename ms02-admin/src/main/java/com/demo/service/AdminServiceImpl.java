package com.demo.service;

import com.demo.entity.Admin;
import com.demo.model.RegistrationRequest;
import com.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String registerAdmin(RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        String name = registrationRequest.getName();
        String phone = registrationRequest.getPhone();

        if (email == null || email.isEmpty() || password == null || password.isEmpty() ||
                name == null || name.isEmpty() || phone == null || phone.isEmpty()) {
            return "All fields are required.";
        }

        if (!isValidEmail(email)) {
            return "Invalid email address.";
        }

        if (!isValidPassword(password)) {
            return "Password must contain an uppercase letter, lowercase letters, a digit, and a special character.";
        }

        if (!isValidName(name)) {
            return "Name cannot contain numbers or special characters.";
        }

        if (adminRepository.findByEmail(email).isPresent()) {
            return "Email is already in use.";
        }

        if (adminRepository.findByPhone(phone).isPresent()) {
            return "Phone number is already in use.";
        }

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);  // You should hash the password before saving it
        admin.setName(name);
        admin.setPhone(phone);

        adminRepository.save(admin);

        return "Registration successful!";
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    private boolean isValidName(String name) {
        String nameRegex = "^[A-Za-z ]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        return pattern.matcher(name).matches();
    }
}
