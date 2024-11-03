package com.helswify.buildings.publicService;


import com.helswify.buildings.Building;
import com.helswify.city.CityFunctions;


public class Education extends Building implements CityFunctions {

	public static final int CAPACITY = 1000;
	
	private static final int COST = 500;
	
	private static final boolean PAYS_TAXES = false;
	
	private static final int OPERATING_COST = 100;
	
	public Education() {
		super(COST, BuildingType.EDUCATION, CAPACITY, PAYS_TAXES);
	}

	@Override
	public int getOperatingCosts() {
		return OPERATING_COST;
	}

}
