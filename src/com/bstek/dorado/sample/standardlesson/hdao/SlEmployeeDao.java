package com.bstek.dorado.sample.standardlesson.hdao;

import org.springframework.stereotype.Repository;

import com.bstek.dorado.hibernate.HibernateDao;
import com.bstek.dorado.sample.standardlesson.entity.SlEmployee;

@Repository
public class SlEmployeeDao extends HibernateDao<SlEmployee, Integer> {

}
