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

class HomepageClass extends JFrame{
	private JFrame HomepageForm;
	private JButton[] buttonArray;
	private JButton btnPlaceOrder;

	public HomepageClass(){
		HomepageForm=new JFrame("Fashion Shop");
		HomepageForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		HomepageForm.setLocationRelativeTo(null);
		HomepageForm.setSize(500, 500);
		HomepageForm.setLayout(new BorderLayout());
		HomepageForm.setResizable(false);

		JLabel lblFashionShop=new JLabel("Fashion Shop");
		lblFashionShop.setFont(new Font ("Arial",Font.BOLD,40));
		lblFashionShop.setHorizontalAlignment(JLabel.CENTER);
		lblFashionShop.setForeground(Color.white);
		lblFashionShop.setOpaque(true);
		lblFashionShop.setBackground(Color.blue);

		JPanel allButtons=new JPanel();
		allButtons.setBounds(0, 0, 10, 500);
		allButtons.setPreferredSize(new Dimension(225, 500));
		allButtons.setLayout(new BorderLayout());

		JPanel buttonPanel=new JPanel();
		buttonPanel.setBounds(0, 0, 10, 500);
		buttonPanel.setPreferredSize(new Dimension(225, 500));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		allButtons.add(buttonPanel,BorderLayout.CENTER);		
		
		buttonArray=new JButton[4];
		String[] name={"Search","Status","Reports","Delete"};

		for (int i = 0; i < name.length; i++) {
			buttonArray[i]=new JButton(name[i]);
			buttonArray[i].setFocusable(false);
			buttonArray[i].setFont(new Font(null, 1, 12));
			buttonArray[i].setHorizontalAlignment(SwingConstants.CENTER);
			buttonArray[i].setPreferredSize(new Dimension(150,35));
			buttonPanel.add(buttonArray[i]);
		}
		
		JPanel PlaceOrderPanel=new JPanel();
		PlaceOrderPanel.setPreferredSize(new Dimension(225, 135));
		btnPlaceOrder=new JButton("Place Order");
		btnPlaceOrder.setPreferredSize(new Dimension(150,50));
		btnPlaceOrder.setBackground(Color.CYAN);
		btnPlaceOrder.setFocusable(false);
		btnPlaceOrder.setFont(new Font("Arial", Font.BOLD, 20));
		btnPlaceOrder.setBorderPainted(false);
		PlaceOrderPanel.add(btnPlaceOrder);
		allButtons.add(PlaceOrderPanel,BorderLayout.SOUTH);

		buttonArray[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent SearchOption){
				String[] options={"Search Customer","Search Order","Cancel"};
        int choice=JOptionPane.showOptionDialog(HomepageForm, "Please Select the Option", "Search Options ",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,  options, options[0]);

        if (choice == 0) {
            new SearchCustomer();
           
        } else if (choice == 1) {
           	new SearchOrder();
        } else if(choice==2){
			dispose(); 
		}
			}
		});

		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent PlacingOrder){
				new PlaceOrderClass();
			}
		});

		buttonArray[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Status){
				new ChangeOrderStatus();
			}
		});

		buttonArray[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Status){
				new RemoveOrder();
			}
		});

		JPanel imagPanel=new JPanel();
		imagPanel.setPreferredSize(new Dimension(275,400));
		imagPanel.setLayout(new BorderLayout());

		ImageIcon fashionimage=new ImageIcon("tshirt.png");
		
		JLabel imagLabel=new JLabel();
		imagLabel.setHorizontalAlignment(imagLabel.CENTER);
		imagLabel.setVerticalAlignment(imagLabel.CENTER);
		imagLabel.setIcon(fashionimage);
		imagPanel.add(imagLabel);

		HomepageForm.add(lblFashionShop,BorderLayout.NORTH);
		HomepageForm.add(allButtons,BorderLayout.WEST);
		HomepageForm.add(imagPanel,BorderLayout.CENTER);
		HomepageForm.setVisible(true);
	}	

	public static void main(String args[]){
		new HomepageClass();
	}
}

