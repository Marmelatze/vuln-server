package com.example.serialization.action;

import com.example.shared.LoginMessage;
import com.example.shared.Message;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.util.List;

public class Login2Action extends ActionSupport implements ServletRequestAware
{
    private HttpServletRequest request;

    @Override
    public String execute() throws Exception
    {
        byte[] bytes = IOUtils.toByteArray(request.getInputStream());
        // Deserialize and cast to expected object (completely unrelated to the Gadget)
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            // could be any object, just cast it
            ObjectInputFilter filesOnlyFilter = ObjectInputFilter.Config.createFilter("com.example.shared.*;!*");
            ois.setObjectInputFilter(filesOnlyFilter);
            Message message = (Message) ois.readObject();
            LoginMessage loginMessage = (LoginMessage) message.getMessage();
            System.out.println("Login from " + loginMessage.getUsername() + ":" + loginMessage.getPassword());
            // and use it as expected by the business usecase...*/
            // ... ... ...
            // ... ... ...
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return "success";
    }


    @Override
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
}
