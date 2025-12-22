
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class User_UI {
    JFrame frame = new JFrame();
    JButton subment = new JButton();
    JPasswordField passField = new JPasswordField();
    JTextField userField = new JTextField();
    JLabel lblhead;
    JLabel lbluser;
    JLabel lblpass;
    JPanel panelhead;
    JPanel panelcent;
    JPanel paneldown;

    public User_UI() {

        //header
        lblhead = new JLabel("Welcome in Factory App");
        panelhead = new JPanel();
        panelhead.setLayout(new FlowLayout());
        panelhead.add(lblhead);

        //center
        lbluser = new JLabel("User_Name");
        lbluser.setHorizontalAlignment(JLabel.CENTER);
        lblpass = new JLabel("Passowrd");
        lblpass.setHorizontalAlignment(JLabel.CENTER);
        userField = new JTextField("");
        passField = new JPasswordField("");
        panelcent = new JPanel();
        panelcent.setLayout(new GridLayout(2, 2));
        panelcent.add(lbluser);
        panelcent.add(userField);
        panelcent.add(lblpass);
        panelcent.add(passField);

        //down button
        subment = new JButton("Login");
        subment.addActionListener(e -> {
            //git the data and check from user state
            
            
        });
        paneldown = new JPanel();
        paneldown.setLayout(new FlowLayout());
        paneldown.add(subment);

        //frame
        frame = new JFrame("Sign in");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.add(panelhead, BorderLayout.NORTH);
        frame.add(panelcent, BorderLayout.CENTER);
        frame.add(paneldown, BorderLayout.SOUTH);

    }
}
