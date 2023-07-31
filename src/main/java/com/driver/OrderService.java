package com.driver;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService{
    
    OrderRepository orderRepository=new OrderRepository();

    public void addOrder(Order o1){
        orderRepository.addOrder(o1);
    }
    public void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);

    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }
    public Order getOrderById(String orderId){
        Order order=orderRepository.getOrderById(orderId);
        return order;
    }
    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner deliveryPartner=orderRepository.getPartnerById(partnerId);
        return deliveryPartner;
    }
    public int getOrderCountByPartnerId(String partnerId){
        int count=getOrderCountByPartnerId(partnerId);
        return count;
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orders=orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }
    public List<String> getAllOrders(){
        List<String> allOrders= orderRepository.getAllOrders();
        return allOrders;


    }
    public int getCountOfUnassignedOrders(){
        int count=orderRepository.getCountOfUnassignedOrders();
        return count;

    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime, String partnerId){
        
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(deliveryTime,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
       return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);

    }
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }
    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}