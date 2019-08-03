package com.chinasofti.eshop.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public abstract class BaseDao {
	static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
	static QueryRunner qr2 = new QueryRunner();
	
}
