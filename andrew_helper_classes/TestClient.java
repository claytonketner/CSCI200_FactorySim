// client modified from Andrew's lab 6 made with the sole purpose of testing Server.java
// delete this after we start the actual factory project code

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestClient extends JFrame implements ActionListener, Networked {
  private NetComm netComm;

  private JTextField text;
  private JButton button;
  private JLabel label;

  public TestClient() {
    // set up networking (assume server is on localhost, but final project cannot make this assumption)
    try {
      netComm = new NetComm(new Socket("localhost", Server.Port), this);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    // set up GUI
    setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    text = new JTextField();
    button = new JButton("Submit");
    label = new JLabel(" ");
    add(text);
    add(button);
    add(label);
    button.addActionListener(this);
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    TestClient client = new TestClient();
  }

  public void msgReceived(Object msgObj, NetComm sender) {
    if (msgObj instanceof CloseConnectionMsg) {
      System.out.println("Unexpectedly disconnected from server");
      System.exit(1);
    }
    else if (msgObj instanceof String) {
      label.setText((String)msgObj);
    }
    else {
      System.out.println("Warning: received unknown message " + msgObj);
    }
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == button) {
      netComm.write(text.getText());
      text.setText("");
    }
  }
}
