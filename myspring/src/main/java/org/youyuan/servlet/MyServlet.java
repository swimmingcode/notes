package org.youyuan.servlet;

import org.youyuan.service.SpringService;
import org.youyuan.service.impl.SpringServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/7 17:46
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    SpringService springService = new SpringServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(springService.getDate());
    }
}
