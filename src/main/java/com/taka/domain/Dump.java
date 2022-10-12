 package com.taka.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dump {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String municipal;
    private String dumpName;
    private String dumpAddress;
    private String dumpDistrict;
    private String dumpRegion;
//    private String dumpStreet;
    private String dumpLocation;
    private String dumpLongitude;
    private String dumpLatitude;
//    private String photo;

    public Dump() {
    }

    public Dump(Long id, String municipal, String dumpName, String dumpAddress,
                String dumpDistrict, String dumpRegion, String dumpLongitude,
                String dumpLatitude, String dumpLocation) {
        this.id = id;
        this.municipal = municipal;
        this.dumpName = dumpName;
        this.dumpAddress = dumpAddress;
        this.dumpDistrict = dumpDistrict;
        this.dumpRegion = dumpRegion;
        this.dumpLongitude = dumpLongitude;
        this.dumpLatitude = dumpLatitude;
        this.dumpLocation = dumpLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipal() {
        return municipal;
    }

    public void setMunicipal(String municipal) {
        this.municipal = municipal;
    }

    public String getDumpName() {
        return dumpName;
    }

    public void setDumpName(String dumpName) {
        this.dumpName = dumpName;
    }

    public String getDumpAddress() {
        return dumpAddress;
    }

    public void setDumpAddress(String dumpAddress) {
        this.dumpAddress = dumpAddress;
    }

    public String getDumpDistrict() {
        return dumpDistrict;
    }

    public void setDumpDistrict(String dumpDistrict) {
        this.dumpDistrict = dumpDistrict;
    }

    public String getDumpRegion() {
        return dumpRegion;
    }

    public void setDumpRegion(String dumpRegion) {
        this.dumpRegion = dumpRegion;
    }

    public String getDumpLongitude() {
        return dumpLongitude;
    }

    public void setDumpLongitude(String dumpLongitude) {
        this.dumpLongitude = dumpLongitude;
    }

    public String getDumpLatitude() {
        return dumpLatitude;
    }

    public void setDumpLatitude(String dumpLatitude) {
        this.dumpLatitude = dumpLatitude;
    }

    public String getDumpLocation() {
        return dumpLocation;
    }

    public void setDumpLocation(String dumpLocation) {
        this.dumpLocation = dumpLocation;
    }
}
