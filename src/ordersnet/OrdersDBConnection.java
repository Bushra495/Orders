/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordersnet;

/**
 *
 * @author User
 */
class OrdersDBConnection {
    private static OrdersList orderslist;
    
    public static OrdersList getOrdersList(){
        if(orderslist==null){
            orderslist=new OrdersList();
        }
        return orderslist;
    }
}

