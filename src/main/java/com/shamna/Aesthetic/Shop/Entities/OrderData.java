package com.shamna.Aesthetic.Shop.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String userId;

    private int pid;

    private String address;

    private boolean status;

    public OrderData() {
    }

    public OrderData(int id, boolean status, String address, int pid, String userId) {
        this.id = id;
        this.status = status;
        this.address = address;
        this.pid = pid;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int oid) {
        this.id = oid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
