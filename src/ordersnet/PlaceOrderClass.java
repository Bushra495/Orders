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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

class PlaceOrderClass extends JFrame{
	private JFrame PlaceOrderFrame;
	private JButton btnBack;
	private JButton btnPlace;
    private JTextField txtcustomerId, txtSize, txtQTY;
	String txtcustomerIdinput;
	String txtSizeinput;
	JLabel lblOrderIdoutput;
	int txtIntQTYinput;
	JLabel amountcalLabel;

	PlaceOrderClass() {
	PlaceOrderFrame=new JFrame("Place Order");
	PlaceOrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	PlaceOrderFrame.setLocationRelativeTo(null);
	PlaceOrderFrame.setSize(500, 500);
	PlaceOrderFrame.setLayout(new BorderLayout());
	PlaceOrderFrame.setResizable(false);
	
	JPanel NorthPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	NorthPanel.setPreferredSize(new Dimension(500,70));
	btnBack=new JButton("Back");
	btnBack.setFont(new Font("Arial",Font.BOLD,20));
	btnBack.setBackground(Color.RED);
	btnBack.setForeground(Color.white);
	btnBack.setFocusable(false);
	NorthPanel.add(btnBack);

	JPanel SouthPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	SouthPanel.setPreferredSize(new Dimension(500,50));
	btnPlace=new JButton("Place");
	btnPlace.setFont(new Font("Arial",Font.BOLD,20));
	btnPlace.setBackground(Color.cyan);
	btnPlace.setForeground(Color.white);
	btnPlace.setFocusable(false);
	SouthPanel.add(btnPlace);
	

	JPanel WestPanel=new JPanel(new BorderLayout());
	
	JPanel WestPaneltoEast=new JPanel();
	WestPaneltoEast.setPreferredSize(new Dimension(200, 300));
	
	JPanel TopWestPanel=new JPanel(new BorderLayout());
	TopWestPanel.setPreferredSize(new Dimension(110, 60));
			
	JLabel lblOrderId=new JLabel("Order ID : ");
	lblOrderId.setFont(new Font("Arial",Font.BOLD,15));
	lblOrderId.setHorizontalAlignment(JLabel.LEFT);
	
	TopWestPanel.add(lblOrderId,BorderLayout.CENTER);
	
	WestPanel.add(TopWestPanel,BorderLayout.NORTH);
	
	JPanel BottomWestPanel=new JPanel(new GridLayout(4, 1));
	BottomWestPanel.setPreferredSize(new Dimension(110,200));
	

	JLabel lblCustomerId=new JLabel("Customer ID : ");
	lblCustomerId.setFont(new Font("Arial",Font.BOLD,15));
	BottomWestPanel.add(lblCustomerId);
	lblCustomerId.setHorizontalAlignment(JLabel.LEFT);
	
	
	JLabel lblSize=new JLabel("Size : ");
	lblSize.setFont(new Font("Arial",Font.BOLD,15));
	BottomWestPanel.add(lblSize);
	lblSize.setHorizontalAlignment(JLabel.LEFT);
	
	JLabel lblQTY=new JLabel("QTY : ");
	lblQTY.setFont(new Font("Arial",Font.BOLD,15));
	BottomWestPanel.add(lblQTY);
	lblQTY.setHorizontalAlignment(JLabel.LEFT);
	
	JLabel lblAmount=new JLabel("Amount : ");
	lblAmount.setFont(new Font("Arial",Font.BOLD,15));
	BottomWestPanel.add(lblAmount);
	lblAmount.setHorizontalAlignment(JLabel.LEFT);

	JPanel textPanel=new JPanel(new BorderLayout());
	
	textPanel.setPreferredSize(new Dimension(110, 300));
	
	JPanel orderIDOutputpanel=new JPanel(new BorderLayout());
	orderIDOutputpanel.setPreferredSize(new Dimension(110,70));
	lblOrderIdoutput=new JLabel();////
	
	try{
	lblOrderIdoutput.setText(GenerateId());
	lblOrderIdoutput.setFont(new Font("",1,15));
	lblOrderIdoutput.setPreferredSize(new Dimension(110, 70));
	//lblOrderIdoutput.setBackground(Color.green);//
	lblOrderIdoutput.setOpaque(true);//
	orderIDOutputpanel.add(lblOrderIdoutput,BorderLayout.CENTER);
	textPanel.add(orderIDOutputpanel,BorderLayout.NORTH);
	}catch(IOException ex){}
	
	JPanel textPanel2=new JPanel(new GridLayout(4, 1));
	textPanel2.setPreferredSize(new Dimension(110,200));
	
	JPanel textPanel3=new JPanel(new BorderLayout());
	textPanel.add(textPanel3,BorderLayout.CENTER);

	txtcustomerId=new JTextField(12);
	txtcustomerId.setInputVerifier(new InputVerifier() {
		@Override
		public boolean verify(JComponent input){
			String phone=txtcustomerId.getText();
			if(phone.charAt(0)=='0'&&phone.length()==10){
				return true;
			}else{
				JOptionPane.showMessageDialog(input, "Invalid input ","Invalid Input", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	});

	txtcustomerId.setFont(new Font("",1,15));
	txtcustomerId.setPreferredSize(new Dimension(200, 30));
	txtcustomerIdinput=txtcustomerId.getText();
	
	
	JPanel txtcustomerPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	txtcustomerPanel.setPreferredSize(new Dimension(200, 30));
	txtcustomerPanel.add(txtcustomerId);
	
	textPanel2.add(txtcustomerPanel);
	
	txtSize=new JTextField(12);
	txtSize.setText("XS");
	txtSize.setInputVerifier(new InputVerifier() {
		@Override
		public boolean verify(JComponent input){
			String text=txtSize.getText();
			for (String string : OrdersDBConnection.getOrdersList().SizesArray) {
				if(text.equals(string)){
					return true;
				}
			}
			JOptionPane.showMessageDialog(input, "Please enter a valid size (XS, S, M, L, XL, XXL).","Invalid Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	});

	txtSize.setFont(new Font("",1,15));
	txtSize.setPreferredSize(new Dimension(200, 30));
	txtSizeinput=txtSize.getText();

	JPanel txtSizePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel sizesLabel=new JLabel("(XS/S/M/L/XL/XXL)");
	txtSizePanel.setPreferredSize(new Dimension(200, 30));
	txtSizePanel.add(txtSize);
	txtSizePanel.add(sizesLabel);
	textPanel2.add(txtSizePanel);
	
	txtQTY=new JTextField(12);
	txtQTY.setText("0");
	txtQTY.setFont(new Font("",1,15));
	txtQTY.setPreferredSize(new Dimension(200, 30));
	JPanel txtQTYPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	txtQTYPanel.setPreferredSize(new Dimension(200, 30));
	txtQTYPanel.add(txtQTY);
	textPanel2.add(txtQTYPanel);
	
	txtQTY.setInputVerifier(new InputVerifier() {
		@Override
		public boolean verify(JComponent input){
			int quantity=Integer.parseInt(txtQTY.getText());
			if(quantity>=0){
				return true;
			}else{
				JOptionPane.showMessageDialog(input, "Please enter a valid input ","Invalid Input", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	});

	amountcalLabel=new JLabel("0");//
	amountcalLabel.setText(String.valueOf((Integer.parseInt(txtQTY.getText())*OrdersDBConnection.getOrdersList().UnitPriceTShirtArray[(OrdersDBConnection.getOrdersList().SizeChart(txtSize.getText()))])));
	
	amountcalLabel.setFont(new Font("",1,15));
	amountcalLabel.setPreferredSize(new Dimension(200, 30));
	//amountcalLabel.setBackground(Color.RED);//
	amountcalLabel.setOpaque(true);//
	
	KeyAdapter keyListener =new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent event){
			updateAmount();
		}
	};

	txtQTY.addKeyListener(keyListener);

	textPanel2.add(amountcalLabel);
	
	textPanel3.add(textPanel2,BorderLayout.NORTH);
	//textPanel.setBackground(Color.blue);//
	
	WestPaneltoEast.add(TopWestPanel,BorderLayout.NORTH);
	WestPaneltoEast.add(BottomWestPanel,BorderLayout.SOUTH);
	
	//WestPaneltoEast.setBackground(Color.CYAN);//
	
	WestPanel.add(WestPaneltoEast,BorderLayout.EAST);
	

	btnPlace.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			try{
			String OrderId=GenerateId();
			String CustomerId=txtcustomerId.getText();
			String Size=txtSize.getText();
			int Quantity=Integer.parseInt(txtQTY.getText());
			double Amount = Double.parseDouble(amountcalLabel.getText());
			OrderOb Orderob = new OrderOb(OrderId, CustomerId, Size, Quantity, Amount,0);
			OrdersDBConnection.getOrdersList().add(Orderob);
			///////
			
			JOptionPane.showMessageDialog(PlaceOrderFrame, "Order Placed", "Information", JOptionPane.INFORMATION_MESSAGE);//new
			lblOrderIdoutput.setText(GenerateId());
			txtcustomerId.setText("");
			txtSize.setText("XS");
			txtQTY.setText("0");
			amountcalLabel.setText("0");
			}catch(IOException ex){}
		}
	});
	
	
	PlaceOrderFrame.add(NorthPanel,BorderLayout.NORTH);
	PlaceOrderFrame.add(SouthPanel,BorderLayout.SOUTH);
	PlaceOrderFrame.add(WestPanel,BorderLayout.WEST);
	PlaceOrderFrame.add(textPanel,BorderLayout.CENTER);
	PlaceOrderFrame.setVisible(true);
	}
	private String GenerateId() throws IOException{
		// Scanner scanid=new Scanner(new File("OrdersList.txt"));
		// String line=null;
		// while (scanid.hasNext()) {
		// 	line=scanid.nextLine();
		// }
		// if(line==null){
		// 	return "ODR#0001";
		// }
		// String lastId=line.substring(4,8);
		// int lastIdNum=Integer.parseInt(lastId);
		// lastIdNum++;
		// String newId=String.format("ODR#%04d",lastIdNum);
		// return newId;
			
		if(OrdersDBConnection.getOrdersList().size()==0){
			return "ODR#0001";
		}
		String lastId=OrdersDBConnection.getOrdersList().getIndex(OrdersDBConnection.getOrdersList().size()-1).getOrderId();
		String lastNumber=lastId.substring(4);	
		int lastNo=Integer.parseInt(lastNumber);
		String newId=String.format("ODR#%04d",lastNo+1);
		return newId;
	}

	private void updateAmount(){
		try {
			double qty=Double.parseDouble(txtQTY.getText());

			double amount=qty*OrdersDBConnection.getOrdersList().UnitPriceTShirtArray[(OrdersDBConnection.getOrdersList().SizeChart(txtSize.getText()))];
			amountcalLabel.setText(String.valueOf(amount));
		} catch (Exception e) {
			amountcalLabel.setText("Invalid input");
		}
	}
}
	

