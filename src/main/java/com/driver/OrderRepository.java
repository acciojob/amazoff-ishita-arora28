package com.driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository 
public class OrderRepository{
    Map<String,Order> orderDb=new HashMap<>();
    Map<String,DeliveryPartner> deliveryPartnerDb=new HashMap<>();

    Map<String,String> orderPartenerdb=new HashMap<>();
    Map<String,List<String>> partnerOrderDb=new HashMap<>();
    

    public void addOrder(Order order){
        orderDb.put(order.getId(),order);
    }
    public void addPartner(String partnerId){
        deliveryPartnerDb.put(partnerId,new DeliveryPartner(partnerId));
    }
    public void addOrderPartnerPair(String orderId, String partnerId){
        if(orderDb.containsKey(orderId) && deliveryPartnerDb.containsKey(partnerId)){
            orderPartenerdb.put(orderId,partnerId);
            List<String> currentOrder=new ArrayList<>();
            if(partnerOrderDb.containsKey(partnerId)){
                currentOrder=partnerOrderDb.get(partnerId);
            }
            currentOrder.add(orderId);
            partnerOrderDb.put(partnerId,currentOrder);

            DeliveryPartner deliveryPartner=deliveryPartnerDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(currentOrder.size());

        }
       

    }
     public Order getOrderById(String orderId){
            return orderDb.get(orderId);
        }
        public DeliveryPartner getPartnerById(String partnerId){
            return deliveryPartnerDb.get(partnerId);
        }
    public int getOrderCountByPartnerId(String partnerId){
        Integer orderCount=0;
        if(deliveryPartnerDb.containsKey(partnerId)){
            orderCount=deliveryPartnerDb.get(partnerId).getNumberOfOrders()
        }
        return orderCount;
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return partnerOrderDb.get(partnerId);
    }
    public List<String> getAllOrders(){
        List<String> orders=new ArrayList<>();
        for(String order: orderDb.keySet()){
            orders.add(order);
        }
        return orders;

    }
    public int getCountOfUnassignedOrders(){
       Integer countOfOrders=0;
       for(String orderId:orderDb.keySet()){
        if(!orderPartenerdb.containsKey(orderId)){
            countOfOrders+=1;
        }
       }
       return countOfOrders;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        Integer hour = Integer.valueOf(time.substring(0,2));
        Integer mins=Integer.valueOf(time.substring(3));
        Integer newTime=hour*60+mins;

        int count=0;
        if(partnerOrderDb.containsKey(partnerId))
        {
        List<String> orders=partnerOrderDb.get(partnerId);
        for(String orderId :orders){
               int deliveryTime= orderDb.get(orderId).getDeliveryTime();
                if(deliveryTime>newTime){
                    count+=1;
                }
        }
    }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int maxTime=0;
        if(partnerOrderDb.containsKey(partnerId)){
        List<String> orders=partnerOrderDb.get(partnerId);
        for(String orderId:orders){
            int deliveryTime=orderDb.get(orderId).getDeliveryTime();
            maxTime=Math.max(maxTime,deliveryTime);

        }
    }
    Integer hour=maxTime/60;
    Integer minutes=maxTime%60;
    String hourInString = String.valueOf(hour);
    String minInString = String.valueOf(minutes);

    if(hourInString.length() == 1){
        hourInString = "0" + hourInString;
    }
    if(minInString.length() == 1){
        minInString = "0" + minInString;
    } 
    return hourInString +":"+ minInString;

    }
    public void deletePartnerById(String partnerId){
        deliveryPartnerDb.remove(partnerId);
 
        List<String> orders=partnerOrderDb.get(partnerId);
        partnerOrderDb.remove(partnerId);
        for(String o:orders){
            orderPartenerdb.remove(o);
        }

    }
    public void deleteOrderById(String orderId){
        orderDb.remove(orderId);
        String partnerId=orderPartenerdb.get(orderId);
        orderPartenerdb.remove(orderId);
        partnerOrderDb.get(partnerId).remove(partnerId);
        deliveryPartnerDb.get(partnerId).setNumberOfOrders(partnerOrderDb.get(partnerId).size());

    }
    
}
