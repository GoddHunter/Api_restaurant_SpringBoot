package com.formation.restaurant.util;

import com.formation.restaurant.exceptions.ResourceNotfoundException;
import com.formation.restaurant.models.Menu;
import com.formation.restaurant.models.Restaurant;

public final class CtrlrPrecondition {

	public static <T> T checkFound(T object) {
		if(object == null) {
			throw new ResourceNotfoundException();
		}
		return object;
	}
}
