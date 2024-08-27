

package com.demo.entity;



import javax.persistence.*;


@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length=50,nullable = false, unique = true)
    private String email;
    @Column(length=50,nullable = false)
    private String password;
    @Column
    private int failedLoginAttempts;
    @Column(nullable = false)
    private boolean accountLocked;
    @Transient
    private long lockoutEndTime;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public long getLockoutEndTime() {
        return lockoutEndTime;
    }

    public void setLockoutEndTime(long lockoutEndTime) {
        long currentTime = System.currentTimeMillis();
        long lockoutDuration = 30 * 60 * 1000;

        this.lockoutEndTime =  currentTime + lockoutDuration;


    }
}

