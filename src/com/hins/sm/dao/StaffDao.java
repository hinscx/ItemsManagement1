package com.hins.sm.dao;

import com.hins.sm.domain.Staff;
import com.hins.sm.utils.JDBCUtils;
import com.hins.sm.domain.PageBean;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;


public class StaffDao {

        private QueryRunner qr= JDBCUtils.getQueryRunner();

        public List<Staff> findAll()
        {
            try{
                String sql="select * from staffs";
                return qr.query(sql,new BeanListHandler<>(Staff.class));
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }


        public PageBean<Staff> findAll(int pc, int pr) {
            try{

                PageBean<Staff> pb=new PageBean<>();
                pb.setPc(pc);
                pb.setPr(pr);

                String sql = "select count(*) from staffs";
                Number number = qr.query(sql,new ScalarHandler<>());

                int tr=number.intValue();
                pb.setTr(tr);

                sql="select * from staffs order by name limit ?,?";
                Object[] params={(pc-1)*pr,pr};
                List<Staff> beanList=qr.query(sql,new BeanListHandler<>(Staff.class),params);

                pb.setStaffList(beanList);

                return pb;
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        public void add(Staff s)
        {
            try {
                String sql = "insert into staffs (name, gender, phone, email, description) " +
                        "values(?,?,?,?,?)";
                Object[] params = {s.getName(), s.getGender(),
                        s.getPhone(), s.getEmail(), s.getDescription()};

                qr.update(sql, params);
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

    public Staff find(String id)
    {
        try {
            String sql = "select * from staffs where id=?";
            return qr.query(sql, new BeanHandler<Staff>(Staff.class), id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void edit(Staff staff)
    {
        try{
            String sql="update staffs set name=?,gender=?,phone=?,email=?,description=? where id=?";
            Object[] params={staff.getName(),staff.getGender(),staff.getPhone(),staff.getEmail(),staff.getDescription(),staff.getId()};

            qr.update(sql,params);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id)
    {
        try {
            String sql = "delete from staffs where id=?";

            qr.update(sql, id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


//    }

    public PageBean<Staff> query(Staff staff,int pc,int pr) {

        try {
            PageBean<Staff> pb = new PageBean<>();
            pb.setPc(pc);
            pb.setPr(pr);

            StringBuilder cntSql = new StringBuilder("select count(*) from staffs ");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String name = staff.getName();
            if (name != null && !name.trim().isEmpty()) {
                whereSql.append("and name like ?");
                params.add("%" + name + "%");
            }

            String gender = staff.getGender();
            if (gender != null && !gender.trim().isEmpty()) {
                whereSql.append("and gender=?");
                params.add(gender);
            }

            String phone = staff.getPhone();
            if (phone != null && !phone.trim().isEmpty()) {
                whereSql.append("and phone like ?");
                params.add("%" + phone + "%");
            }

            String email = staff.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                whereSql.append("and email like ?");
                params.add("%" + email + "%");
            }

            Number num = qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());

            int tr = num.intValue();
            pb.setTr(tr);

            StringBuilder sql = new StringBuilder("select * from staffs ");
            StringBuilder lmitSql = new StringBuilder(" limit ?,?");

            params.add((pc - 1) * pr);
            params.add(pr);

            List<Staff> beanList = qr.query(sql.append(whereSql).append(lmitSql).toString(), new BeanListHandler<Staff>(Staff.class), params.toArray());
            pb.setStaffList(beanList);

            return pb;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

