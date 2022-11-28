package com.formation.restaurant.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> liste = new ArrayList<Restaurant>();
		
		Restaurant resto1 = new Restaurant();
		resto1.setId("resto1");
		resto1.setNom("Le Restaurant");
		resto1.setAddresse("4 Boulevard de Paris, 44400 Nnates");
		liste.add(resto1);
		
		Restaurant resto2 = new Restaurant();
		resto1.setId("resto2");
		resto1.setNom("Crêperie");
		resto1.setAddresse("4 Bis Boulevard de Paris, 44400 Nnates");
		liste.add(resto2);
		
		return liste;
	}

}
