package com.epam.preproduction.koshevyi.dto;

import com.epam.preproduction.koshevyi.entity.Order;

public class OrderDto {

    public Order createOrder() {
        return new Order();
    }
}
