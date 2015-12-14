/**
 * 
 */
package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marcos.fernando
 *
 */
public class BeerExpert {
	
	public List<String> getBrands(String color) {
		List<String> brands = new ArrayList<String>();
		if (color.equals("Amarela")) {
			brands.add("Skol Garrafa");
			brands.add("Skol Lata");
			brands.add("Brahma Garrafa");
			brands.add("Brahma Lata");			
		} else {
			brands.add("Bohemia Garrafa");
			brands.add("Bohemia Lata");
			brands.add("Antartica Garrafa");
			brands.add("Antartica Lata");			
		}
		return brands;
	}
	

}
