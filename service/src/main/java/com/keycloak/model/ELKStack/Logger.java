package com.keycloak.model.ELKStack;

public class Logger {

    //=================================
    //      Data members
    //=================================
    private String name;
    private String email;
    //=================================
    //  Default Constructors
    //=================================
    public Logger(){}
    //=================================
    //  Parameterized Constructors
    //=================================
    public Logger(String name, String email) {
        this.name = name;
        this.email = email;
    }
    //=================================
    //  Properties
    //=================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    //================================
    //  override toString()
    //================================
    @Override
    public String toString() {
        return "Logger [email=" + email + ", name=" + name + "]";
    }
    
}
