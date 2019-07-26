package com.example.serialization.action;

import com.example.serialization.model.Credentials;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction  extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    private Credentials credentials;

    @Override
    public String execute() throws Exception
    {
        credentials = new Credentials();
        return "input";
    }

    @Override
    public void validate()
    {
        if (credentials == null) {
            return;
        }
        if (!credentials.getUsername().contentEquals("admin") && !credentials.getPassword().contentEquals("admin")) {
            addFieldError("credentials.username", "Login failed. Wrong username or password");
        }
    }

    public Credentials getCredentials()
    {
        return credentials;
    }

    public void setCredentials(Credentials credentials)
    {
        this.credentials = credentials;
    }
}
