/**
 * 
 */
package com.helswify.city;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.helswify.buildings.Building;
import com.helswify.buildings.Utility;
import com.helswify.buildings.publicService.Education;
import com.helswify.buildings.taxable.Commercial;
import com.helswify.buildings.taxable.Industrial;
import com.helswify.buildings.taxable.Residential;

public class SaveAndLoad {
	static FileWriter fw = null;
	static BufferedWriter bw = null;
	static FileReader fr = null;
	static BufferedReader br = null;
	static File file = new File("src/myCity.txt");
	
	public SaveAndLoad() {
		
	}
	
	public static void save(City city) {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			String content = "Java City contents:\n";
			bw.write(content);
			bw.write("Buildings: " + city.getBuildings() +"\n");
			bw.write("City Balance: " + city.getCityBalance() +"\n");
			bw.write("City Demand: " + city.getDemand() +"\n");
			bw.write("City Supply: " + city.getSupply() +"\n");
			bw.flush();
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static City load() {
		City loadedCity = new City();
		int numOfUtilityBuildings = 0;
		int numOfResidentialBuildings = 0;
		int numOfCommercialBuildings = 0;
		int numOfIndustrialBuildings = 0;
		int numOfEducationBuildings = 0;
		double cityBalance = 0;
		final int BALANCE_INDEX = 14;
		int cityDemand = 0;
		final int DEMAND_INDEX = 13;
		int citySupply = 0;
		final int SUPPLY_INDEX = 13;
		List<Building> buildings = new ArrayList<>();
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String sCurrentLine;
			int lineCount = 0;

			while ((sCurrentLine = br.readLine()) != null) {
				if (lineCount == 1 && sCurrentLine.contains("Buildings: ")) {
					numOfUtilityBuildings = sCurrentLine.split("UTILITY").length - 1;
					for (int x = 0; x < numOfUtilityBuildings; x++) {
						buildings.add(new Utility());
					}
					numOfResidentialBuildings = sCurrentLine.split("RESIDENTIAL").length - 1;
					for (int x = 0; x < numOfResidentialBuildings; x++) {
						buildings.add(new Residential());
					}
					numOfCommercialBuildings = sCurrentLine.split("COMMERCIAL").length - 1;
					for (int x = 0; x < numOfCommercialBuildings; x++) {
						buildings.add(new Commercial());
					}
					numOfIndustrialBuildings = sCurrentLine.split("INDUSTRIAL").length - 1;
					for (int x = 0; x < numOfIndustrialBuildings; x++) {
						buildings.add(new Industrial());
					}
					numOfEducationBuildings = sCurrentLine.split("EDUCATION").length - 1;
					for (int x = 0; x < numOfEducationBuildings; x++) {
						buildings.add(new Education());
					}
				}
				loadedCity.setBuildings(buildings);				
				
				if (lineCount == 2) {
					cityBalance = Double.parseDouble(sCurrentLine.substring(BALANCE_INDEX));
					loadedCity.setCityBalance(cityBalance);
				}
				if (lineCount == 3) {
					cityDemand = Integer.parseInt(sCurrentLine.substring(DEMAND_INDEX));
					loadedCity.setDemand(cityDemand);
				}
				if (lineCount == 4) {
					citySupply = Integer.parseInt(sCurrentLine.substring(SUPPLY_INDEX));
					loadedCity.setSupply(citySupply);
				}
				lineCount++;
			}
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No saved city", "File not found", JOptionPane.ERROR_MESSAGE);
			return loadedCity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null || br != null) {
					fr.close();
					br.close();
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return loadedCity;
	}
}
