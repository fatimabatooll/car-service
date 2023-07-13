package com.glc.car.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glc.car.Model.Car;


public interface CarRepo extends JpaRepository<Car, Long> {

    public List<Car> findAll();

}
