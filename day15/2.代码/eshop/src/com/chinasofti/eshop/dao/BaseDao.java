package com.chinasofti.eshop.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public abstract class BaseDao {
	static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	static QueryRunner qr = new QueryRunner(dataSource);
	static QueryRunner qr2 = new QueryRunner();
	
}
