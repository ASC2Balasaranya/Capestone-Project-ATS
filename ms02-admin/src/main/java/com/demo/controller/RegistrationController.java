package com.demo.controller;

import com.demo.model.RegistrationRequest;
import com.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final AdminService adminService;

    @Autowired
    public RegistrationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return adminService.registerAdmin(registrationRequest);
    }
}
