package com.helswify.buildings.taxable;
import com.helswify.buildings.Building;


public class Residential extends Building {
	
	public static final int CAPACITY = 200;
	
	private static final int COST = 200;
	
	private static final boolean PAYS_TAXES = true;
	
	public Residential() {
		super(COST, BuildingType.RESIDENTIAL, CAPACITY, PAYS_TAXES);
	}

}
