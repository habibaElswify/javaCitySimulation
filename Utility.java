package com.helswify.buildings;



public class Utility extends Building {
	
	private static final int CAPACITY = 600;
	
	private static final int COST = 1000;
	
	private static final boolean PAYS_TAXES = false;
	
	public Utility() {
		super(COST, BuildingType.UTILITY, CAPACITY, PAYS_TAXES);
	}

}
