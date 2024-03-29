
package com.mycompany.inventorymainscreen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Functions {
    private boolean isSelected;
    private JPanel optionPanel;

    public Functions(boolean isSelected, JPanel optionPanel) {
        this.isSelected = isSelected;
        this.optionPanel = optionPanel;
    }
     public void optionPanelButton(final JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(button);
                handleButtonClick(button);
            }
        });
     }

    private void setSelectedButton(JButton selectedButton) {
        if (isSelected) {
            // Reset background color of all buttons
            for (Component comp : InventoryMainScreen.optionPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    button.setBackground(null);
                }
            }

            // Set background color for the selected button
            selectedButton.setBackground(new Color(248, 250, 229, 255));
        }
    }

    private void handleButtonClick(JButton button) {
        String buttonName = button.getName();

        switch (buttonName) {

            // Add cases for other buttons and define actions
            // case "Home":
            // // Do something for the Home button
            // break;
            case "Customer":
                new InventoryLogin();
                break;
            case "Seller":
                new InventoryLogin();
                break;
            case "Manager":
                new InventoryLogin();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                // Handle other buttons if needed
                break;
        }
    }  
        
}

