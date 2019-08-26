package com.epam.preproduction.koshevyi.cart;

import com.epam.preproduction.koshevyi.bean.ProductBean;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * This interface contains methods which are
 * required for process of buying item.
 */
public interface ShoppingCart {

    Map<ProductBean, Integer> getCart();

    /**
     * Adds specified product to the cart.
     *
     * @param product instance of Product which
     *                will be added to the cart
     */
    void addProduct(ProductBean product);

    /**
     * Removes all units of specified product from the cart.
     *
     * @param product instance of Product which
     *                will be removed from the cart
     */
    void removeProduct(ProductBean product);

    /**
     * Removes all products from the cart.
     */
    void clearCart();

    /**
     * Returns Set instance of all products
     * which are stored in the cart.
     *
     * @return Set instance of all products
     *         which are stored in the cart
     */
    Set<ProductBean> getProducts();

    /**
     * Returns the exact quantity of items which
     * were added to the cart.
     *
     * @return the exact quantity of items which
     *         were added to the cart
     */
    int getAllProductsCount();

    /**
     * Returns the total cost of all products
     * from the cart.
     *
     * @return the total cost of all products
     *         from the cart
     */
    BigDecimal getTotalCost();

    /**
     * Returns current cost of the item according
     * to the new item's quantity.
     *
     * @param itemId id of current the item
     * @param newQuantity changed quantity
     *        of the current item
     * @return the cost of the current item after
     *         quantity changing
     */
    BigDecimal changeItemQuantity(int itemId, int newQuantity);
}