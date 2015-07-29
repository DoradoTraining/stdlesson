package qk.chapter10;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bstek.dorado.data.variant.Record;
import com.bstek.dorado.sql.iapi.AbstractStoreListener;
import com.bstek.dorado.sql.iapi.ISqlDao;
import com.bstek.dorado.sql.iapi.SingleRecordStoreArguments;

//qk.chapter10.DeptStoreListener
public class DeptStoreListener extends AbstractStoreListener {

	
	private void storeDepts(Record dept,ISqlDao dao){
		@SuppressWarnings("unchecked")
		Collection<Record> depts = (Collection<Record>) dept.get("depts");
		int deptId = dept.getInt("dept_id");
		if(depts != null){
			for(Record d: depts){
				d.setInt("parent_id", deptId);
			}
			dao.store(depts);
		}
		
	}
	@Override
	public void afterNone(SingleRecordStoreArguments arguments) {
		Record record = arguments.getData();
		ISqlDao dao = arguments.getSqlDao();
		storeDepts(record, dao);
	}
	
	@Override
	public void afterInsert(SingleRecordStoreArguments arguments) {
		Record record = arguments.getData();
		ISqlDao dao = arguments.getSqlDao();
		storeDepts(record, dao);
	}

	@Override
	public void afterUpdate(SingleRecordStoreArguments arguments) {
		Record record = arguments.getData();
		ISqlDao dao = arguments.getSqlDao();
		storeDepts(record, dao);
	}
	@Override
	public void beforeDelete(SingleRecordStoreArguments arguments) {
		Record dept = arguments.getData();
		int deptId = dept.getInt("dept_id");
		String sql = "delete from sl_employee where dept_id=:dept_id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", deptId);
		
		ISqlDao dao = arguments.getSqlDao();
		dao.getSqlOperations().update(sql, map);
	}


}
