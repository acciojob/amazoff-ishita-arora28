package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String[] currDelTime=deliveryTime.split(":");
        int hr=Integer.parseInt(currDelTime[0]);
        int sec=Integer.parseInt(currDelTime[1]);
        System.out.println(hr*60+sec);
        

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
