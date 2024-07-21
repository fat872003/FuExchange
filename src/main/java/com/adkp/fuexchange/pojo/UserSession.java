package com.adkp.fuexchange.pojo;


import com.adkp.fuexchange.request.OrdersRequest;

public class UserSession {
    private static UserSession instance;
    private OrdersRequest ordersRequest;

    private UserSession() {}

    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void cleanUserSession() {
        ordersRequest = null;
    }

    public OrdersRequest getOrdersRequest() {
        return ordersRequest;
    }

    public void setOrdersRequest(OrdersRequest ordersRequest) {
        this.ordersRequest = ordersRequest;
    }

    @Override
    public String toString() {
        return "UserSession [ordersRequest=" + ordersRequest + "]";
    }

}