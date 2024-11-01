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
import javax.swing.table.DefaultTableModel;

class SearchCustomer extends JFrame{
	private JFrame SearchCustomerFrame;
	private JButton btnBack;
	private JButton btnSearch;
    private JLabel lblTotalAmount;
    private JTextField txtcustomerId;
	private DefaultTableModel tableModel;
	private JTable customerTable;

	SearchCustomer() {
	SearchCustomerFrame=new JFrame("Search Customer");
	SearchCustomerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	SearchCustomerFrame.setLocationRelativeTo(null);
	SearchCustomerFrame.setSize(500, 500);
	SearchCustomerFrame.setLayout(new BorderLayout());
	SearchCustomerFrame.setResizable(false);
	
	JPanel NorthPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	NorthPanel.setPreferredSize(new Dimension(500,50));
	btnBack=new JButton("Back");
	btnBack.setFont(new Font("Arial",Font.BOLD,20));
	btnBack.setBackground(Color.RED);
	btnBack.setForeground(Color.white);
	btnBack.setFocusable(false);
	NorthPanel.add(btnBack);

	JPanel SouthPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	SouthPanel.setPreferredSize(new Dimension(500,50));
	lblTotalAmount=new JLabel();
	lblTotalAmount.setFont(new Font("Arial",Font.BOLD,20));
	
    SouthPanel.add(lblTotalAmount);
	
	JPanel CenterPanel=new JPanel(new BorderLayout());
	
	JPanel CenterPaneltoNorth=new JPanel(new FlowLayout());
	CenterPaneltoNorth.setPreferredSize(new Dimension(400, 50));
	
	JPanel CenterPaneltoCenter=new JPanel(new BorderLayout());
	CenterPaneltoCenter.setPreferredSize(new Dimension(300, 300));
	
	JLabel lblEnterCustomerId=new JLabel("Enter Customer ID : ");
	lblEnterCustomerId.setFont(new Font("Arial",Font.BOLD,15));
	lblEnterCustomerId.setHorizontalAlignment(JLabel.LEFT);
	
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
	
	String[] tblClmnsearchCustomer={"Size","Quantity","Amount"};
	tableModel=new DefaultTableModel(tblClmnsearchCustomer,0);
	customerTable=new JTable(tableModel);
	JScrollPane tablepan=new JScrollPane(customerTable);
	add("Center",tablepan);
  	btnSearch=new JButton("Search");
	btnSearch.setFont(new Font("Arial",Font.BOLD,20));
	btnSearch.setBackground(Color.CYAN);
	btnSearch.setForeground(Color.white);
	btnSearch.setFocusable(false);
	
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			double totalAmount=0;
			tableModel.setRowCount(0);
			for (int i = 0; i < OrdersDBConnection.getOrdersList().size(); i++) {
				OrderOb orderob=OrdersDBConnection.getOrdersList().getIndex(i); 
				if(txtcustomerId.getText().equals(orderob.getPhoneNumber())){
				String Size=orderob.getTShirtSize();
				int Quantity=orderob.getQuantity();
				double Amount=orderob.getAmount();
				Object[] rowData={Size,Quantity,Amount};
				tableModel.addRow(rowData);

				totalAmount+=Amount; 
				lblTotalAmount.setText("Total:\t\t\t"+totalAmount);
				}
			}
		}
	});
	
	CenterPaneltoNorth.add(lblEnterCustomerId);
	CenterPaneltoNorth.add(txtcustomerId);
	CenterPaneltoNorth.add(btnSearch);

	CenterPaneltoCenter.add(tablepan);
	CenterPanel.add("North",CenterPaneltoNorth);
	CenterPanel.add("Center",CenterPaneltoCenter);

	
	JPanel EastPanel=new JPanel();
    JPanel WestPanel=new JPanel();
        
	SearchCustomerFrame.add(NorthPanel,BorderLayout.NORTH);
	SearchCustomerFrame.add(EastPanel,BorderLayout.EAST);
	SearchCustomerFrame.add(WestPanel,BorderLayout.WEST);
	
	SearchCustomerFrame.add(SouthPanel,BorderLayout.SOUTH);
	SearchCustomerFrame.add(CenterPanel,BorderLayout.CENTER);
	SearchCustomerFrame.setVisible(true);
	}
}
	

