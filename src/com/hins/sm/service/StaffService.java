package com.hins.sm.service;

import com.hins.sm.domain.PageBean;
import com.hins.sm.dao.StaffDao;
import com.hins.sm.domain.Staff;

import java.util.List;

public class StaffService {

    private StaffDao staffDao=new StaffDao();
    
    public List<Staff> findAll()
    {
        return staffDao.findAll();
    }

    public PageBean<Staff> findAll(int pc,int pr)
    {
        return staffDao.findAll(pc,pr);
    }

    public void add(Staff staff)
    {
        staffDao.add(staff);
    }

    public Staff find(String id)
    {
        return staffDao.find(id);
    }

    public void edit(Staff staff)
    {
        staffDao.edit(staff);
    }

    public void delete(String id)
    {
        staffDao.delete(id);
    }

    public PageBean<Staff> query(Staff staff,int pc,int pr)
    {
        return staffDao.query(staff,pc,pr);

    }
}
