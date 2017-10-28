package com.ppi.model;

public class LoginCreds {

    String id; 
    String password;
    String base;
    String role;
    String status;
    String last_ip;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginCreds{" + "id=" + id + ", password=" + password + ", base=" + base + ", role=" + role + ", status=" + status + ", last_ip=" + last_ip + '}';
    }

    
    
}
