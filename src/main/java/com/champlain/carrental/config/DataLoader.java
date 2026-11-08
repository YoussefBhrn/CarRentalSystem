package com.champlain.carrental.config;
import com.champlain.carrental.domain.*;import com.champlain.carrental.repository.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.*;import java.math.BigDecimal;import java.time.LocalDate;
@Configuration public class DataLoader {
@Bean CommandLineRunner seed(CarRepository cars,CustomerRepository customers,RentalRepository rentals){return args->{
String[] makes={"Toyota","Honda","Ford","Hyundai","Kia","Mazda","BMW","Nissan","Volkswagen","Chevrolet","Subaru","Audi"};
for(int i=0;i<12;i++){Car c=new Car();c.make=makes[i];c.model="Model"+(i+1);c.year=2017+i%8;c.licensePlate="QC-"+(1000+i);c.category=CarCategory.values()[i%5];c.dailyRate=BigDecimal.valueOf(49+i*7);c.status=i<10?CarStatus.RENTED:CarStatus.AVAILABLE;cars.save(c);Customer u=new Customer();u.firstName="Customer"+(i+1);u.lastName="Example"+(i+1);u.email="customer"+(i+1)+"@example.com";u.phone="555-010"+(i%10);u.driverLicenseNumber="DL-"+(10000+i);customers.save(u);}
for(int i=1;i<=10;i++){Rental r=new Rental();r.car=cars.findById((long)i).orElseThrow();r.customer=customers.findById((long)i).orElseThrow();r.startDate=LocalDate.now().plusDays(1);r.endDate=r.startDate.plusDays(3);r.totalPrice=r.car.dailyRate.multiply(BigDecimal.valueOf(3));r.status=RentalStatus.ACTIVE;rentals.save(r);}
};}
}