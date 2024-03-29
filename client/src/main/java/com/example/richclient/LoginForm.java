package com.example.richclient;

import com.example.shared.LoginMessage;
import com.example.shared.Message;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import okhttp3.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class LoginForm
{

    public static final MediaType BINARY
        = MediaType.parse("application/octet-strean");

    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    public JPanel loginview;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginForm()
    {
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // do login
                Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress("localhost", 8080));

                OkHttpClient client = new OkHttpClient.Builder()
                    .proxy(proxy)
                    .build();

                LoginMessage loginMessage = new LoginMessage();
                loginMessage.setUsername(username.getText());
                loginMessage.setPassword(password.getText());

                Message message = new Message(loginMessage);


                try {
                    ByteArrayOutputStream sink = new ByteArrayOutputStream();
                    ObjectOutputStream output = null;
                    output = new ObjectOutputStream(sink);

                    output.writeObject(message);
                    output.close();

                    RequestBody requestBody = RequestBody.create(BINARY, sink.toByteArray());

                    Request request = new Request.Builder()
                        .url("http://localhost:8081/serialization/login2.action")
                        .post(requestBody)
                        .build();

                    Response response = client.newCall(request).execute();
                    System.out.println(response.body());

                } catch (IOException e2) {
                    e2.printStackTrace();
                }


            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$()
    {
        loginview = new JPanel();
        loginview.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        username = new JTextField();
        loginview.add(username, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        loginview.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        loginview.add(spacer2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        loginview.add(usernameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        password = new JPasswordField();
        loginview.add(password, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        loginview.add(passwordLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setHorizontalAlignment(0);
        loginButton.setHorizontalTextPosition(4);
        loginButton.setText("Login");
        loginview.add(loginButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$()
    {
        return loginview;
    }

}
