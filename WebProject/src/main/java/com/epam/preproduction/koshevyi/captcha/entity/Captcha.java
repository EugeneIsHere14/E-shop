package com.epam.preproduction.koshevyi.captcha.entity;

import java.util.Objects;

/**
 * Captcha entity class.
 */
public class Captcha {

    private int id;
    private String value;
    private long creationTime;

    public Captcha(int id, String value, long creationTime) {
        this.id = id;
        this.value = value;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captcha captcha = (Captcha) o;
        return id == captcha.id &&
                creationTime == captcha.creationTime &&
                value.equals(captcha.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, creationTime);
    }
}