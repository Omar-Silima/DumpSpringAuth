package com.taka.domain;

import javax.persistence.*;

@Entity
public class Municipal {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private int id;


    @Id
    private String name;
    private String email;
    private String address;
    private String region;
    private String district;
    private String phone;
    private long zipCode;
//    private String password;

    public Municipal() {
    }

    public Municipal(String name, String address, String region, String district,
                     long zipCode, String email, String phone, int id) {
        this.name = name;
        this.address = address;
        this.region = region;
        this.district = district;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.id = id;
//        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }

//    public void setPassword(String password) {
//        this.password = password;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
