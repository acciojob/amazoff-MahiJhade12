package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
   OrderRepository orderRepository;
    public  void addOrder(Order order){
        orderRepository.addOrder(order);
    }
    public void addPartner(String deliveryPartner){
        orderRepository.addDeliveryPartner(deliveryPartner);
    }
    public void addOrderPartnerPair(String order ,String deliverPartner){
        orderRepository.addOrderDeliverPartner(order,deliverPartner);
    }
    public Order getOrderById(String orderId){
       return orderRepository.getOrderById(orderId);
    }
    public DeliveryPartner getPartnerById(String deliverPartner){
        return orderRepository.getDeliverPartner(deliverPartner);
    }
    public int getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountForPartner(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrderListByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){
        return orderRepository.getAllOrder();
    }
    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public void deletePartnerById(String partnerId){
        orderRepository.getOrderListByPartnerId(partnerId);
    }
    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
