package com.bstek.dorado.sample.standardlesson.BO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;










import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.data.variant.Record;
import com.bstek.dorado.sample.standardlesson.entity.SlEmployee;
import com.bstek.dorado.sample.standardlesson.hdao.SlEmployeeDao;

@Service
public class EmployeeBO {

	@Autowired
	private SlEmployeeDao employeeDao;
	
	//employeeBO#getAll
	@DataProvider
	public Collection<SlEmployee> getAll(){
		List<SlEmployee> employees = employeeDao.getAll();
		return employees;
	}
	@DataProvider
	//Map<String, Object> parameter
	public void getAll(Page<SlEmployee> page,Record parameter){
		if(parameter != null){
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SlEmployee.class);
			//员工姓名
			String employeeName = (String) parameter.get("employeeName");
			if(StringUtils.isNotEmpty(employeeName)){
				Criterion criterion = Restrictions.like("employeeName", employeeName, MatchMode.ANYWHERE);
				detachedCriteria.add(criterion);
			}
			
			//工资>=
			BigDecimal salary1 = parameter.getBigDecimal("salary1");
			if(salary1 != null){
				Criterion criterion = Restrictions.ge("salary", salary1);
				detachedCriteria.add(criterion);
			}
			
			BigDecimal salary2 = parameter.getBigDecimal("salary2");
			if(salary2 != null){
				Criterion criterion = Restrictions.le("salary", salary2);
				detachedCriteria.add(criterion);
			}
			employeeDao.find(page, detachedCriteria);
		}else{
			employeeDao.getAll(page);
			
		}
	}
	
	@DataProvider
	public void getAllByHql(Page<SlEmployee> page,Record parameter){
		if(parameter != null){
			StringBuilder hql = new StringBuilder(128);
			Map<String, Object> parameters = new HashMap<String, Object>();
			hql.append("from SlEmployee where 1=1");
			String employeeName = parameter.getString("employeeName");
			BigDecimal salary1 = parameter.getBigDecimal("salary1");
			BigDecimal salary2 = parameter.getBigDecimal("salary2");
			if(!StringUtils.isEmpty(employeeName)){
				hql.append(" and employeeName like:employeeName");
				parameters.put("employeeName", "%"+employeeName+"%");
			}
			if(salary1 != null){
				hql.append(" and salary >=:salary1");
				parameters.put("salary1", salary1);
			}
			if(salary2 != null){
				hql.append(" and salary <=:salary2");
				parameters.put("salary2", salary2);
			}
			employeeDao.find(page, hql.toString(), parameters);
		}else{
			employeeDao.getAll(page);
		}
	}
	
	@Transactional
	@DataResolver
	public void saveAll(Collection<SlEmployee> employees) {
		// for (SlEmployee e: employees){
		// EntityState state = EntityUtils.getState(e);
		// System.out.println(state);
		// }
		employeeDao.persistEntities(employees);
	}

	@Transactional
	@DataResolver
	// 可以被前台调用
	public void raiseSalary(SlEmployee employee) {
		BigDecimal salary = employee.getSalary();
		salary = salary.add(BigDecimal.valueOf(1000));
		employee.setSalary(salary);

		employeeDao.persistEntity(employee);

	}
	
}
