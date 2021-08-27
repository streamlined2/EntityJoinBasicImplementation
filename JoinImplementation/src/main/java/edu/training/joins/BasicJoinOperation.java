package edu.training.joins;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import edu.training.data.DataRow;
import edu.training.data.JoinedDataRow;
import edu.training.data.Value;

public abstract class BasicJoinOperation<
	K,V1 extends Value,V2 extends Value,D1 extends DataRow<K,V1>,D2 extends DataRow<K,V2>,R extends JoinedDataRow<K,V1,V2>>
implements JoinOperation<D1,D2,R> {
	
	protected abstract <T extends Value,A extends JoinedDataRow<K,?,?>> void keyMissing(Collection<A> result,K key,T value);

	protected <T1 extends Value,T2 extends Value,A extends JoinedDataRow<K,?,?>> void keyFound(Collection<A> result,K key,T1 value,T2 oppositeValue) {	
		result.add((A)new JoinedDataRow<K,T1,T2>(key,value,oppositeValue));		
	}
	
	protected <V extends Value,T extends DataRow<K,V>> Map<K,V> sortOperand(Collection<T> collection){
		Map<K,V> map = new TreeMap<>();
		for(T elem:collection) {
			map.put(elem.getKey(), elem.getValue());
		}
		return map;
	}
	
	protected <A1 extends Value,A2 extends Value,T extends DataRow<K,A2>,R extends JoinedDataRow<K,V1,V2>> void scanCollection(Collection<T> collection, Collection<R> result, Map<K, A1> map) {
		for(T row:collection) {
			if(map.containsKey(row.getKey())) {
				A1 oppositeValue = map.get(row.getKey());
				keyFound(result,row.getKey(),row.getValue(),oppositeValue);
			}else {
				keyMissing(result,row.getKey(),row.getValue());
			}
		}
	}

}
