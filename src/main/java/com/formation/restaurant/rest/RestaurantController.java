package com.formation.restaurant.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.restaurant.exceptions.ResourceNotfoundException;
import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restoService;
	
	@GetMapping
	public List<Restaurant> findAll() {
		return restoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable("id") String identifiant) {
		Restaurant response = restoService.findById(identifiant);
		if(response == null) {
			throw new ResourceNotfoundException();
		}
		return response;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@RequestBody Restaurant restaurantAcreer) {
		return restoService.create(restaurantAcreer);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable("id") String identifiant, @RequestBody Restaurant restaurantMaj) {
		if(restoService.findById(identifiant) == null) {
			throw new ResourceNotfoundException();
		}
		restoService.update(identifiant, restaurantMaj);
	}
}
