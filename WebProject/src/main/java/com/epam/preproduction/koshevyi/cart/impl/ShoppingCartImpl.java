package com.epam.preproduction.koshevyi.cart.impl;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.cart.ShoppingCart;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.math.BigDecimal.valueOf;

public class ShoppingCartImpl implements ShoppingCart {

    private Map<ProductBean, Integer> cart = new LinkedHashMap<>();

    @Override
    public Map<ProductBean, Integer> getCart() {
        return cart;
    }

    @Override
    public void addProduct(ProductBean product) {
        cart.merge(product, 1, Integer::sum);
    }

    @Override
    public void removeProduct(ProductBean product) {
        for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
            if (entry.getKey().equals(product)) {
                cart.remove(entry.getKey());
                return;
            }
        }
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public Set<ProductBean> getProducts() {
        return cart.keySet();
    }

    @Override
    public int getAllProductsCount() {
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public BigDecimal getTotalCost() {
        BigDecimal orderSum = valueOf(0);
        for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
            orderSum = orderSum.add(entry.getKey().getPrice().multiply(valueOf(entry.getValue())));
        }
        return orderSum;
    }

    @Override
    public BigDecimal changeItemQuantity(int itemId, int newQuantity) {
        for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == itemId) {
                cart.put(entry.getKey(), newQuantity);
                return entry.getKey().getPrice().multiply(BigDecimal.valueOf(newQuantity));
            }
        }
        return null;
    }
}