package com.champlain.carrental.domain;
import jakarta.persistence.*;
@Entity @Table(name="customers") public class Customer {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
@Column(nullable=false) public String firstName,lastName;
@Column(nullable=false,unique=true) public String email;
public String phone;
@Column(nullable=false,unique=true) public String driverLicenseNumber;
public Customer(){}
    public Long getId() {
        return id;
    }
}
