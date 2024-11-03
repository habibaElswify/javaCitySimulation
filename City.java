package com.helswify.city;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.helswify.buildings.Building;
import com.helswify.buildings.Utility;
import com.helswify.buildings.publicService.Education;

public class City {
	private int demand;
	
	private int supply;
	
	private double cityBalance;
	
	private List<Building> buildings = new ArrayList<>();
	
	private double propertyTax = 0.07;
	
	public City() {
		this.cityBalance = 3000;
	}
	
	/**
	 * @return the propertyTax
	 */
	public double getPropertyTax() {
		return propertyTax;
	}

	/**
	 * @param propertyTax the propertyTax to set
	 */
	public void setPropertyTax(double propertyTax) {
		this.propertyTax = propertyTax;
	}

	/**
	 * @return the demand
	 */
	public int getDemand() {
		return demand;
	}

	/**
	 * @param demand the demand to set
	 */
	public void setDemand(int demand) {
		this.demand = demand;
	}

	/**
	 * @return the supply
	 */
	public int getSupply() {
		return supply;
	}

	/**
	 * @param supply the supply to set
	 */
	public void setSupply(int supply) {
		this.supply = supply;
	}
	
	/**
	 * @return the cityBalance
	 */
	public double getCityBalance() {
		return cityBalance;
	}

	/**
	 * @param cityBalance2 the cityBalance to set
	 */
	public void setCityBalance(double cityBalance2) {
		this.cityBalance = cityBalance2;
	}

	/**
	 * @return the buildings
	 */
	public List<Building> getBuildings() {
		return buildings;
	}

	public int netNeed() {
		return demand - supply;
	}
	
	public void purchase(Building building) {
		if (getCityBalance() - building.getBuildingCost() < 0) {
			JOptionPane.showMessageDialog(null, "Not enough funds to purchase " + building.getBuildingType(), "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
			return;
		}
		cityBalance -= building.getBuildingCost();
		if (building instanceof Utility) {
			supply += building.getCapacity();
		} else {
			demand += building.getCapacity();;
		}
		buildings.add(building);
	}
	
	public void collectTaxes() {
		double income = 0;
		if (netNeed() > 0) {
			income = propertyTax * getSupply();
		} else {
			income = propertyTax * getDemand();
		}
		this.cityBalance += income; 
	}
	
	public void payOperatingCosts() {
		double expense = 0;
		for (Building building : buildings) {
			if (building instanceof Education) {
				expense += ((Education) building).getOperatingCosts();
			}
		}
		this.cityBalance -= expense; 
	}
	
	public String toString() {
		String newLine = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		sb.append("City Property Taxes: ").append(getPropertyTax()).append(newLine);
		sb.append("City Supply: ").append(getSupply()).append(newLine);
		sb.append("City Demand: ").append(getDemand()).append(newLine);
		sb.append("City Net Need: ").append(netNeed()).append(newLine);
		sb.append("City balance: ").append(getCityBalance()).append(newLine);
		return sb.toString();
	}

	public void setBuildings(List<Building> buildings2) {
		// TODO Auto-generated method stub
		
	}
}