package javadev.filereader.main.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javadev.filereader.annotations.FileHeader;
import javadev.filereader.main.interfaces.CSVReader;
import javadev.filereader.main.interfaces.FileBeanValidation;
import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;
import javadev.filereader.parsers.utils.ParsersUtils;

final class SimpleCSVColumnHeaderReader<T> implements CSVReader<T>{
	
	private Class<T> target;
	private String separator;
	private BufferedReader reader;
	private Map<Class<?>, TypeParser<?>> parsers =  ParsersUtils.getParsers();
	private FileBeanValidation beanValidation = new SimpleBeanCSVBeanHeaderValidation();
	
	public SimpleCSVColumnHeaderReader(Class<T> target, String separator, BufferedReader reader){
		this.target = target;
		this.separator = separator;
		this.reader = reader;
	}

	@Override
	public List<T> getResultList() throws IOException, InstantiationException, IllegalAccessException, FieldParsingException{
		List<Map<String,String>> list = readWithColunmName();
		List<T> resultList = new ArrayList<T>();
		for (Map<String, String> data : list) {
			resultList.add(getNewObject(data));
		}
		
		return resultList;
	}
	
	private T getNewObject(Map<String, String> data) throws InstantiationException, IllegalAccessException, FieldParsingException{
		T newObject = target.newInstance();
		Field[] fields = target.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(!field.isAnnotationPresent(FileHeader.class)) continue;
			String name = field.getAnnotation(FileHeader.class).value().toUpperCase();
			String datum = data.get(name) == null ? null : data.get(name).trim();
			beanValidation.validate(datum, field);
			Class<?> type = field.getType();
			if(field.getType().isEnum()) type = Enum.class;
			TypeParser<?> parser = parsers.get(type);
			field.set(newObject, parser.parse(field, datum));
		}
		
		return newObject;
	}
	
	private List<Map<String,String>> readWithColunmName() throws IOException{
		List<Map<String,String>> list = new ArrayList<Map<String,String>>(); 
		String[] header = reader.readLine().split(separator);
		String line;
		while ((line = reader.readLine()) != null) {
			Map<String, String> result = new HashMap<String, String>();
			String[] data = line.split(separator);
			for(int i = 0; i < header.length; i++){
				result.put(header[i].toUpperCase(), data[i]);
			}
			list.add(result);
		}
		
		return list;
	}	
	
}