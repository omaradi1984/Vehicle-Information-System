import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

public class VehicleUI implements ActionListener {

public static String div = "===================================";
public static List<Vehicle> vehicles = new ArrayList<Vehicle>();
public static JTextArea display;
public static JButton[] buttons = new JButton[3];
public static JLabel[] subTitles = new JLabel[7];
public static JTextField[] inputs = new JTextField[7];

public static void main(String[] args) {

    // Fonts used in the interface
    Font titleFont = new Font("Times New Roman", 1, 24);
    Font subFont = new Font("Times New Roman", 1, 16);

    // Frame properties
    JFrame frame = new JFrame("Vehicle Record System");
    frame.setSize(560, 550);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    // Container
    JPanel container = new JPanel();
    container.setLayout(null);
    frame.setContentPane(container);

    // Title
    JLabel title = new JLabel("Vehicle Record", JLabel.CENTER);
    title.setFont(titleFont);
    title.setOpaque(true);
    title.setForeground(Color.white);
    title.setBackground(Color.darkGray);
    title.setBounds(160, 10, 200, 24);

    // Labels and text fields
    for (int i = 0; i < subTitles.length; i++) {
        subTitles[i] = new JLabel();
        subTitles[i].setFont(subFont);
        subTitles[i].setBounds(5, 50 + (i * 35), 190, 16);
    }
    subTitles[0].setText("VIN: ");
    subTitles[1].setText("Type: ");
    subTitles[2].setText("Brand: ");
    subTitles[3].setText("Model: ");
    subTitles[4].setText("Color: ");
    subTitles[5].setText("Year: ");
    subTitles[6].setText("Registration Date: ");

    for (int i = 0; i < subTitles.length; i++) {
        inputs[i] = new JTextField();
        inputs[i].setBounds(160, 47 + (35 * i), 150, 22);
    }

    // Buttons
    for (int i = 0; i < buttons.length; i++) {
        buttons[i] = new JButton();
        buttons[i].addActionListener(new VehicleUI());
        buttons[i].setBounds(330, 47 + (60 * i), 200, 50);
        buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
    }
    buttons[0].setText("<html><center>"+"Add"+"<br>"+"(ALL FIELDS ARE REQUIRED)"+"</center></html>");
    buttons[1].setText("<html><center>"+"Remove"+"<br>"+"(Type VIN# to find record)"+"</center></html>");
    buttons[2].setText("Show current records");

    // Text area
    display = new JTextArea();
    display.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(display);
    scrollPane.setBounds(5, 300, 535, 200);

    // Building interface
    container.add(title);
    container.add(scrollPane);
    // Using max value of subtitles for the loop because the number of text fields is equal to subtitles
    for (int i = 0; i < subTitles.length; i++) {
        container.add(subTitles[i]);
        container.add(inputs[i]);
    }
    for (int i = 0; i < buttons.length; i++) {
        container.add(buttons[i]);
    }

    // Additional features
    frame.toFront();
    frame.setVisible(true);
}

public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(buttons[0])) {
        // Checking if the program should continue or not
        boolean pass = true;
        // Loop to check if all text fields aren't null
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].getText().equals("")) {
                display.setText("Error: Enter data for ALL fields.");
                pass = false;
            }
        }

        // If data exists, the program continues
        if (pass == true) {
            // Checking if VIN# already exists
            if (vehicles.contains(inputs[0].getText())) {
                // Displaying error message if entered VIN# already exists
                display.setText("Error: VIN # already exists! Enter another.");
                // If not, it adds all the data
            } else {
                // Adding all info to the list
            	vehicles.add(new Vehicle(inputs[0].getText(), //VIN
                        inputs[1].getText(),                //type
                        inputs[2].getText(),                //brand
                        inputs[3].getText(),  				//model
                        inputs[4].getText(),  				//color
                        Integer.parseInt(inputs[5].getText()),  //year
                        new Date(inputs[6].getText())                   //rDate
                        ));
                display.setText("VIN#" + inputs[0].getText() + " added to record(s).");
                // Loop to set all text fields to null
                for (int i = 0; i < inputs.length; i++) {
                    inputs[i].setText(null);
                }
            }
        }
    } else if (event.getSource().equals(buttons[1])) {
        // Loop to search list for requested VIN#
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            // If the request is found, it removes record
            if (inputs[0].getText() == vehicles.get(i).getVin()) {
                display.setText("VIN#" + vehicles.get(i).getVin() + " record has been removed!");
                vehicles.remove(i);
                break;
                // If not, the VIN# does not exist
            } else {
                display.setText("Error: VIN# does not exist! Try again.");
            }
        }
    } else {
        // Resets text area and lists all records
        display.setText(null);
        for (int i = 0; i < vehicles.size(); i++) {
            display.append(div + "\nVIN: " + vehicles.get(i).getVin() + "\nType: " + vehicles.get(i).getType()
                    + "\nBrand: " + vehicles.get(i).getBrand() + "\nModel: " + vehicles.get(i).getModel()
                    + "\nColor: " + vehicles.get(i).getColor()+ "\nYear: " + vehicles.get(i).getYear()
                    + "\nRegistration Date: " + vehicles.get(i).getRegistrationDate() + "\n");
        	}
    	}
	}

}