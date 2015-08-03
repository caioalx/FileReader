package javadev.filereader.main.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javadev.filereader.annotations.SimpleCSVBean;
import javadev.filereader.main.interfaces.CSVFileReader;
import javadev.filereader.main.interfaces.CSVReader;

final class SimpleCSVFileReader<T> implements CSVFileReader<T>{

	private Class<T> target;
	private String separator;
	private BufferedReader reader;
	
	public SimpleCSVFileReader(Class<T> target, String separator, InputStream file){
		if(!target.isAnnotationPresent(SimpleCSVBean.class)) 
			throw new IllegalStateException("Class does not have @SimpleCSVBean annotation");
		if(target == null || separator == null || file == null)
			throw new IllegalStateException("Some of the arguments have no values");
		this.target = target;
		this.separator = separator;
		InputStreamReader reader = new InputStreamReader(file);
		this.reader = new BufferedReader(reader);
	}

	@Override
	public CSVReader<T> withHeader() throws IOException{
		return new SimpleCSVColumnHeaderReader<T>(target, separator, reader);
	}	
	
	@Override
	public CSVReader<T> withPosition() throws IOException{
		return new SimpleCSVColumnPositionReader<T>(target, separator, reader);
	}

	@Override
	public CSVReader<T> withCustomCSVReader(CSVReader<T> customCSVReader) throws IOException{
		return customCSVReader;
	}	
	
}