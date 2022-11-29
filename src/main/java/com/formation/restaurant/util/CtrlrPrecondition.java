package com.formation.restaurant.util;

import com.formation.restaurant.exceptions.ResourceNotfoundException;
import com.formation.restaurant.models.Restaurant;

public final class CtrlrPrecondition {

	public static Restaurant checkFound(Restaurant restaurant) {
		if(restaurant == null) {
			throw new ResourceNotfoundException();
		}
		return restaurant;
	}
}
