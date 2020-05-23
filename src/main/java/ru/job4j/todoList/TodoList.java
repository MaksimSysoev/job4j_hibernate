package ru.job4j.todoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class TodoList {
    /*
    private Service service = new Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("windows-1251");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/json");
        String descr = req.getParameter("descr");
        boolean done = Boolean.parseBoolean(req.getParameter("done"));
        Item item = new Item();
        item.setDescr(descr);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(done);
        service.add(item);
     // resp.sendRedirect(String.format("%s/list", req.getContextPath()));
     //   doGet(req, resp);
    }
    */
}
