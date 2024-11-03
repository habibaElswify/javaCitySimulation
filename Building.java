package com.helswify.buildings;



public abstract class Building {
	private int buildingCost;
	
	private BuildingType buildingType;
	
	private int capacity;
	
	private boolean paysTaxes;
	
	public Building(int buildingCost, BuildingType buildingType, int capacity, boolean paysTaxes) {
		this.buildingCost = buildingCost;
		this.buildingType = buildingType;
		this.capacity = capacity;
		this.paysTaxes = paysTaxes;
	}

	/**
	 * @return the paysTaxes
	 */
	public boolean isPaysTaxes() {
		return paysTaxes;
	}

	/**
	 * @param paysTaxes the paysTaxes to set
	 */
	public void setPaysTaxes(boolean paysTaxes) {
		this.paysTaxes = paysTaxes;
	}

	/**
	 * @return the buildingCost
	 */
	public int getBuildingCost() {
		return buildingCost;
	}

	/**
	 * @param buildingCost the buildingCost to set
	 */
	public void setBuildingCost(int buildingCost) {
		this.buildingCost = buildingCost;
	}

	/**
	 * @return the buildingType
	 */
	public BuildingType getBuildingType() {
		return buildingType;
	}

	/**
	 * @param buildingType the buildingType to set
	 */
	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String toString() {
		String newLine = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		sb.append("BuildingType: ").append(getBuildingType()).append(newLine);
		sb.append("Capacity: ").append(getCapacity()).append(newLine);
		return sb.toString();
	}
	
	public enum BuildingType {
		UTILITY,
		RESIDENTIAL,
		COMMERCIAL,
		INDUSTRIAL,
		EDUCATION
	}
}
