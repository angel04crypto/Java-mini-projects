import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[10];
    private String[] functions = {"+", "-", "*", "/", "sqrt", "sin", "cos", "tan", "log", "pi"};
    private JButton equalButton, clearButton, deleteButton, decimalButton, signButton;
    private String operator = "";
    private double firstNum, secondNum, result;

    public ScientificCalculator() {
        setTitle("Name: Angel Kumari | Roll No: 2306258");
        setLayout(new BorderLayout());
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));

     
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }
        
        for (int i = 1; i < 10; i++) {
            panel.add(numberButtons[i]);
        }
      
        panel.add(numberButtons[0]);

        for (int i = 0; i < functions.length; i++) {
            functionButtons[i] = new JButton(functions[i]);
            functionButtons[i].addActionListener(this);
            panel.add(functionButtons[i]);
        }

 
        decimalButton = new JButton(".");
        decimalButton.addActionListener(this);
        panel.add(decimalButton);

        equalButton = new JButton("=");
        equalButton.addActionListener(this);
        panel.add(equalButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        panel.add(clearButton);

        signButton = new JButton("+/-");
        signButton.addActionListener(this);
        panel.add(signButton);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            textField.setText(textField.getText() + command);
        }
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            operator = command;
            firstNum = Double.parseDouble(textField.getText());
            textField.setText("");
        }
        else if (command.equals("sqrt")) {
            firstNum = Double.parseDouble(textField.getText());
            result = Math.sqrt(firstNum);
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("sin")) {
            firstNum = Double.parseDouble(textField.getText());
            result = Math.sin(Math.toRadians(firstNum));
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("cos")) {
            firstNum = Double.parseDouble(textField.getText());
            result = Math.cos(Math.toRadians(firstNum));
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("tan")) {
            firstNum = Double.parseDouble(textField.getText());
            result = Math.tan(Math.toRadians(firstNum));
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("log")) {
            firstNum = Double.parseDouble(textField.getText());
            result = Math.log10(firstNum);
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("pi")) {
            textField.setText(String.valueOf(Math.PI));
        }
        else if (command.equals("=")) {
            secondNum = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    if (secondNum != 0) {
                        result = firstNum / secondNum;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
        }
        else if (command.equals("C")) {
            textField.setText("");
        }
        else if (command.equals("Delete")) {
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        else if (command.equals("+/-")) {
            if (!textField.getText().isEmpty()) {
                double temp = Double.parseDouble(textField.getText());
                temp = temp * -1;
                textField.setText(String.valueOf(temp));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        });
    }
}

