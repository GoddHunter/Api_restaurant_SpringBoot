package com.formation.restaurant.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formation.restaurant.models.Menu;
import com.formation.restaurant.services.MenuService;
import com.formation.restaurant.services.RestaurantService;
import com.formation.restaurant.util.CtrlrPrecondition;

@RestController
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RestaurantService restoService;
	
	@GetMapping("/restaurants/{idResto}/menus")
	public Set<Menu> findAllOfRestaurant(@PathVariable("idResto") String idRestaurant) {
		
		CtrlrPrecondition.checkFound(restoService.findById(idRestaurant));
		
		return menuService.findAllOfRestaurant(idRestaurant);
	}
	
	@GetMapping("/menus/{id}")
	public Menu findById(@PathVariable("id") String id) {
		Menu response = menuService.findById(id);
		CtrlrPrecondition.checkFound(response);
		return response;
	}
	
}
