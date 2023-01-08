package com.driver;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class OrderRepository {


    private HashMap<String ,Order> orderHashMap;
    private HashMap<String,DeliveryPartner> deliveryPartnerHashMap;
    private HashMap<String, List<String>> orderDeliveryPartnerPair;

    OrderRepository (){
        this.orderHashMap=orderHashMap;
        this.deliveryPartnerHashMap=deliveryPartnerHashMap;
        this.orderDeliveryPartnerPair=orderDeliveryPartnerPair;

    }
     public void addOrder(Order order){
        orderHashMap.put(order.getId(),order);
     }
     public void addDeliveryPartner(String deliveryPartner){
        DeliveryPartner partner=new DeliveryPartner(deliveryPartner);
        deliveryPartnerHashMap.put(deliveryPartner,partner);
     }
     public void  addOrderDeliverPartner(String deliverId,String orderId){
        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(deliverId)){
            List<String> orderList=new ArrayList<>();
            if (orderDeliveryPartnerPair.containsKey(deliverId)){
                orderList=orderDeliveryPartnerPair.get(deliverId);
                orderList.add(orderId);
            }
            orderDeliveryPartnerPair.put(deliverId,orderList);
        }
     }
     public Order getOrderById(String orderId){
           if(orderHashMap.containsKey(orderId)){
               return orderHashMap.get(orderId);
           }
           return null;
     }
       public DeliveryPartner getDeliverPartner(String deliveryPartnerId){

            if(deliveryPartnerHashMap.containsKey(deliveryPartnerId)){
                return deliveryPartnerHashMap.get(deliveryPartnerId);
            }
            return null;
       }
       public int getOrderCountForPartner(String partnerId){
           List<String> orderList=new ArrayList<>();
          if(deliveryPartnerHashMap.containsKey(partnerId)){
              orderList= orderDeliveryPartnerPair.get(partnerId);
              return orderList.size();
          }
          return orderList.size();
       }
       public  List<String> getOrderListByPartnerId(String partnerId){
           List<String> orderList=new ArrayList<>();
           if(deliveryPartnerHashMap.containsKey(partnerId)){
               orderList= orderDeliveryPartnerPair.get(partnerId);
           }
           return orderList;
       }
       public List<String> getAllOrder(){
           List<String> orderList=new ArrayList<>();
           return new ArrayList<String>(orderHashMap.keySet());
       }
       public int getCountOfUnassignedOrders(){
           HashSet<String> allPairedOrder=new HashSet<>();
           for(String delverId:orderDeliveryPartnerPair.keySet()){
               for (String orderId:orderDeliveryPartnerPair.get(delverId)){
                   allPairedOrder.add(orderId);
               }
           }
           int allOrder=orderHashMap.size();
           int unsignedOrder=allOrder-allPairedOrder.size();
           return unsignedOrder;
       }
        public int getOrdersLeftAfterGivenTimeByPartnerId(String time1){
        int count=0;
        for(String delverId:orderDeliveryPartnerPair.keySet()){
            for(String orderId:orderDeliveryPartnerPair.get(delverId)){
                DateFormat formatter = new SimpleDateFormat("time1");
                String time2= String.valueOf(orderHashMap.get(orderId).getDeliveryTime());
                DateFormat givenTime=new SimpleDateFormat("time2");
                 }
                }
        return 0;
            }
         public  void deletePartnerById(String partnerId){
           HashSet<String> orderList=new HashSet<>();
           if(orderDeliveryPartnerPair.containsKey(partnerId)){
               for (String partner:orderDeliveryPartnerPair.keySet()){
                   for(String order:orderDeliveryPartnerPair.get(partnerId)){
                       orderList.add(order);
                       orderDeliveryPartnerPair.remove(partnerId);
                   }
               }
           }
           for(String order:orderList){
               if(orderHashMap.containsKey(order)){
                   orderHashMap.remove(order);
               }
           }

         }
      public  void deleteOrderById(String orderId) {
          for (String partner : orderDeliveryPartnerPair.keySet()) {
              for (String order : orderDeliveryPartnerPair.get(partner)) {
                  if (order == orderId) {
                      orderDeliveryPartnerPair.remove(partner);
                  }

              }
          }
          if(orderHashMap.containsKey(orderId)){
              orderHashMap.remove(orderId);
          }
      }
}
