package com.vijava.demo;

public class VIJavaTest {
	public static void main(String[] args) {
		VIJava viJava = new VIJava(Constants.URL, Constants.USER_NAME, Constants.PASSWORD);
		
		System.out.println(viJava.findVirtualMachines()); 
	}
}
