package com.champlain.carrental.domain;
import jakarta.persistence.*;import java.math.BigDecimal;
@Entity @Table(name="cars") public class Car {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
@Column(nullable=false) public String make,model;
@Column(nullable=false,name="model_year") public Integer year;
@Column(nullable=false,unique=true) public String licensePlate;
@Enumerated(EnumType.STRING) @Column(nullable=false) public CarCategory category;
@Column(nullable=false,precision=10,scale=2) public BigDecimal dailyRate;
@Enumerated(EnumType.STRING) @Column(nullable=false) public CarStatus status;
public Car(){} }
