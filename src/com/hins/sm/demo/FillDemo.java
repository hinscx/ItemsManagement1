package com.hins.sm.demo;

import com.hins.sm.utils.CommonUtils;
import com.hins.sm.utils.JDBCUtils;
import com.hins.sm.dao.StaffDao;
import com.hins.sm.domain.Staff;

import org.apache.commons.dbutils.QueryRunner;

/**
 * 填充数据库
 */
public class FillDemo {

    private QueryRunner qr= JDBCUtils.getQueryRunner();


    
    public static void main(String[] args)
    {
        StaffDao staffDao=new StaffDao();

        for (int i=0;i<50;i++)
        {
            Staff staff=new Staff();
            staff.setName("staff"+i);
            staff.setGender(i%2==0?"male":"female");
            staff.setPhone("13476"+i);
            staff.setEmail("staff"+i+"@qq.com");
            staff.setDescription("Hello World");

            staffDao.add(staff);
        }
    }
}


