package com.formation.restaurant.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.restaurant.dao.RestaurantRepository;
import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restoRepository;
	
	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> liste = new ArrayList<Restaurant>();
		
		restoRepository.findAll().forEach(liste::add);
		return liste;
	}

	@Override
	public Restaurant findById(String id) {
		if(restoRepository.findById(id).isPresent()) {
			return restoRepository.findById(id).get();
		}
		return null;		
	}

	@Override
	public String create(Restaurant restaurantAcreer) {		
		return restoRepository.save(restaurantAcreer).getId();
	}

	@Override
	public void update(String identifiant, Restaurant restaurantMaj) {
		restaurantMaj.setId(identifiant);
		
		restoRepository.save(restaurantMaj);
		
	}

	@Override
	public void partialUpdate(String identifiant, Map<String, Object> updates) {
		Restaurant restoToUpdate = restoRepository.findById(identifiant).get();
		
		for(String key : updates.keySet()) {
			switch(key) {
			case "nom": 
				restoToUpdate.setNom((String) updates.get(key)); break;
			case "adresse":
				restoToUpdate.setAdresse((String) updates.get(key)); break;
			}
		
		restoRepository.save(restoToUpdate);
		}	
	}

}
