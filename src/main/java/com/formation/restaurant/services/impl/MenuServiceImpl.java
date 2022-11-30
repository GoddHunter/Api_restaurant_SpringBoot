package com.formation.restaurant.services.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.restaurant.dao.MenuRepository;
import com.formation.restaurant.dao.RestaurantRepository;
import com.formation.restaurant.models.Menu;
import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private RestaurantRepository restoRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Set<Menu> findAllOfRestaurant(String idRestaurant) {
		return restoRepository.findById(idRestaurant).get().getMenus();
	}

	@Override
	public Menu findById(String id) {
		if (menuRepository.findById(id).isPresent()) {
			return menuRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public String create(String idRestaurant, Menu menuCreated) {

		Restaurant restoEntity = restoRepository.findById(idRestaurant).get();

		restoEntity.getMenus().add(menuCreated);
		restoRepository.save(restoEntity);

		Menu menuEntity = restoEntity.getMenus().stream().filter(m -> m.equals(menuCreated)).findFirst().get();
		return menuEntity.getIdentifiant();

	}

	@Override
	public void update(String id, Menu menuMaj) {
		menuMaj.setIdentifiant(id);
		menuRepository.save(menuMaj);
	}

	@Override
	public void partialUpdate(String id, Map<String, Object> updates) {

		Menu menuToMaj = menuRepository.findById(id).get();

		for (String key : updates.keySet()) {
			switch (key) {
			case "entrees": {
					menuToMaj.setEntrees((String) updates.get(key));break;
				}
			case "plats": {
				menuToMaj.setPlats((String) updates.get(key));break;
				}
			case "dessert": {
				menuToMaj.setDessert((String) updates.get(key));break;
				}
			}
		}
		
		menuRepository.save(menuToMaj);

	}

	@Override
	public void deleteById(String idRestaurant, String idMenu) {
	
		Restaurant restoToUpdate = restoRepository.findById(idRestaurant).get();
		
		Set<Menu> menus = restoToUpdate.getMenus();
		
		Menu menu = menus.stream().filter(m -> m.getIdentifiant().equals(idMenu)).findFirst().get();
		menus.remove(menu);
		
		restoToUpdate.setMenus(menus);
		restoRepository.save(restoToUpdate);
		
		menuRepository.delete(menu);
	}
}
