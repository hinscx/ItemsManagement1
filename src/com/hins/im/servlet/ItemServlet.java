package com.hins.sm.servlet;

import com.hins.sm.service.itemService;
import com.hins.sm.domain.item;
import com.hins.sm.domain.PageBean;
import com.hins.sm.utils.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@WebServlet(name="itemServlet", urlPatterns="/itemServlet")
public class itemServlet extends BaseServlet {

    private itemService itemService = new itemService();

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

        PageBean<item> pb = itemService.findAll(pc, pr);
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
        item item = itemService.find(id);

        request.setAttribute("item", item);

        return "/jsp/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        item item = CommonUtils.toBean(request.getParameterMap(), item.class);

        itemService.edit(item);

        request.setAttribute("msg", "恭喜，编辑商品成功");
        return "/jsp/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        itemService.delete(id);

        request.setAttribute("msg", "恭喜，删除商品成功");

        return "/jsp/msg.jsp";
    }
    
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        item item = CommonUtils.toBean(request.getParameterMap(), item.class);


        itemService.add(item);

        request.setAttribute("msg", "恭喜，成功添加商品");

        return "/jsp/msg.jsp";
    }

    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        item item = CommonUtils.toBean(request.getParameterMap(), item.class);

        //item = encoding(item);

        int pc = getPc(request);
        int pr = 10;

        PageBean<item> pb = itemService.query(item, pc, pr);

        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        return "/jsp/list.jsp";

    }

//    private Item encoding(Item item) throws UnsupportedEncodingException {
//
//        String name = item.getName();
//        String gender = item.getGender();
//        String phone = item.getPhone();
//        String email = item.getEmail();
//
//        if (name != null && !name.trim().isEmpty()) {
//            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
//            item.setName(name);
//        }
//        if (gender != null && !gender.trim().isEmpty()) {
//            gender = new String(gender.getBytes("ISO-8859-1"), "utf-8");
//            item.setGender(gender);
//        }
//        if (phone != null && !phone.trim().isEmpty()) {
//            phone = new String(phone.getBytes("ISO-8859-1"), "utf-8");
//            item.setPhone(phone);
//        }
//        if (email != null && !email.trim().isEmpty()) {
//            email = new String(email.getBytes("ISO-8859-1"), "utf-8");
//            item.setEmail(email);
//        }
//        return item;
//    }
}
