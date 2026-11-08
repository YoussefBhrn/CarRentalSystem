package com.champlain.carrental.domain;
import jakarta.persistence.*;import java.time.LocalDate;import java.math.BigDecimal;
@Entity @Table(name="rentals") public class Rental {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
@ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="car_id",nullable=false) public Car car;
@ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="customer_id",nullable=false) public Customer customer;
@Column(nullable=false) public LocalDate startDate,endDate;
public LocalDate returnDate;
@Column(nullable=false,precision=12,scale=2) public BigDecimal totalPrice;
@Enumerated(EnumType.STRING) @Column(nullable=false) public RentalStatus status;
public Rental(){} }
