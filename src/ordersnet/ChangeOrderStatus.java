/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordersnet;

/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class ChangeOrderStatus extends JFrame {
    private JFrame ChangeOrderStatusFrame;
    private JButton btnBack;
    private JButton btnSearch, btnChangeStatus;
    private JLabel lblCustomerIdOut, lblSizeOut, lblQTYout, lblAmountOut, lblStatusOut;

    private JTextField txtOrderId;

    ChangeOrderStatus() {
        ChangeOrderStatusFrame = new JFrame("Status Form");
        ChangeOrderStatusFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ChangeOrderStatusFrame.setLocationRelativeTo(null);
        ChangeOrderStatusFrame.setSize(500, 500);
        ChangeOrderStatusFrame.setLayout(new BorderLayout());
        ChangeOrderStatusFrame.setResizable(false);

        JPanel NorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        NorthPanel.setPreferredSize(new Dimension(500, 150));
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.white);
        btnBack.setFocusable(false);
        NorthPanel.add(btnBack);

        JPanel NSouthPanel = new JPanel(new FlowLayout());
        NSouthPanel.setPreferredSize(new Dimension(500, 100));
        JPanel CenterPanel = new JPanel();

        JLabel lblOrderId = new JLabel("Enter Order ID : ");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 15));
        lblOrderId.setHorizontalAlignment(JLabel.LEFT);

        txtOrderId = new JTextField(12);
        /*
         * txtOrderId.setInputVerifier(new InputVerifier() {
         * 
         * @Override
         * public boolean verify(JComponent input) {
         * String OID = txtOrderId.getText();
         * 
         * for (int i = 0; i < orders.ArraySize(); i++) {
         * OrderOb orderob = orders.getIndex(i);
         * if (OID.equals(orderob.getOrderId())) {
         * return true;
         * }
         * }
         * 
         * }
         * });
         */

        txtOrderId.setFont(new Font("", 1, 15));
        txtOrderId.setPreferredSize(new Dimension(190, 30));

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 20));
        btnSearch.setBackground(Color.CYAN);
        btnSearch.setForeground(Color.white);
        btnSearch.setFocusable(false);

        NSouthPanel.add(lblOrderId);
        NSouthPanel.add(txtOrderId);
        NSouthPanel.add(btnSearch);
        NorthPanel.add("South", NSouthPanel);

        lblCustomerIdOut = new JLabel("");
        lblCustomerIdOut.setPreferredSize(new Dimension(190, 50));
        lblSizeOut = new JLabel("");
        lblSizeOut.setPreferredSize(new Dimension(190, 50));
        lblQTYout = new JLabel("");
        lblQTYout.setPreferredSize(new Dimension(190, 50));
        lblAmountOut = new JLabel("");
        lblAmountOut.setPreferredSize(new Dimension(190, 50));
        lblStatusOut = new JLabel("");
        lblStatusOut.setPreferredSize(new Dimension(190, 50));

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                for (int i = 0; i < OrdersDBConnection.getOrdersList().size(); i++) {
                    OrderOb orderob = OrdersDBConnection.getOrdersList().getIndex(i);
                    if (txtOrderId.getText().equals(orderob.getOrderId())) {
                        lblCustomerIdOut.setText(orderob.getPhoneNumber());
                        lblSizeOut.setText(orderob.getTShirtSize());
                        lblQTYout.setText(orderob.getQuantity() + "");
                        lblAmountOut.setText(orderob.getAmount() + "");
                        lblStatusOut.setText(orderob.getStatus());

                    }
                }
            }
        });

        JLabel lblCustomerId = new JLabel("Customer ID : ");
        lblCustomerId.setFont(new Font("Arial", Font.BOLD, 15));
        lblCustomerId.setPreferredSize(new Dimension(195, 50));
        JLabel lblSize = new JLabel("Size : ");
        lblSize.setFont(new Font("Arial", Font.BOLD, 15));
        lblSize.setPreferredSize(new Dimension(195, 50));
        JLabel lblQTY = new JLabel("QTY : ");
        lblQTY.setFont(new Font("Arial", Font.BOLD, 15));
        lblQTY.setPreferredSize(new Dimension(190, 50));
        JLabel lblAmount = new JLabel("Amount : ");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmount.setPreferredSize(new Dimension(190, 50));
        JLabel lblStatus = new JLabel("Status : ");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 15));
        lblStatus.setPreferredSize(new Dimension(190, 50));
        JPanel Grid1Panel = new JPanel(new GridLayout(5, 2));

        Grid1Panel.add(lblCustomerId);
        Grid1Panel.add(lblCustomerIdOut);

        Grid1Panel.add(lblSize);
        Grid1Panel.add(lblSizeOut);

        Grid1Panel.add(lblQTY);
        Grid1Panel.add(lblQTYout);

        Grid1Panel.add(lblAmount);
        Grid1Panel.add(lblAmountOut);

        Grid1Panel.add(lblStatus);
        Grid1Panel.add(lblStatusOut);

        CenterPanel.add(Grid1Panel);

        JPanel EastPanel = new JPanel();
        EastPanel.setPreferredSize(new Dimension(50, 500));
        JPanel WestPanel = new JPanel();
        WestPanel.setPreferredSize(new Dimension(50, 500));

        JPanel SouthPanel = new JPanel();
        btnChangeStatus = new JButton("Change Status");
        btnChangeStatus.setFont(new Font("Arial", Font.BOLD, 20));
        btnChangeStatus.setBackground(Color.blue);
        btnChangeStatus.setForeground(Color.white);
        btnChangeStatus.setFocusable(false);
        SouthPanel.add(btnChangeStatus);

        btnChangeStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                for (int i = 0; i < OrdersDBConnection.getOrdersList().size(); i++) {
                    OrderOb orderob = OrdersDBConnection.getOrdersList().getIndex(i);
                    if (txtOrderId.getText().equals(orderob.getOrderId())) {
                        if (orderob.getIntStatus() == OrdersDBConnection.getOrdersList().PROCESSING) {
                            String[] options = { "Delivering", "Delivered" };
                            int choice = JOptionPane.showOptionDialog(ChangeOrderStatusFrame,
                                    "Please Select the Option", "Status ", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            if (choice == 0) {
                                
                                orderob.setStatus(OrdersDBConnection.getOrdersList().DELIVERING);
                                JOptionPane.showMessageDialog(ChangeOrderStatusFrame, "Updated !");
                                lblCustomerIdOut.setText(orderob.getPhoneNumber());
                                lblSizeOut.setText(orderob.getTShirtSize());
                                lblQTYout.setText(orderob.getQuantity() + "");
                                lblAmountOut.setText(orderob.getAmount() + "");
                                lblStatusOut.setText(orderob.getStatus());// }
                            } else if (choice == 1) {
                               
                                orderob.setStatus(OrdersDBConnection.getOrdersList().DELIVERED);
                                JOptionPane.showMessageDialog(ChangeOrderStatusFrame, "Updated !");
                                lblCustomerIdOut.setText(orderob.getPhoneNumber());
                                lblSizeOut.setText(orderob.getTShirtSize());
                                lblQTYout.setText(orderob.getQuantity() + "");
                                lblAmountOut.setText(orderob.getAmount() + "");
                                lblStatusOut.setText(orderob.getStatus());
    
                            }
                        }
                    } else if (orderob.getIntStatus() == OrdersDBConnection.getOrdersList().DELIVERING) {
                        String[] options = { "Delivered" };
                        int choice = JOptionPane.showOptionDialog(ChangeOrderStatusFrame, "Please Select the Option",
                                "Status ", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (choice == 0) {
                            orderob.setStatus(OrdersDBConnection.getOrdersList().DELIVERED);

                            JOptionPane.showMessageDialog(ChangeOrderStatusFrame, "Updated !");
                            lblCustomerIdOut.setText(orderob.getPhoneNumber());
                            lblSizeOut.setText(orderob.getTShirtSize());
                            lblQTYout.setText(orderob.getQuantity() + "");
                            lblAmountOut.setText(orderob.getAmount() + "");
                            lblStatusOut.setText(orderob.getStatus());
                        }
                    }

                }
            }
        });

        ChangeOrderStatusFrame.add(NorthPanel, BorderLayout.NORTH);
        ChangeOrderStatusFrame.add(EastPanel, BorderLayout.EAST);
        ChangeOrderStatusFrame.add(WestPanel, BorderLayout.WEST);
        ChangeOrderStatusFrame.add(SouthPanel, BorderLayout.SOUTH);
        ChangeOrderStatusFrame.add(CenterPanel, BorderLayout.CENTER);
        ChangeOrderStatusFrame.setVisible(true);
    }
}
