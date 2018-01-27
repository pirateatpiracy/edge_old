package com.edge.udaf.complex;

import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public class SumInt extends AbstractGenericUDAFResolver {

	@Override
	public GenericUDAFEvaluator getEvaluator(TypeInfo[] info) throws SemanticException {
		
	
		if (info.length != 1) {
			throw new UDFArgumentTypeException(info.length - 1, "Exactly one argument is expected.");
		}
		
		
		if (info[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
			throw new UDFArgumentTypeException(0, "Only primitive type arguments are accepted but " + info[0].getTypeName() + " was passed as parameter 1.");
		}
		
		
		if (((PrimitiveTypeInfo)info[0]).getPrimitiveCategory() == PrimitiveCategory.STRING) {
			return new SumStringEvaluator();
		} else if (((PrimitiveTypeInfo)info[0]).getPrimitiveCategory() == PrimitiveCategory.INT) {
			return new SumIntEvaluator();
		} else {
			throw new UDFArgumentTypeException(0, "Only string, int type arguments are accepted but " + info[0].getTypeName() + " was passed as parameter 1.");
		}
	}
	
	
	/**
	 * 
	 * @author User
	 *
	 */
	public static class SumStringEvaluator extends GenericUDAFEvaluator {

		private PrimitiveObjectInspector inputOI;
		
		static class SumAggregationBuffer implements AggregationBuffer {
			int sum;
		}
		
		@Override
		public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
			super.init(m, parameters);
			
			inputOI = (PrimitiveObjectInspector) parameters[0];
			return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
		}
		
		@Override
		public AggregationBuffer getNewAggregationBuffer() throws HiveException {
			SumAggregationBuffer sum = new SumAggregationBuffer();
			reset(sum);
			return sum;
		}

		@Override
		public void reset(AggregationBuffer agg) throws HiveException {
			((SumAggregationBuffer) agg).sum = 0;
		}

		@Override
		public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {	
			if(parameters.length != 0 && inputOI.getPrimitiveJavaObject(parameters[0]) != null) {
				((SumAggregationBuffer) agg).sum += Integer.parseInt(inputOI.getPrimitiveJavaObject(parameters[0]).toString());
			}
		}

		@Override
		public Object terminatePartial(AggregationBuffer agg) throws HiveException {
			return ((SumAggregationBuffer) agg).sum;
		}

		@Override
		public void merge(AggregationBuffer agg, Object partial) throws HiveException {
			((SumAggregationBuffer) agg).sum += Integer.parseInt(inputOI.getPrimitiveJavaObject(partial).toString());
		}

		@Override
		public Object terminate(AggregationBuffer agg) throws HiveException {
			return ((SumAggregationBuffer) agg).sum;
		}
		
	}
	
	/** 
	 * 
	 * @author User
	 *
	 */
	public static class SumIntEvaluator extends GenericUDAFEvaluator {

		private IntObjectInspector inputOI;
		
		static class SumAggregationBuffer implements AggregationBuffer {
			int sum;
		}
		
		@Override
		public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
			super.init(m, parameters);
			
			inputOI = (IntObjectInspector) parameters[0];
			return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
		}
		
		@Override
		public AggregationBuffer getNewAggregationBuffer() throws HiveException {
			SumAggregationBuffer sum = new SumAggregationBuffer();
			reset(sum);
			return sum;
		}

		@Override
		public void reset(AggregationBuffer agg) throws HiveException {
			((SumAggregationBuffer) agg).sum = 0;
		}

		@Override
		public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
			((SumAggregationBuffer) agg).sum += inputOI.get(parameters[0]);
		}

		@Override
		public Object terminatePartial(AggregationBuffer agg) throws HiveException {
			return ((SumAggregationBuffer) agg).sum;
		}

		@Override
		public void merge(AggregationBuffer agg, Object partial) throws HiveException {
			((SumAggregationBuffer) agg).sum += inputOI.get(partial);
		}

		@Override
		public Object terminate(AggregationBuffer agg) throws HiveException {
			return ((SumAggregationBuffer) agg).sum;
		}
		
	}
}