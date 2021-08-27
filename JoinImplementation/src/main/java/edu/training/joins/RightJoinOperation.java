package edu.training.joins;

import java.util.Collection;
import java.util.HashSet;

import edu.training.data.DataRow;
import edu.training.data.JoinedDataRow;
import edu.training.data.Value;

public class RightJoinOperation<
	K,V1 extends Value,V2 extends Value,D1 extends DataRow<K,V1>,D2 extends DataRow<K,V2>,R extends JoinedDataRow<K,V1,V2>> 
extends BasicJoinOperation<K,V1,V2,D1,D2,R>{

	@Override
	protected <T extends Value,A extends JoinedDataRow<K,?,?>> void keyMissing(Collection<A> result,K key,T value) {
		result.add((A)new JoinedDataRow<K,V1,T>(key,null,value));
	}

	@Override
	public Collection<R> join(Collection<D1> left, Collection<D2> right) {
		Collection<R> result = new HashSet<>();
		scanCollection(right, result, sortOperand(left));
		return result;
	}

}
