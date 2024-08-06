package Experiments;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {


		Date date= new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		
		
	System.getProperties().list(System.out);
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));
		

	}

}
