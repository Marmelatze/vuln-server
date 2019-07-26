package com.example.shared;

import java.io.*;
import java.util.Base64;

public class Message implements Serializable
{
    private String message;

    public Message(MessageInterface message)
    {
        try {
            ByteArrayOutputStream sink = new java.io.ByteArrayOutputStream();
            ObjectOutputStream output = null;
            output = new ObjectOutputStream(sink);

            output.writeObject(message);
            output.close();
            this.message = new String(java.util.Base64.getEncoder().encode(sink.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageInterface getMessage()
    {
        byte[] data = Base64.getDecoder().decode(this.message);

        // Deserialize and cast to expected object (completely unrelated to the Gadget)
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            // could be any object, just cast it
            return (MessageInterface) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
