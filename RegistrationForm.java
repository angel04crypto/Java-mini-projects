import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
public class RegistrationForm extends JFrame implements ActionListener {
JTextField nameField, mobileField;
JRadioButton male, female;
JComboBox<String> dayBox, monthBox, yearBox;
JTextArea addressArea;
JCheckBox terms;
JButton submit, reset;
JLabel resultLabel;
ButtonGroup gender;
public RegistrationForm() {
setTitle("Registration Form");
setSize(500, 500);
setLayout(null);
JLabel nameLabel = new JLabel("Name");
nameLabel.setBounds(30, 50, 100, 20);
add(nameLabel);
nameField = new JTextField();
nameField.setBounds(150, 50, 150, 20);
add(nameField);
JLabel mobileLabel = new JLabel("Mobile");
mobileLabel.setBounds(30, 90, 100, 20);
add(mobileLabel);
mobileField = new JTextField();
mobileField.setBounds(150, 90, 150, 20);
add(mobileField);
JLabel genderLabel = new JLabel("Gender");
genderLabel.setBounds(30, 130, 100, 20);
add(genderLabel);
male = new JRadioButton("Male");
male.setBounds(150, 130, 70, 20);
female = new JRadioButton("Female");
female.setBounds(220, 130, 80, 20);
ButtonGroup genderGroup = new ButtonGroup();
genderGroup.add(male);
genderGroup.add(female);
add(male);
add(female);
JLabel dobLabel = new JLabel("DOB");
dobLabel.setBounds(30, 170, 100, 20);
add(dobLabel);
String[] days = new String[31];
for (int i = 0; i < 31; i++) days[i] = Integer.toString(i + 1);
dayBox = new JComboBox<>(days);
dayBox.setBounds(150, 170, 50, 20);
add(dayBox);
String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
"Nov", "Dec" };
monthBox = new JComboBox<>(months);
monthBox.setBounds(210, 170, 60, 20);
add(monthBox);
String[] years = new String[100];
int currentYear = LocalDate.now().getYear();
for (int i = 0; i < 100; i++) years[i] = Integer.toString(currentYear - i);
yearBox = new JComboBox<>(years);
yearBox.setBounds(280, 170, 70, 20);
add(yearBox);
JLabel addressLabel = new JLabel("Address");
addressLabel.setBounds(30, 210, 100, 20);
add(addressLabel);
addressArea = new JTextArea();
addressArea.setBounds(150, 210, 200, 60);
add(addressArea);
terms = new JCheckBox("Accept Terms And Conditions.");
terms.setBounds(150, 280, 250, 20);
add(terms);
submit = new JButton("Submit");
submit.setBounds(80, 320, 100, 30);
submit.addActionListener(this);
add(submit);
reset = new JButton("Reset");
reset.setBounds(200, 320, 100, 30);
reset.addActionListener(this);
add(reset);
resultLabel = new JLabel();
resultLabel.setBounds(30, 360, 400, 100);
add(resultLabel);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent e) {
if (e.getSource() == submit) {
String name = nameField.getText();
String mobile = mobileField.getText();
String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "";
String dob = dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " +
yearBox.getSelectedItem();
String address = addressArea.getText();
if (!mobile.matches("\\d{10}")) {
resultLabel.setText("Invalid Mobile Number. Must be 10 digits.");
return;
}
int day = Integer.parseInt((String) dayBox.getSelectedItem());
int month = monthBox.getSelectedIndex() + 1;
int year = Integer.parseInt((String) yearBox.getSelectedItem());
LocalDate dobDate = LocalDate.of(year, month, day);
if (!dobDate.isBefore(LocalDate.of(2010, 1, 1))) {
resultLabel.setText("DOB must be before 1st Jan 2010.");
return;
}
if (!terms.isSelected()) {
resultLabel.setText("Please accept terms and conditions.");
return;
}
resultLabel.setText("<html>Name: " + name + "<br>Mobile: " + mobile + "<br>Gender: "
+ gender +
"<br>DOB: " + dob + "<br>Address: " + address + "</html>");
} else if (e.getSource() == reset) {
nameField.setText("");
mobileField.setText("");
gender.clearSelection();
dayBox.setSelectedIndex(0);
monthBox.setSelectedIndex(0);
yearBox.setSelectedIndex(0);
addressArea.setText("");
terms.setSelected(false);
resultLabel.setText("");
}
}
public static void main(String[] args) {
new RegistrationForm();
}
}
