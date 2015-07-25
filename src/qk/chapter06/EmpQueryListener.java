package qk.chapter06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bstek.dorado.data.variant.Record;
import com.bstek.dorado.sql.iapi.AbstractQueryListener;
import com.bstek.dorado.sql.iapi.QueryArguments;

public class EmpQueryListener extends AbstractQueryListener {

//	public void beforeQuery(QueryArguments arguments) {
//		//demoPreventDefault(arguments);
//		Record parameter = (Record) arguments.getParameter();
//		parameter.set("EMPLOYEE_NAME", "李四");
//	}
//	
//	private void demoPreventDefault(QueryArguments arguments){
//		List<Record> records = new ArrayList<Record>();
//		for(int i=0;i<10;i++){
//			Record r = new Record();
//			r.set("EMPLOYEE_ID", i);
//			records.add(r);
//		}
//		arguments.setRecords(records);
//		arguments.preventDefault();
//	}
	
	public void afterQuery(QueryArguments arguments){
		Collection<Record> records = arguments.getRecords();
		for(Record r : records){
			String name = r.getString("EMPLOYEE_NAME");
			System.out.println(":::"+name);
		}
	}

}
