package javadev.filereader.main.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javadev.filereader.annotations.FilePosition;
import javadev.filereader.main.interfaces.CSVReader;
import javadev.filereader.main.interfaces.FileBeanValidation;
import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;
import javadev.filereader.parsers.utils.ParsersUtils;

final class SimpleCSVColumnPositionReader<T> implements CSVReader<T>{
	
	private Class<T> target;
	private String separator;
	private BufferedReader reader;
	private Map<Class<?>, TypeParser<?>> parsers =  ParsersUtils.getParsers();
	private FileBeanValidation beanValidation = new SimpleCSVBeanPositionValidation();
	
	public SimpleCSVColumnPositionReader(Class<T> target, String separator, BufferedReader reader){
		this.target = target;
		this.separator = separator;
		this.reader = reader;
	}

	@Override
	public List<T> getResultList() throws IOException, InstantiationException, IllegalAccessException, FieldParsingException{
		List<T> resultList = new ArrayList<T>();
		List<Map<Integer,String>> list = readWithColunmPosition();
		for (Map<Integer, String> data : list) {
			resultList.add(getNewObject(data));
		}
		return resultList;
	}
	
	private T getNewObject(Map<Integer, String> data) throws InstantiationException, IllegalAccessException, FieldParsingException{
		T newObject = target.newInstance();
		Field[] fields = target.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(!field.isAnnotationPresent(FilePosition.class)) continue;
			Integer position = field.getAnnotation(FilePosition.class).value();
			String datum = data.get(position) == null ? null : data.get(position).trim();
			beanValidation.validate(datum, field);
			Class<?> type = field.getType();
			if(field.getType().isEnum()) type = Enum.class;
			TypeParser<?> parser = parsers.get(type);
			field.set(newObject, parser.parse(field, datum));		
		}
		
		return newObject;
	}
	
	private List<Map<Integer,String>> readWithColunmPosition() throws IOException{
		List<Map<Integer,String>> list = new ArrayList<Map<Integer,String>>(); 
		String line;
		while ((line = reader.readLine()) != null) {
			Map<Integer, String> result = new HashMap<Integer, String>();
			String[] data = line.split(separator);
			for(int i = 0; i < data.length; i++){
				result.put(i, data[i]);
			}
			list.add(result);
		}
		
		return list;
	}	
	
}