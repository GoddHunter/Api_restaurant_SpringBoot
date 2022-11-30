package com.formation.restaurant.models;

import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "menus")
@Entity
public class Menu {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy="uuid")
	private String identifiant;
	private String entrees;
	private String plats;
	private String dessert;
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getEntrees() {
		return entrees;
	}
	public void setEntrees(String entrees) {
		this.entrees = entrees;
	}
	public String getPlats() {
		return plats;
	}
	public void setPlats(String plats) {
		this.plats = plats;
	}
	public String getDessert() {
		return dessert;
	}
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dessert, entrees, plats);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		return Objects.equals(dessert, other.dessert) && Objects.equals(entrees, other.entrees)
				&& Objects.equals(plats, other.plats);
	}
	
	
}
