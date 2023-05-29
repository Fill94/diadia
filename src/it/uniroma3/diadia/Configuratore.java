package it.uniroma3.diadia;

import java.util.Properties;

public final class Configuratore {
	private final static String DIA_DIA_PROPERTIES ="diadia.properties";
	private static  Properties prop;
	private static String PESO_MAX = "peso_max_borsa";
	private static String CFU_INIZIALI = "cfu_iniziali";
	public Configuratore() {
		prop = new Properties();
		carica(prop);
	}
	
	private static void carica(Properties prop) {
		try {
			prop.load(ClassLoader.getSystemResourceAsStream(DIA_DIA_PROPERTIES));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int getPesoMax() {
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}
	public int getCfuIniziali() {
		return Integer.parseInt(prop.getProperty(CFU_INIZIALI));
	}
}
