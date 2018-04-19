package com.hins.sm.dao;

import com.hins.sm.domain.Item;
import com.hins.sm.utils.JDBCUtils;
import com.hins.sm.domain.PageBean;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;


public class ItemDao {

        private QueryRunner qr= JDBCUtils.getQueryRunner();

        public List<Item> findAll()
        {
            try{
                String sql="select * from items";
                return qr.query(sql,new BeanListHandler<>(Item.class));
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }


        public PageBean<Item> findAll(int pc, int pr) {
            try{

                PageBean<Item> pb=new PageBean<>();
                pb.setPc(pc);
                pb.setPr(pr);

                String sql = "select count(*) from items";
                Number number = qr.query(sql,new ScalarHandler<>());

                int tr=number.intValue();
                pb.setTr(tr);

                sql="select * from items order by name limit ?,?";
                Object[] params={(pc-1)*pr,pr};
                List<Item> beanList=qr.query(sql,new BeanListHandler<>(Item.class),params);

                pb.setItemList(beanList);

                return pb;
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        public void add(Item s)
        {
            try {
                String sql = "insert into items (name, gender, phone, email, description) " +
                        "values(?,?,?,?,?)";
                Object[] params = {s.getName(), s.getGender(),
                        s.getPhone(), s.getEmail(), s.getDescription()};

                qr.update(sql, params);
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

    public Item find(String id)
    {
        try {
            String sql = "select * from items where id=?";
            return qr.query(sql, new BeanHandler<Item>(Item.class), id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void edit(Item item)
    {
        try{
            String sql="update items set name=?,gender=?,phone=?,email=?,description=? where id=?";
            Object[] params={item.getName(),item.getGender(),item.getPhone(),item.getEmail(),item.getDescription(),item.getId()};

            qr.update(sql,params);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id)
    {
        try {
            String sql = "delete from items where id=?";

            qr.update(sql, id);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


//    }

    public PageBean<Item> query(Item item,int pc,int pr) {

        try {
            PageBean<Item> pb = new PageBean<>();
            pb.setPc(pc);
            pb.setPr(pr);

            StringBuilder cntSql = new StringBuilder("select count(*) from items ");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String name = item.getName();
            if (name != null && !name.trim().isEmpty()) {
                whereSql.append("and name like ?");
                params.add("%" + name + "%");
            }

            String gender = item.getGender();
            if (gender != null && !gender.trim().isEmpty()) {
                whereSql.append("and gender=?");
                params.add(gender);
            }

            String phone = item.getPhone();
            if (phone != null && !phone.trim().isEmpty()) {
                whereSql.append("and phone like ?");
                params.add("%" + phone + "%");
            }

            String email = item.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                whereSql.append("and email like ?");
                params.add("%" + email + "%");
            }

            Number num = qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());

            int tr = num.intValue();
            pb.setTr(tr);

            StringBuilder sql = new StringBuilder("select * from items ");
            StringBuilder lmitSql = new StringBuilder(" limit ?,?");

            params.add((pc - 1) * pr);
            params.add(pr);

            List<Item> beanList = qr.query(sql.append(whereSql).append(lmitSql).toString(), new BeanListHandler<Item>(Item.class), params.toArray());
            pb.setItemList(beanList);

            return pb;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

