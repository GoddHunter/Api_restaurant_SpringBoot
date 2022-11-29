package com.formation.restaurant.services;

import java.util.List;

import com.formation.restaurant.models.Restaurant;

public interface RestaurantService {
	public List<Restaurant> findAll();

	public Restaurant findById(String id);

	public String create(Restaurant restaurantAcreer);

	public void update(String identifiant, Restaurant restaurantMaj);
}
