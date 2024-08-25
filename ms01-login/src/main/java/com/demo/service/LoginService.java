package com.demo.service;
//import com.demo.entity.User;
//import com.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.regex.Pattern;
//
//@Service
//public class LoginService {
//    private static final int MAX_FAILED_ATTEMPTS = 3;
//    private static final long LOCK_DURATION = 30 * 60 * 1000; // 30 minutes
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public String login(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            return "Invalid email or password.";
//        }
//
//        if (user.isAccountLocked()) {
//            if (System.currentTimeMillis() > user.getLockoutEndTime()) {
//                user.setAccountLocked(false);
//                user.setFailedLoginAttempts(0);
//                userRepository.save(user);
//            } else {
//                return "Account is locked. Try again later.";
//            }
//        }
//
//        if (!isValidPassword(password)) {
//            return "Invalid password format.";
//        }
//
//        if (user.getPassword().equals(password)) {
//            user.setFailedLoginAttempts(0);
//            userRepository.save(user);
//            return "Login successful!";
//        } else {
//            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
//            if (user.getFailedLoginAttempts() >= MAX_FAILED_ATTEMPTS) {
//                user.setAccountLocked(true);
//                user.setLockoutEndTime(System.currentTimeMillis() + LOCK_DURATION);
//            }
//            userRepository.save(user);
//            return "Invalid email or password.";
//        }
//    }
//
//    private boolean isValidPassword(String password) {
//        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//        return Pattern.matches(passwordPattern, password);
//    }
//}

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String login(String email, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isAccountLocked()) {
                long lockoutEndTime = user.getLockoutEndTime();
                if (System.currentTimeMillis() < lockoutEndTime) {
                    // Convert the lockoutEndTime to a human-readable format
                    String readableTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                            .format(new java.util.Date(lockoutEndTime));
                    return "Account is locked until " + readableTime;
                } else {
                    user.setAccountLocked(false);
                    user.setFailedLoginAttempts(0);
                    user.setLockoutEndTime(0);
                    userRepository.save(user);
                }
            }

            if (user.getPassword().equals(password)) {  // No password encoder, direct comparison
                user.setFailedLoginAttempts(0);
                userRepository.save(user);
                return "Login successful!";
            } else {
                int failedAttempts = user.getFailedLoginAttempts() + 1;
                user.setFailedLoginAttempts(failedAttempts);

                if (failedAttempts >= 3) {
                    user.setAccountLocked(true);
                    long lockoutEndTime = System.currentTimeMillis() + 30 * 60 * 1000;
                    user.setLockoutEndTime(lockoutEndTime);
                    userRepository.save(user);

                    // Convert the lockoutEndTime to a human-readable format
                    String readableTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                            .format(new java.util.Date(lockoutEndTime));
                    return "Account locked until " + readableTime;
                } else {
                    userRepository.save(user);
                    return "Invalid credentials! Attempt " + failedAttempts + " of 3.";
                }
            }
        } else {
            return "User not found!";
        }
    }
}

