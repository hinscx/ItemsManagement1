package com.hins.sm.servlet;

import com.hins.sm.service.StaffService;
import com.hins.sm.domain.Staff;
import com.hins.sm.domain.PageBean;
import com.hins.sm.utils.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@WebServlet(name="StaffServlet", urlPatterns="/StaffServlet")
public class StaffServlet extends BaseServlet {

    private StaffService staffService = new StaffService();

    /**
     * 处理"显示所有人员"的请求
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     *
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pc = getPc(request);

        int pr = 10;

        PageBean<Staff> pb = staffService.findAll(pc, pr);
        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);

        return "f:/jsp/list.jsp";
    }

    /**
     * 显示当前页码
     * @param request
     * @return
     */
    private int getPc(HttpServletRequest request) {

        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(value);
    }

    /**
     * 替换原URL中的pc值
     * 用于拼装分页中的超链接
     * @param request
     * @return
     */
    private String getUrl(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        if (queryString.contains("&pc=")) {
            int index = queryString.lastIndexOf("&pc=");
            queryString = queryString.substring(0, index);
        }

        return contextPath + servletPath + "?" + queryString;
    }


    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Staff staff = staffService.find(id);

        request.setAttribute("staff", staff);

        return "/jsp/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Staff staff = CommonUtils.toBean(request.getParameterMap(), Staff.class);

        staffService.edit(staff);

        request.setAttribute("msg", "恭喜，编辑员工成功");
        return "/jsp/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        staffService.delete(id);

        request.setAttribute("msg", "恭喜，删除员工成功");

        return "/jsp/msg.jsp";
    }
    
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Staff staff = CommonUtils.toBean(request.getParameterMap(), Staff.class);


        staffService.add(staff);

        request.setAttribute("msg", "恭喜，成功添加员工");

        return "/jsp/msg.jsp";
    }

    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Staff staff = CommonUtils.toBean(request.getParameterMap(), Staff.class);

        //staff = encoding(staff);

        int pc = getPc(request);
        int pr = 10;

        PageBean<Staff> pb = staffService.query(staff, pc, pr);

        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        return "/jsp/list.jsp";

    }

//    private Staff encoding(Staff staff) throws UnsupportedEncodingException {
//
//        String name = staff.getName();
//        String gender = staff.getGender();
//        String phone = staff.getPhone();
//        String email = staff.getEmail();
//
//        if (name != null && !name.trim().isEmpty()) {
//            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
//            staff.setName(name);
//        }
//        if (gender != null && !gender.trim().isEmpty()) {
//            gender = new String(gender.getBytes("ISO-8859-1"), "utf-8");
//            staff.setGender(gender);
//        }
//        if (phone != null && !phone.trim().isEmpty()) {
//            phone = new String(phone.getBytes("ISO-8859-1"), "utf-8");
//            staff.setPhone(phone);
//        }
//        if (email != null && !email.trim().isEmpty()) {
//            email = new String(email.getBytes("ISO-8859-1"), "utf-8");
//            staff.setEmail(email);
//        }
//        return staff;
//    }
}
