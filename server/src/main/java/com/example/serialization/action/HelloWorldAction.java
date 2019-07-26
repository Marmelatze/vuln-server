package com.example.serialization.action;

import com.example.serialization.model.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport
{
    private MessageStore messageStore;

    @Override
    public String execute() throws Exception
    {
        messageStore = new MessageStore();
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }
}
