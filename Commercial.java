
package com.helswify.buildings.taxable;

import com.helswify.buildings.Building;


public class Commercial extends Building {
	
	public static final int CAPACITY = 250;
	
	private static final int COST = 200;
	
	private static final boolean PAYS_TAXES = true;
	
	public Commercial() {
		super(COST, BuildingType.COMMERCIAL, CAPACITY, PAYS_TAXES);
	}
}
