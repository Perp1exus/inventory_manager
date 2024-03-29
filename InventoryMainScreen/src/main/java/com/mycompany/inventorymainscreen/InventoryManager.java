
package com.mycompany.inventorymainscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryManager extends JFrame {
    private JTable table;
    private DefaultTableModel model;   
    
     public InventoryManager() {

        setTitle("Manager Dashboard View");
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

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add a new row to the table with default values
                model.addRow(new Object[] { "", "" });
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove selected row(s) from the table
                int[] selectedRows = table.getSelectedRows();
            
            try {
            Database db = new Database();
            Connection connection = db.check();
            
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int selectedRow = selectedRows[i];
                String name = model.getValueAt(selectedRow, 0).toString(); 
                String quantity = model.getValueAt(selectedRow, 1).toString();
                String price = model.getValueAt(selectedRow, 2).toString();
                
                String deleteQuery = "DELETE FROM tableinventory WHERE name = ? AND quantity = ? AND price = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setString(1, name);
                deleteStatement.setString(2, quantity);
                deleteStatement.setString(3, price);
                
                int rowsDeleted = deleteStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    model.removeRow(selectedRow); // Remove row from the table if deletion is successful
                    JOptionPane.showMessageDialog(null, "Row deleted successfully.");
                }
                
                deleteStatement.close();
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle potential errors in a better way in your actual application
        }
    }
});
        
                    
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the selected row in the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = model.getValueAt(selectedRow, 0).toString();
                    String quantity = model.getValueAt(selectedRow, 1).toString();
                    String price = model.getValueAt(selectedRow, 2).toString();

                    // Perform update operations based on the selected row's data
                    // For example, update data in a database
                    // Your logic here...


                    try {
                            Database db = new Database();
                        Connection connection = db.check();
                        // Create a PreparedStatement to execute an update query
                        String sql = ("INSERT INTO tableinventory(name, quantity, price)" + "VALUES(?, ?, ?)");
                        String sql1 = ("INSERT INTO tableseller(name, quantity, price)" + "VALUES(?, ?, ?)");
                        PreparedStatement statement = connection.prepareStatement(sql);
                        PreparedStatement statement1 = connection.prepareStatement(sql1);
                        // Set parameters for the PreparedStatement
                        statement.setString(1, name);
                        statement.setString(2, quantity);
                        statement.setString(3, price);
                        
                        statement1.setString(1, name);
                        statement1.setString(2, quantity);
                        statement1.setString(3, price);
                        
                        statement.executeUpdate();
                        statement1.executeUpdate();
                        System.out.println("\nAdded Successfully in Manager Database");
                        connection.close();
                        // Execute the update query
                        int rowsUpdated = statement.executeUpdate();
                        int rowsUpdated1 = statement1.executeUpdate();
                        

                        if (rowsUpdated > 0 || rowsUpdated1 > 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Row updated: Name=" + name + ", Price=" + price + ", Quantity=" + quantity);
                        }
                        // Close the statement
                        statement.close();
                    } catch (SQLException ex) {
                        System.out.println("Updated into the Manager Database"); // Handle potential errors in a better
                                                                                 // way in your actual application
                    }
                    JOptionPane.showMessageDialog(null,
                            "Updated row: name=" + name + ", Price=" + price + "Quantity=" + quantity);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
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
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, "South");
        add(backButton, "North");

        setLocationRelativeTo(null);
        setVisible(true);
        
        loadDataFromDatabase();
    }
        private void loadDataFromDatabase() {
        try {
                Database db = new Database();
            Connection connection = db.check();
            
            String query = "SELECT * FROM tableinventory"; // Replace 'tableinventory' with your table name
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

