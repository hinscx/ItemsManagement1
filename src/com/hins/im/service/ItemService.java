package com.hins.sm.service;

import com.hins.sm.domain.PageBean;
import com.hins.sm.dao.ItemDao;
import com.hins.sm.domain.Item;

import java.util.List;

public class ItemService {

    private ItemDao itemDao=new ItemDao();
    
    public List<Item> findAll()
    {
        return itemDao.findAll();
    }

    public PageBean<Item> findAll(int pc,int pr)
    {
        return itemDao.findAll(pc,pr);
    }

    public void add(Item item)
    {
        itemDao.add(item);
    }

    public Item find(String id)
    {
        return itemDao.find(id);
    }

    public void edit(Item item)
    {
        itemDao.edit(item);
    }

    public void delete(String id)
    {
        itemDao.delete(id);
    }

    public PageBean<Item> query(Item item,int pc,int pr)
    {
        return itemDao.query(item,pc,pr);

    }
}
