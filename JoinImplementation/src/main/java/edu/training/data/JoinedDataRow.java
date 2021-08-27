package edu.training.data;

public class JoinedDataRow<K,V1 extends Value,V2 extends Value> extends DataRow<K,Value>{

	public JoinedDataRow(K key,V1 leftValue,V2 rightValue){
		super(key, leftValue);
		Value value = (leftValue==null)? new Value() : new Value(leftValue);
		if(rightValue != null) value.addAll(rightValue);
		setValue(value);
	}
		
}
