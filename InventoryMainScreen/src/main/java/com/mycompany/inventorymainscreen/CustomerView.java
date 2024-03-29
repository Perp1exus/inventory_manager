
package com.mycompany.inventorymainscreen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    
    public CustomerView(){
        setTitle("Customer Dashboard View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price");

        // Create the table using the DefaultTableModel
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        
        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the selected row in the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = model.getValueAt(selectedRow, 0).toString();
                    String price = model.getValueAt(selectedRow, 1).toString();
                    String quantity = model.getValueAt(selectedRow, 2).toString();
                    // Perform update operations based on the selected row's data
                    // For example, update data in a database
                    // Your logic here...
                    
                                        Database db = new Database();
                    Connection connection = db.check();
                try {
                // Create a PreparedStatement to execute an update query
                String sql=("INSERT INTO customercart(name, quantity, price)"+"VALUES(?, ?, ?)");
                PreparedStatement statement = connection.prepareStatement(sql);
                // Set parameters for the PreparedStatement
                    statement.setString(1, name);
                    statement.setString(2, quantity);
                    statement.setString(3, price);
                    
            
            statement.executeUpdate();
            System.out.println("\nAdded Successfully in Customer Database!");
            connection.close();
                // Execute the update query
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Row updated: Name=" + name + ", Price=" + price + ", Quantity=" + quantity);
                }           
                // Close the statement
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Updated into the Seller Database!"); // Handle potential errors in a better way in your actual application
            }  
                    
   
                    JOptionPane.showMessageDialog(null, "Updated row: name=" + name + ", Price=" + price + "Quantity="+ quantity);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to Order.");
                }
            }
        });
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                InventoryLogin inventoryLogin = new InventoryLogin();
                inventoryLogin.setVisible(true);
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(orderButton);        
        add(buttonPanel, "South");
        add(backButton,"North");
           
        setLocationRelativeTo(null);
        setVisible(true);
        
        loadDataFromDatabase();
    }
    
            private void loadDataFromDatabase() {
        try {
                Database db = new Database();
                Connection connection = db.check();
                
            String query = "SELECT * FROM tableseller"; // Replace 'tableinventory' with your table name
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear existing rows in the table
            model.setRowCount(0);

            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String quantity = resultSet.getString("quantity");
                String price = resultSet.getString("price");

                model.addRow(new Object[]{name, quantity, price});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential errors in a better way in your actual application
        }
    }
    
}

