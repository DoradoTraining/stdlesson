package qk.chapter06;

import java.util.Collection;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.variant.Record;
import com.bstek.dorado.sql.iapi.AbstractStoreListener;
import com.bstek.dorado.sql.iapi.CollectionRecordsArguments;
import com.bstek.dorado.sql.iapi.SingleRecordStoreArguments;

public class EmpStoreListener extends AbstractStoreListener {

	@Override
	public void beforeStore(CollectionRecordsArguments arguments) {
		Collection<Record> records = arguments.getData();
		for(Record r : records){
			EntityState state = EntityUtils.getState(r);
			String name = r.getString("EMPLOYEE_NAME");
			switch (state) {
			case NEW:
				System.out.println("即将被insert-> "+name);
				break;
			case MODIFIED:
				System.out.println("即将被update-> "+name);
				break;
			case DELETED:
				System.out.println("即将被delete-> "+name);
				break;
			case NONE:
				System.out.println("不被处理-> "+name);
				break;
			default:
				break;
			}
			System.out.println();
		}
	}
	@Override
	public void afterUpdate(SingleRecordStoreArguments arguments) {
		Record record = arguments.getData();
		System.out.println("修改 ：" + record.getString("EMPLOYEE_NAME"));
	}

	
}
