package com.helswify.buildings.taxable;

import com.helswify.buildings.Building;

public class Industrial extends Building {

	public static final int CAPACITY = 325;
	
	private static final int COST = 200;
	
	private static final boolean PAYS_TAXES = true;
	
	public Industrial() {
		super(COST, BuildingType.INDUSTRIAL, CAPACITY, PAYS_TAXES);
	}

}
