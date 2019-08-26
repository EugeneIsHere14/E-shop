package com.epam.preproduction.koshevyi.entity;

import java.util.Objects;

/**
 * User entity class.
 */
public class User {

    private int id;
    private String email;
    private String login;
    private String name;
    private String password;
    private String salesSubscription;
    private String goodsSubscription;
    private int roleId;

    public User() {
    }

    public User(String email, String login, String name, String password,
                String salesSubscription, String goodsSubscription, int roleId) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.password = password;
        this.salesSubscription = salesSubscription;
        this.goodsSubscription = goodsSubscription;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                roleId == user.roleId &&
                email.equals(user.email) &&
                login.equals(user.login) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                Objects.equals(salesSubscription, user.salesSubscription) &&
                Objects.equals(goodsSubscription, user.goodsSubscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, name, password, salesSubscription, goodsSubscription, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salesSubscription='" + salesSubscription + '\'' +
                ", goodsSubscription='" + goodsSubscription + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}