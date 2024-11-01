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
import java.io.*;

class RemoveOrder extends JFrame {
    private JFrame RemoveOrderFrame;
    private JButton btnBack;
    private JButton btnSearch, btnRemove;
    private JLabel lblCustomerIdOut, lblSizeOut, lblQTYout, lblAmountOut, lblStatusOut;

    private JTextField txtOrderId;

    RemoveOrder() {
        RemoveOrderFrame = new JFrame("Remove Form");
        RemoveOrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RemoveOrderFrame.setLocationRelativeTo(null);
        RemoveOrderFrame.setSize(500, 500);
        RemoveOrderFrame.setLayout(new BorderLayout());
        RemoveOrderFrame.setResizable(false);

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
        btnRemove = new JButton("Remove");
        btnRemove.setFont(new Font("Arial", Font.BOLD, 20));
        btnRemove.setBackground(Color.blue);
        btnRemove.setForeground(Color.white);
        btnRemove.setFocusable(false);
        SouthPanel.add(btnRemove);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                for (int i = 0; i < OrdersDBConnection.getOrdersList().size(); i++) {
                    OrderOb orderob = OrdersDBConnection.getOrdersList().getIndex(i);
                    if (txtOrderId.getText().equals(orderob.getOrderId())) {
                        lblCustomerIdOut.setText(orderob.getPhoneNumber());
                        lblSizeOut.setText(orderob.getTShirtSize());
                        lblQTYout.setText(orderob.getQuantity() + "");
                        lblAmountOut.setText(orderob.getAmount() + "");
                        lblStatusOut.setText(orderob.getIntStatus()+"");

                    }
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String Orderid=txtOrderId.getText();
				int YN=JOptionPane.showConfirmDialog(RemoveOrderFrame,"Do you want to delete this order?", "Delete Confimration", JOptionPane.YES_NO_OPTION);
                try {
                    if(YN==JOptionPane.YES_OPTION){
                        boolean isDelete=OrdersDBConnection.getOrdersList().deleteOrder(Orderid);
                    if(isDelete==true){
                        JOptionPane.showMessageDialog(RemoveOrderFrame,"OrderDeleted");
                    } 
                    }
                } catch (IOException e) {
                }
                
                // String Phone=lblCustomerIdOut.getText();
				// String Size=lblSizeOut.getText();
                // int QTY=Integer.parseInt(lblQTYout.getText());
				// double Amount=Double.parseDouble(lblAmountOut.getText());
                // int Status=Integer.parseInt(lblStatusOut.getText());
				// OrderOb ob=new OrderOb(Orderid,Phone,Size, QTY,Amount,Status);
                
				// int YN=JOptionPane.showConfirmDialog(RemoveOrderFrame,"Do you want to delete this order?", "Delete Confimration", JOptionPane.YES_NO_OPTION);

                // if(YN==JOptionPane.YES_OPTION){
                //     boolean isDelete=OrdersDBConnection.getOrdersList().remove(OrdersDBConnection.getOrdersList().search(ob));
				// if(isDelete==true){
				// 	JOptionPane.showMessageDialog(RemoveOrderFrame,"OrderDeleted");
				// } 
                // }
            }
        });
        
        

        RemoveOrderFrame.add(NorthPanel, BorderLayout.NORTH);
        RemoveOrderFrame.add(EastPanel, BorderLayout.EAST);
        RemoveOrderFrame.add(WestPanel, BorderLayout.WEST);
        RemoveOrderFrame.add(SouthPanel, BorderLayout.SOUTH);
        RemoveOrderFrame.add(CenterPanel, BorderLayout.CENTER);
        RemoveOrderFrame.setVisible(true);
    }
}
