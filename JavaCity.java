package com.helswify.city;


import javax.swing.SwingUtilities;

public class JavaCity {
	
	public static City city = new City();

	public static void main(String[] args) {
		CityGraphics g = new CityGraphics();
		SwingUtilities.invokeLater(g);
	}

}
