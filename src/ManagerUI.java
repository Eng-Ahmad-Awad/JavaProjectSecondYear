
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class ManagerUI extends JFrame {

    Management mg = new Management();
    // components to basic frame
    JPanel pnl1;
    JPanel pnl2;
    JPanel pnl3;
    JPanel pnl4;
    JPanel pnl5;
    JPanel pnl6;
    JLabel lbl1;
    JLabel lbl2;
    JLabel lbl3;
    JButton btn1;
    JButton btn2;
    JButton btn3;

    // constructor
    public ManagerUI() {
        // Initialize the UI components here

        pnl1 = new JPanel();
        pnl2 = new JPanel();
        pnl3 = new JPanel();
        pnl4 = new JPanel();
        pnl5 = new JPanel();
        pnl6 = new JPanel();
        lbl1 = new JLabel("Add ProductLine");
        lbl2 = new JLabel("Edit ProductLine");
        lbl3 = new JLabel("View Lines Performance");
        btn1 = new JButton("Add");
        btn2 = new JButton("Edit");
        btn3 = new JButton("View");

        btn1.setFocusable(false);
        btn1.addActionListener(e -> {
            addFrameMethod();
        });
        btn2.setFocusable(false);
        btn2.addActionListener(e -> {
            editFrameMethod();
        });
        btn3.setFocusable(false);
        btn3.addActionListener(e -> {
            viewFrameMethod();
        });

        pnl1.setLayout(new FlowLayout());
        pnl2.setLayout(new FlowLayout());
        pnl3.setLayout(new FlowLayout());
        pnl4.setLayout(new FlowLayout());
        pnl5.setLayout(new FlowLayout());
        pnl6.setLayout(new FlowLayout());

        pnl1.add(lbl1);
        pnl2.add(btn1);
        pnl3.add(lbl2);
        pnl4.add(btn2);
        pnl5.add(lbl3);
        pnl6.add(btn3);

        setTitle("Manager UI");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);
        setVisible(true);
        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);
        add(pnl6);
    }

    // frame for add button
    JFrame addFrameMethod() {
        JFrame addFrame = new JFrame("Add Product Line");

        String[] statusOptions = {"Active", "Inactive", "Disabled"};
        JLabel lbl1 = new JLabel("Line Name:");
        JLabel lbl2 = new JLabel("Line Status:");
        JTextField txt1 = new JTextField(10);
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
        JPanel pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout());
        pnl1.add(lbl1);
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(new FlowLayout());
        pnl2.add(txt1);
        JPanel pnl3 = new JPanel();
        pnl3.setLayout(new FlowLayout());
        pnl3.add(lbl2);
        JPanel pnl4 = new JPanel();
        pnl4.setLayout(new FlowLayout());
        pnl4.add(comboBox);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(pnl1);
        panel.add(pnl2);
        panel.add(pnl3);
        panel.add(pnl4);
        // button to submit new product line
        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.addActionListener(e -> {
            String lineName = txt1.getText();
            String lineStatus = (String) comboBox.getSelectedItem();
            addFrame.dispose();
            // Here you can add code to handle the new product line data
            mg.Add_ProductLine(lineName);

        });
        addFrame.setSize(300, 200);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setLayout(new BorderLayout());
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
        addFrame.add(panel);
        addFrame.add(submitButton, BorderLayout.SOUTH);
        return addFrame;
    }

    // frame for edit button
    JFrame editFrameMethod() {
        JFrame editFrame = new JFrame("Choose Product Line to Edit");
        JLabel lbl2 = new JLabel("Change ProductLine Name:");
        JTextField txt = new JTextField(10);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        JPanel pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout());
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(new FlowLayout());
        JPanel pnl3 = new JPanel();
        pnl3.setLayout(new FlowLayout());
        JPanel pnl4 = new JPanel();
        pnl4.setLayout(new FlowLayout());
        JLabel lbl1 = new JLabel("Select Product Line:");

        String[] lineName = new String[ProductLine.deferentproductLines.size()];
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            lineName[i] = (String) ProductLine.deferentproductLines.get(i);
        }
        JComboBox<String> comboBox = new JComboBox<>(lineName);
        pnl1.add(lbl1);
        pnl2.add(comboBox);
        pnl3.add(lbl2);
        pnl4.add(txt);
        panel.add(pnl1);
        panel.add(pnl2);
        panel.add(pnl3);
        panel.add(pnl4);
        // button to confirm selection
        JButton selectButton = new JButton("Select");
        selectButton.setFocusable(false);
        selectButton.addActionListener(e -> {
            String selectedLine = (String) comboBox.getSelectedItem();
            String newLineName = txt.getText();
            editFrame.dispose();
            // Here you can add code to handle the edited product line data
            mg.ProductLine_editer(selectedLine, newLineName);

        });
        editFrame.setSize(400, 200);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLayout(new BorderLayout());
        editFrame.setLocationRelativeTo(null);
        editFrame.setVisible(true);
        editFrame.add(panel, BorderLayout.CENTER);
        editFrame.add(selectButton, BorderLayout.SOUTH);
        return editFrame;
    }

    // frame for view button
    JFrame viewFrameMethod() {
        //frame to view lines performance
        JFrame viewFrame = new JFrame("View Lines Performance");
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        viewFrame.setSize(300, 200);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setLayout(new FlowLayout());
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(false);
        viewFrame.add(progressBar);

        //frame to chose product line
        JFrame chooseFrame = new JFrame("Choose Product Line");
        JLabel lbl1 = new JLabel("Select Product Line:");
        String[] lineName = new String[ProductLine.deferentproductLines.size()];
        for (int i = 0; i < ProductLine.deferentproductLines.size(); i++) {
            lineName[i] = (String) ProductLine.deferentproductLines.get(i);
        }
        JComboBox<String> comboBox = new JComboBox<>(lineName);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JPanel pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout());
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(new FlowLayout());
        JButton selectButton = new JButton("Select");
        selectButton.setFocusable(false);
        selectButton.addActionListener(e -> {
            String selectedLine = (String) comboBox.getSelectedItem();
            chooseFrame.dispose();
            viewFrame.setVisible(true);
            // Here you can add code to display the performance of the selected product line
            mg.View_ProductLine_Performance();

        });
        pnl1.add(lbl1);
        pnl2.add(comboBox);
        panel.add(pnl1);
        panel.add(pnl2);
        chooseFrame.setSize(300, 100);
        chooseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chooseFrame.setLayout(new BorderLayout());
        chooseFrame.setLocationRelativeTo(null);
        chooseFrame.setVisible(true);
        chooseFrame.add(panel);
        chooseFrame.add(selectButton, BorderLayout.SOUTH);

        return chooseFrame;
    }

}
