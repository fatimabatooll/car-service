package com.glc.car.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.car.Model.Car;
import com.glc.car.Repository.CarRepo;

@RestController
@RequestMapping("/cars") // http://localhost:8080/cars
@CrossOrigin("*")
public class CarCon {
      private final CarRepo carRepo;
     
   public CarCon(CarRepo carRepo) {
    this.carRepo = carRepo;
  }
  @PostMapping("/add") // http://localhost:8080/car/add
  public ResponseEntity<Car> addHotel(@RequestBody Car car) {
    return ResponseEntity.ok().body(carRepo.save(car));
  }
    
  @GetMapping("/getall") // http://localhost:8080/car/getall
  public List<Car> getHotelall() {
    return carRepo.findAll();
  }
}
