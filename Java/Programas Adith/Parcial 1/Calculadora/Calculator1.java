
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Calculator1 extends JPanel implements ActionListener {
    double firstnum;
    double secondnum;
    double answer;
    String sanswer;
    int operation = 0;
    protected JButton zero, one, two, three, four, five, six, seven, eight, nine, equal, multiply, decmal, minus, plus,
            divide;
    JTextField screen;
    GridBagConstraints constraints = new GridBagConstraints();

    public Calculator1() {

        setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 5;

        screen = new JTextField(20);
        add(screen, constraints);
        constraints.gridy = 1;
        constraints.gridwidth = 1;

        seven = new JButton("7");
        seven.setActionCommand("eseven");
        add(seven, constraints);
        constraints.gridx = 1;
        eight = new JButton("8");
        eight.setActionCommand("eeight");
        add(eight, constraints);
        constraints.gridx = 2;
        nine = new JButton("9");
        nine.setActionCommand("enine");
        add(nine, constraints);
        constraints.gridx = 3;
        divide = new JButton("/");
        divide.setActionCommand("edivide");
        add(divide, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;

        four = new JButton("4");
        four.setActionCommand("efour");
        add(four, constraints);
        constraints.gridx = 1;
        five = new JButton("5");
        five.setActionCommand("efive");
        add(five, constraints);
        constraints.gridx = 2;
        six = new JButton("6");
        six.setActionCommand("esix");
        add(six, constraints);
        constraints.gridx = 3;
        multiply = new JButton("*");
        multiply.setActionCommand("emultiply");
        add(multiply, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        one = new JButton("1");
        one.setActionCommand("eone");
        add(one, constraints);
        constraints.gridx = 1;
        two = new JButton("2");
        two.setActionCommand("etwo");
        add(two, constraints);
        constraints.gridx = 2;
        three = new JButton("3");
        three.setActionCommand("ethree");
        add(three, constraints);
        constraints.gridx = 3;
        minus = new JButton("-");
        minus.setActionCommand("eminus");
        add(minus, constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        zero = new JButton("0");
        zero.setActionCommand("ezero");
        add(zero, constraints);
        constraints.gridx = 1;
        decmal = new JButton(".");
        decmal.setActionCommand("edecmal");
        add(decmal, constraints);
        constraints.gridx = 2;
        equal = new JButton("=");
        equal.setActionCommand("eequals");
        add(equal, constraints);
        constraints.gridx = 3;
        plus = new JButton("+");
        plus.setActionCommand("eplus");
        add(plus, constraints);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        zero.addActionListener(this);
        plus.addActionListener(this);
        minus.addActionListener(this);
        equal.addActionListener(this);
        divide.addActionListener(this);
        multiply.addActionListener(this);
        decmal.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if ("eone".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '1');
        }
        if ("etwo".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '2');
        }
        if ("ethree".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '3');
        }
        if ("efour".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '4');
        }
        if ("efive".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '5');
        }
        if ("esix".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '6');
        }
        if ("eseven".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '7');
        }
        if ("eeight".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '8');
        }
        if ("enine".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '9');
        }
        if ("ezero".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '0');
        }
        if ("eplus".equals(e.getActionCommand())) {
            firstnum = (Double.parseDouble(screen.getText()));
            operation = 1;
            screen.setText("");
        }
        if ("eminus".equals(e.getActionCommand())) {
            firstnum = (Double.parseDouble(screen.getText()));
            operation = 2;
            screen.setText("");
        }
        if ("emultiply".equals(e.getActionCommand())) {
            firstnum = (Double.parseDouble(screen.getText()));
            operation = 3;
            screen.setText("");
        }
        if ("edivide".equals(e.getActionCommand())) {
            firstnum = (Double.parseDouble(screen.getText()));
            operation = 4;
            screen.setText("");
        }
        if ("eequals".equals(e.getActionCommand())) {
            secondnum = (Double.parseDouble(screen.getText()));

            if (operation == 1) {
                screen.setText("");
                answer = firstnum + secondnum;
                sanswer = Double.toString(answer);
                screen.setText(sanswer);
            }
            if (operation == 2) {
                screen.setText("");
                answer = firstnum - secondnum;
                sanswer = Double.toString(answer);
                screen.setText(sanswer);
            }
            if (operation == 3) {
                screen.setText("");
                answer = firstnum * secondnum;
                sanswer = Double.toString(answer);
                screen.setText(sanswer);
            }
            if (operation == 4) {
                screen.setText("");
                answer = firstnum / secondnum;
                sanswer = Double.toString(answer);
                screen.setText(sanswer);

            }
        }
        if ("edecmal".equals(e.getActionCommand())) {
            String pnum = screen.getText();
            screen.setText(pnum + '.');
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Calculator1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Calculator1 newContentPane = new Calculator1();

        frame.setContentPane(newContentPane);
        frame.setSize(400, 500);
        frame.setLocation(200, 200);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
