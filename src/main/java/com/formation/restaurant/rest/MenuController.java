package com.formation.restaurant.rest;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping("/restaurants/{idResto}/menus")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@PathVariable("idResto") String idRestaurant, @RequestBody Menu menuCreated) {
		CtrlrPrecondition.checkFound(restoService.findById(idRestaurant));
		return menuService.create(idRestaurant, menuCreated);
	}
	
	@PutMapping("/menus/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable("id") String id, @RequestBody Menu menuMaj) {
		CtrlrPrecondition.checkFound(menuService.findById(id));
		menuService.update(id, menuMaj);
	}
	
	@PatchMapping("/menus/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void partialUpdate(@PathVariable("id") String id, @RequestBody Map<String, Object> updates) {
		CtrlrPrecondition.checkFound(menuService.findById(id));
		menuService.partialUpdate(id, updates);		
	}
	
	@DeleteMapping("/restaurants/{idResto}/menus/{idMenu}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable("idResto") String idRestaurant, @PathVariable("idMenu") String idMenu) {
		CtrlrPrecondition.checkFound(restoService.findById(idRestaurant));
		CtrlrPrecondition.checkFound(menuService.findById(idMenu));
		menuService.deleteById(idRestaurant, idMenu);
	}
		
}
