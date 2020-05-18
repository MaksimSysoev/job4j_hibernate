package ru.job4j.carStorage;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

public class ListOfAds extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();


    public String getModels(Brand brand) {
        String str = "";
        int count = 0;
        for (Model model : brand.getModels()) {
               if (count!=brand.getModels().size()-1) {
                   str = str + "{\"name\":\""+model.getName()+"\", \"brand\":\""+model.getBrand().getId()+"\"},";
               } else {
                   str = str + "{\"name\":\""+model.getName()+"\", \"brand\":\""+model.getBrand().getId()+"\"}";
               }
               count++;
        }
        return str;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("windows-1251");

        List<Pts> brands = logic.select("Brand");
        // Получение списка брендов и моделей
        String str="";
        int count = 0;
        for (int i = 0; i < brands.size(); i++) {
            Brand brand = (Brand) brands.get(i);
            String sub = getModels(brand);

            if (count!=brands.size()-1) {
                str = str + "{\"name\":\""+brands.get(i).getName()+"\", \"model\":["+sub+"]},";
            } else {
                str = str + "{\"name\":\""+brands.get(i).getName()+"\", \"model\":["+sub+"]}";
            }
            count++;
        }


        // Получение всех объявлений
        List<Pts> listAds = logic.select("Ads");
        String s = "";
        count= 0;
        for (int i = 0; i < listAds.size(); i++) {
            Ads ads = (Ads) listAds.get(i);
            if  (count!=listAds.size()-1)  {
                s = s + "{\"name\":\""+ads.getName()+"\", \"brend\":\""+ads.getBrand().getName()+"\", \"model\":\""+ads.getModel().getName()+"\",  \"date\":\""+ads.getCreated()+"\", \"active\":\""+ads.isActive()+"\"}, ";
            } else {
               s = s + "{\"name\":\""+ads.getName()+"\", \"brend\":\""+ads.getBrand().getName()+"\", \"model\":\""+ads.getModel().getName()+"\", \"date\":\""+ads.getCreated()+"\", \"active\":\""+ads.isActive()+"\"}";
            }
            count++;
        }

        String st="[" + s + "]";
        String js = "["+str+", "+st+"]";
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(js);
        writer.flush();
       // req.getRequestDispatcher("index.html").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

/***********НЕ РАБОТАЕТ! Просто хочу хоть что-то добавить в базу!***********/
        Ads ads = new Ads();
        ads.setName("Объявление 5");
        ads.setCreated(new Date());
        ads.setActive(true);
        ads.setBrand(new Brand(1));
        ads.setModel(new Model(1));
        ads.setBody(new Body(1));
        logic.add(ads);
        //doGet(req, resp);
    }


}
