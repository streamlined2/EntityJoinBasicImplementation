package edu.training.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Value implements Iterable<Object>{
	
	private List<Object> list;
	
	public Value() {
		list = new LinkedList<>();
	}
	
	public <T> Value(T data) {
		this();
		list.add(data);
	}
	
	public Value(Value value) {
		list = new LinkedList<>(value.list);
	}
	
	public void add(Object o) {
		list.add(o);
	}
	
	public void addAll(Value extra) {
		list.add(extra.list);
	}
	
	@Override
	public String toString() {
		StringJoiner join = new StringJoiner(",","{","}");
		for(Object o:list) {
			join.add(o.toString());
		}
		return join.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return list.iterator();
	}

}
