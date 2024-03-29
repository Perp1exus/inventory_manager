package com.mycompany.inventorymainscreen;
import java.awt.*;
import javax.swing.*;

public class InventoryMainScreen {
   
        static JFrame frame = new JFrame();
        static JPanel homePanel = new JPanel();
        static JPanel customerPanel = new JPanel(new BorderLayout());
        static JPanel sellerPanel = new JPanel(new BorderLayout());
        static JPanel managerPanel = new JPanel();

        static JButton home = new JButton();
        static JButton customer = new JButton();
        static JButton seller = new JButton();
        static JButton manager = new JButton();
        static JButton exit = new JButton();

        static JPanel optionPanel = new JPanel();

    public static void main(String[] args) {
        
          
                Functions functions = new Functions(false,optionPanel);
                ImageIcon homeIcon = new ImageIcon(
                                new ImageIcon("Assets//Home.png").getImage().getScaledInstance(50, 50,
                                                Image.SCALE_SMOOTH));

                ImageIcon customerIcon = new ImageIcon(
                                new ImageIcon("Assets//Customer.png").getImage().getScaledInstance(50, 50,
                                                Image.SCALE_SMOOTH));

                ImageIcon sellerIcon = new ImageIcon(
                                new ImageIcon("Assets//Seller.png").getImage().getScaledInstance(50, 50,
                                                Image.SCALE_SMOOTH));

                ImageIcon managerIcon = new ImageIcon(
                                new ImageIcon("Assets//Manager.png").getImage().getScaledInstance(50, 50,
                                                Image.SCALE_SMOOTH));

                ImageIcon exitIcon = new ImageIcon(
                                new ImageIcon("Assets//Exit.png").getImage().getScaledInstance(50, 50,
                                                Image.SCALE_SMOOTH));

                // MAIN FRAME

                frame.getContentPane().setBackground(Color.white);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setUndecorated(true);
                frame.setVisible(true);
                frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

                // PANELS
                optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                optionPanel.setBackground(new Color(177, 148, 148, 255));
                optionPanel.setPreferredSize(new Dimension(80, 1000));
                optionPanel.setMaximumSize(new Dimension(80, 1000));
                optionPanel.add(home);
                optionPanel.add(customer);
                optionPanel.add(seller);
                optionPanel.add(manager);
                optionPanel.add(Box.createVerticalGlue());
                optionPanel.add(exit);
                frame.getContentPane().add(optionPanel);

                // BUTTONS
                home.setName("Home");
                home.setPreferredSize(new Dimension(80, 100));
                home.setOpaque(true);
                home.setContentAreaFilled(true);
                home.setFocusPainted(false);
                home.setBorderPainted(false);
                home.setIcon(homeIcon);
                functions.optionPanelButton(home);

                customer.setName("Customer");
                customer.setPreferredSize(new Dimension(80, 100));
                customer.setOpaque(true);
                customer.setContentAreaFilled(true);
                customer.setFocusPainted(false);
                customer.setBorderPainted(false);
                customer.setIcon(customerIcon);
                functions.optionPanelButton(customer);

                seller.setName("Seller");
                seller.setPreferredSize(new Dimension(80, 100));
                seller.setOpaque(true);
                seller.setContentAreaFilled(true);
                seller.setFocusPainted(false);
                seller.setBorderPainted(false);
                seller.setIcon(sellerIcon);
                functions.optionPanelButton(seller);

                manager.setName("Manager");
                manager.setPreferredSize(new Dimension(80, 100));
                manager.setOpaque(true);
                manager.setContentAreaFilled(true);
                manager.setFocusPainted(false);
                manager.setBorderPainted(false);
                manager.setIcon(managerIcon);
                functions.optionPanelButton(manager);

                exit.setName("Exit");
                exit.setPreferredSize(new Dimension(80, 80));
                exit.setOpaque(true);
                exit.setContentAreaFilled(true);
                exit.setFocusPainted(false);
                exit.setBorderPainted(false);
                exit.setIcon(exitIcon);
                functions.optionPanelButton(exit);

                frame.getContentPane().add(homePanel);
                frame.getContentPane().add(customerPanel);
                frame.getContentPane().add(sellerPanel);
                frame.getContentPane().add(managerPanel);

                homePanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Load the background image for homePanel
                ImageIcon backgroundImage = new ImageIcon("Assets//BackgroundImage.png");
                JLabel backgroundLabel = new JLabel(backgroundImage);
                backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

                homePanel.add(backgroundLabel);
                homePanel.setOpaque(false);
                homePanel.setVisible(true);

                customerPanel.setVisible(false);
                sellerPanel.setVisible(false);
                managerPanel.setVisible(false);
                
                       
                

        }
}
   
    

