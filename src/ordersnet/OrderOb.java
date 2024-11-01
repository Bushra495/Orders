/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordersnet;

/**
 *
 * @author User
 */
import java.io.*;
import java.io.File;
import java.util.Scanner;
class OrdersList{
	public String [] SizesArray = {"XS","S","M","L","XL","XXL"};
	public double [] UnitPriceTShirtArray = {600,800,900,1000,1100,1200};

	public final int PROCESSING =0;////////////
	public final int DELIVERING =1;
	public final int DELIVERED  =2;
	
	
	private Node start;
	
	public void add(OrderOb orderOb) throws IOException{
		addLast(orderOb);
		FileWriter fw=new FileWriter("OrdersList.txt",true);
		String line=orderOb.getOrderId()+" "+orderOb.getPhoneNumber()+" "+orderOb.getTShirtSize()+" "+orderOb.getQuantity()+" "+orderOb.getAmount()+" "+orderOb.getIntStatus();
		fw.write(line);
		fw.write("\n");
		fw.flush();
	}
	public void addLast(OrderOb orderOb){
		Node n1=new Node(orderOb);
		if(start==null){
			start=n1;
		}else{
			Node last=start;
			while(last.next!=null){
				last=last.next;
			}
			last.next=n1;
		}
	}
	public void addFirst(OrderOb orderOb){ 
		Node n1=new Node(orderOb);
		n1.next=start;
		start=n1;
	}
	public void removeFirst(){
		if(start!=null){
			start=start.next;
		}
	}
	// public void removeLast(){
	// 	if(isEmpty()){
	// 		return;
	// 	}
	// 	if(start.next==null){
	// 		start=null;
	// 	}else{
	// 		Node beforeLast=start;
	// 		while(beforeLast.next.next!=null){
	// 			beforeLast=beforeLast.next;
	// 		}
	// 		beforeLast.next=null;
	// 	}
	// }
	
	// public void add(int index, OrderOb orderOb){
	// 	if(index>=0 && index<=size()){
	// 		if(index==0){
	// 			addFirst(orderOb);
	// 		}else{
	// 			Node n1=new Node(orderOb); //1
	// 			Node temp=start;
	// 			int i=0;
	// 			while(i<index-1){
	// 				temp=temp.next;
	// 				i++;
	// 			}
	// 			n1.next=temp.next;
	// 			temp.next=n1;
	// 		}
	// 	}
	// }
	public boolean deleteOrder(String OrderId) throws IOException{
		Node n1=start;
		
		int index=search(new OrderOb(OrderId,null,null,0,0,0));
		
		if (index!=-1) {
			remove(index);
			FileWriter fw=new FileWriter("OrdersList.txt");
			int i=0;
			while(n1!=null){
				OrderOb orderOb=getIndex(i++);
				String line=orderOb.getOrderId()+" "+orderOb.getPhoneNumber()+" "+orderOb.getTShirtSize()+" "+orderOb.getQuantity()+" "+orderOb.getAmount()+" "+orderOb.getIntStatus()+"\n";
				fw.write(line);
				fw.flush();
				n1=n1.next;
			}
			return true;
		}
		return false;
	}

	public boolean remove(int index){
		if(index>=0 && index<=size()){
			if(index==0){
				removeFirst();
			}else{
				Node temp=start;
				int i=0;
				while(i<index-1){
					temp=temp.next;
					i++;
				}
				temp.next=temp.next.next;
			}
			return true;
		}
		return false;
	}
	public OrderOb getIndex(int index){
		if(index>=0 && index<size()){
			int i=0;
			Node temp=start;
			while(i<index){
				temp=temp.next;
				i++;
			}
			return temp.orderOb;
		}
		return null;
	}
	public String toString(){
		Node temp=start;
		String list="{";
		while(temp!=null){
			list+=temp.orderOb+", "; 
			temp=temp.next;
		}
		return isEmpty()? "{empty}":list+"\b\b}";		
	}
	public boolean isEmpty(){
		return start==null;
	}
	public int search(OrderOb orderOb){
		Node temp=start;
		int index=-1;
		while(temp!=null){
			index++;
			if(orderOb.getOrderId().equals(temp.orderOb.getOrderId())){
				return index;
			}
			temp=temp.next;
		}
		return -1;
	}

	public OrderOb searchOrderOb(String OrderId) throws IOException{
		Scanner scan=new Scanner(new File("OrdersList.txt"));
		while(scan.hasNext()){
			String line=scan.nextLine();
			if (line.startsWith(OrderId.toUpperCase())) {
				String[] rowData=line.split(" ");
				String OId=rowData[0];
				String Phone=rowData[1];
				String TSize=rowData[2];
				int QTY=Integer.parseInt(rowData[3]);
				double Amount=Double.parseDouble(rowData[4]);
				int Status=Integer.parseInt(rowData[5]);
				return new OrderOb(OId, Phone, TSize, QTY, Amount, Status);
			}
		}
		return null;
	}

	public int size(){
		Node temp=start;
		int index=0;
		while(temp!=null){
			index++;
			temp=temp.next;
		}
		return index;
	}
	public void clear(){
		start=null;
	}

	public int SizeChart(String Size){
		for(int i=0; i<SizesArray.length;i++){
			if (SizesArray[i].equals(Size)){
				return i;
			}
		}
		return -1;
	}

	class Node{
		private OrderOb orderOb;
		private Node next;
		Node(OrderOb orderOb){this.orderOb=orderOb;}
	}
public OrderOb[] toArray(){
		OrderOb[] orderObArray=new OrderOb[size()];
		Node temp=start;
		for(int i=0; i<size(); i++){
			orderObArray[i]=temp.orderOb;
			temp=temp.next;
		}
		return orderObArray;
	}
}

class OrderOb{    
    private String orderid;
    private String phonenumber;
    private String tshirtsize;
    private int quantity;
    private double amount;
    private int status;

    OrderOb(){}
    OrderOb(String orderid,String phonenumber,String tshirtsize,int quantity,double amount,int status){
    this.orderid=orderid;
    this.phonenumber=phonenumber;
    this.tshirtsize=tshirtsize;
    this.quantity=quantity;
    this.amount=amount;
    this.status=status;
    }

    public void setOrderId(String orderid){
		this.orderid=orderid;
	}
	public void setPhoneNumber(String phonenumber){
		this.phonenumber=phonenumber;
	}
	public void setTShirtSize(String tshirtsize){
		this.tshirtsize=tshirtsize;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public void setAmount(double amount){
		this.amount=amount;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public String getOrderId(){
		return orderid;
	}
	public String getPhoneNumber(){
		return phonenumber;
	} 
	public String getTShirtSize(){
		return tshirtsize;
	}
	public int getQuantity(){
		return quantity;
	}
	public double getAmount(){
		return amount;
	}
	public String getStatus(){
		if(status==0){
            return "Processing";
        }else if(status==1){
            return "Delivering";
        }else if(status==2){
            return "Delivered";
        }else{
            return "";
        } 
	}
	public int getIntStatus(){
		return status; 
	}
}
