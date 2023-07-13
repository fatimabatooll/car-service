package com.glc.car;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.car.Controller.CarCon;
import com.glc.car.Model.Car;
import com.glc.car.Repository.CarRepo;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class CarApplicationTests {

	private MockMvc mvc;
	@Mock
	private CarRepo carRepo;

	@InjectMocks
	private CarCon carCon;

	private JacksonTester<Car>  jsonCar;

	private JacksonTester<Collection<Car>> jsonCars;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(carCon).build();
	}

	@Test
     public void addCar() throws Exception {
		Car car = new Car(5L, "Bugatti Chiron Super Sport", "Unleash unrivaled power and elegance with the Bugatti Chiron Super" , "The finest Islamabad has to offer - like visiting the future!", "https://raw.githubusercontent.com/jeff-lent/exotourista/main/CentaurusHotelSuites-Islamabad.jpg",  62000.0);
		when(carRepo.save(car)).thenReturn((car));
		mvc.perform(MockMvcRequestBuilders   
		           .post("/cars/add")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(jsonCar.write(car).getJson()))
				   .andExpect(MockMvcResultMatchers.status().isOk());
	 }
    
   @Test
public void getAll() throws Exception {
    Car car1 = new Car(5L, "Bugatti Chiron Super Sport", "Unleash unrivaled power and elegance with the Bugatti Chiron Super" , "The finest Islamabad has to offer - like visiting the future!", "https://raw.githubusercontent.com/jeff-lent/exotourista/main/CentaurusHotelSuites-Islamabad.jpg",  62000.0);
    Car car2 = new Car(5L, "Bugatti Chiron Super", "Unleash  and elegance with the Bugatti Chiron Super" , "The finest Islamabad has to offer  visiting the future!", "https://raw.githubusercontent.com/jeff-lent/exotourista/main/CentaurusHotelSuites-Islamabad.jpg",  6200.0);

    List<Car> listOfhotels = new ArrayList<>();
    listOfhotels.add(car1);
    listOfhotels.add(car2);

    when(carRepo.findAll()).thenReturn(listOfhotels);

    mvc.perform(MockMvcRequestBuilders
            .get("/cars/getall")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(jsonCars.write(listOfhotels).getJson()));
}

	@Test
	void contextLoads() {
	}

}
