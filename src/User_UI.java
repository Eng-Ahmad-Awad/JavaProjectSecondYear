import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class User_UI {
    JFrame frame = new JFrame();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JLabel label = new JLabel();

    public User_UI() {

        label.setText("Chose your business");
        label.setSize(new Dimension(500, 50));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        button1.setBounds(150, 50, 80, 25);
        button1.setFocusable(false);
        button1.setText("Manager");
        button1.addActionListener(e -> {
            Manager_UI mg = new Manager_UI();
            frame.dispose();
        });

        button2.setBounds(300, 50, 80, 25);
        button2.setFocusable(false);
        button2.setText("HR");
        button2.addActionListener(e -> {
            Hr_UI hr = new Hr_UI();
            frame.dispose();
        });

        // frame
        frame.setTitle("Java Second Year Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(null);
        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }
}
