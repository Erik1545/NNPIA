package app.eshop.service;

import app.eshop.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    void check(String username);

    List<CustomerOrder> getCustomerOrders(String username);
}
