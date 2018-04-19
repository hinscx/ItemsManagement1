package com.hins.sm.utils;

import org.apache.commons.dbutils.QueryRunner;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

    private static ComboPooledDataSource dp = new ComboPooledDataSource();

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dp);
    }
}
