package edu.training.data;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class DataRow<K,V extends Value> implements Map.Entry<K,V>{
	
	private K key;
	private Value value;
	
	public DataRow(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return (V)value;
	}

	public V setValue(V value) {
		V oldValue = (V)this.value;
		this.value = value;
		return oldValue;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof DataRow dataRow) {
			return Objects.equals(key, dataRow.key);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(",","[","]").add(key.toString()).add(value.toString()).toString();
	}
	
}
