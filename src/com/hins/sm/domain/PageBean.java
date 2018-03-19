package com.hins.sm.domain;

import java.util.List;


public class PageBean<Object>
{
    private int pc;//当前页码page code
    private int tr;//总纪录数total records
    private int pr;//每页纪录数page records
    private List<Object> staffList;//当前页的纪录
    private String url;//回传查询URL

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getTp()
    {
        int tp=tr/pr;
        return tr % pr == 0 ? tp : tp + 1 ;
    }



    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public List<Object> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Object> staffList) {
        this.staffList = staffList;
    }
}
