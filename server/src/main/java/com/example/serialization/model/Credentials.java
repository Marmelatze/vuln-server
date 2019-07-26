package com.example.serialization.model;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class Credentials
{
    private String username;
    private String password;
    private String state;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getState()
    {
        try {
            ByteArrayOutputStream sink = new java.io.ByteArrayOutputStream();
            ObjectOutputStream output = null;
            output = new ObjectOutputStream(sink);

            output.writeObject(new java.util.ArrayList<String>());
            output.close();
            return new String(java.util.Base64.getEncoder().encode(sink.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "bla";
    }

    public void setState(String state)
    {
        byte[] data = Base64.getDecoder().decode(state);
        // Deserialize and cast to expected object (completely unrelated to the Gadget)
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            // could be any object, just cast it
            List result = (List) ois.readObject();
            // and use it as expected by the business usecase...
            // ... ... ...
            // ... ... ...
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.state = state;
    }
}
