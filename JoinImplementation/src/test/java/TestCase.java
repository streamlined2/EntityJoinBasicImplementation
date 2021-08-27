import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.training.data.DataRow;
import edu.training.data.JoinedDataRow;
import edu.training.data.Value;
import edu.training.joins.InnerJoinOperation;
import edu.training.joins.LeftJoinOperation;
import edu.training.joins.RightJoinOperation;

class TestCase {
	
	private static Set<DataRow<Integer,Value>> leftCollection;
	private static Set<DataRow<Integer,Value>> rightCollection;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		leftCollection = Set.of(
				new DataRow(0,new Value("Ukraine")),
				new DataRow(1,new Value("Germany")),
				new DataRow(2,new Value("France"))
			);
		rightCollection = Set.of(
				new DataRow(0,new Value("Kyiv")),
				new DataRow(1,new Value("Berlin")),
				new DataRow(3,new Value("Budapest"))
			);
	}

	@Test
	void testInnerJoin() {
		Assert.assertEquals("inner join operation failed","[[0,{Ukraine,[Kyiv]}], [1,{Germany,[Berlin]}]]",new InnerJoinOperation<Integer, Value, Value, DataRow<Integer,Value>, DataRow<Integer,Value>, JoinedDataRow<Integer,Value,Value>>().join(leftCollection, rightCollection).toString());
	}

	@Test
	void testLeftJoin() {
		Assert.assertEquals("left outer join operation failed","[[0,{Ukraine,[Kyiv]}], [1,{Germany,[Berlin]}], [2,{France}]]",new LeftJoinOperation<Integer, Value, Value, DataRow<Integer,Value>, DataRow<Integer,Value>, JoinedDataRow<Integer,Value,Value>>().join(leftCollection, rightCollection).toString());
	}

	@Test
	void testRightJoin() {
		Assert.assertEquals("right outer join operation failed","[[0,{Kyiv,[Ukraine]}], [1,{Berlin,[Germany]}], [3,{[Budapest]}]]",new RightJoinOperation<Integer, Value, Value, DataRow<Integer,Value>, DataRow<Integer,Value>, JoinedDataRow<Integer,Value,Value>>().join(leftCollection, rightCollection).toString());
	}

}
