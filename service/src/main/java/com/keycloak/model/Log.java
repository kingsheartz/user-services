package com.keycloak.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	
	//==================================
	//		Data Members
	//==================================
	public Logger logger;
	public FileHandler fh;
	
	//==================================
	//		Parameterized Constructor
	//==================================
	public Log(String file_name) throws SecurityException,IOException{
		
		File f=new File(file_name+".log");
		
		if(!f.exists()) {
			f.createNewFile();
			System.out.println("File created");
		}
		fh=new FileHandler(file_name+".log",true);
		logger = Logger.getLogger(Logger.class.getName());
		logger.addHandler(fh);
		SimpleFormatter formatter=new SimpleFormatter();
		fh.setFormatter(formatter);
	}
}
