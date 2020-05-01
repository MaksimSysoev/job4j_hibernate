package ru.job4j.todoList;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

public class TodoList extends HttpServlet {
    private Service service = new Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("windows-1251");

        List<Item> items = service.select();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, items);
        writer.flush();

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
}
