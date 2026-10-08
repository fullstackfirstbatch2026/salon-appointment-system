package com.salon.salon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STYLISTS")
public class Stylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stylist_id")
    private Integer stylistId;

    @Column(name = "stylist_name")
    private String stylistName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "specialization")
    private String specialization;


    public Stylist() {
    }


    public Integer getStylistId() {
        return stylistId;
    }

    public void setStylistId(Integer stylistId) {
        this.stylistId = stylistId;
    }


    public String getStylistName() {
        return stylistName;
    }

    public void setStylistName(String stylistName) {
        this.stylistName = stylistName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}