
package com.mycompany.inventorymainscreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryLogin extends JFrame {
     private JTextField usernameField;
     private JPasswordField passwordField;
     
    public InventoryLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        User authenticatedUser = AuthenticationService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            dispose(); // Close the login screen
            showDashboard(authenticatedUser);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Authentication Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDashboard(User user) {
        // Implement a Swing main dashboard with buttons/menu based on the user's role
        // Each button should trigger specific actions (update, add, remove for inventory_manager, etc.)
        // create a new JFrame or switch to a new JPanel for the dashboard  
        if(user != null){
        String role = user.getRole();
        
        if("inventory_manager".equals(role)){
           new InventoryManager(); 
         }
        else if("seller".equals(role)){
            new SellerInventory();
         }
        else if("customer".equals(role)){
            new CustomerView();
         }
        }    
        
    }
}
        
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new InventoryLogin();              
//            }
//        });
//    }
//}
