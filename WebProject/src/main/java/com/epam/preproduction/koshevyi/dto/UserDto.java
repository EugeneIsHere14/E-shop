package com.epam.preproduction.koshevyi.dto;

import com.epam.preproduction.koshevyi.entity.User;

/**
 * User DTO class. Contains method for creating
 * an instance of User class.
 */
public class UserDto {

    private String email;
    private String login;
    private String name;
    private String password;
    private String confirmPassword;
    private String salesSubscription;
    private String goodsSubscription;

    public UserDto(String email, String login, String name, String password, String confirmPassword,
                   String salesSubscription, String goodsSubscription) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.salesSubscription = salesSubscription;
        this.goodsSubscription = goodsSubscription;
    }

    /**
     * Creates User instance.
     *
     * @return User instance
     */
    public User createNewUser() {
        return new User(this.email, this.login, this.name, this.password,
                this.salesSubscription, this.goodsSubscription, 2);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSalesSubscription() {
        return salesSubscription;
    }

    public void setSalesSubscription(String salesSubscription) {
        this.salesSubscription = salesSubscription;
    }

    public String getGoodsSubscription() {
        return goodsSubscription;
    }

    public void setGoodsSubscription(String goodsSubscription) {
        this.goodsSubscription = goodsSubscription;
    }
}