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
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CarStorage  extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    public String getModels(Brand brand) {
        String str = "";
        int count = 0;
        for (Model model : brand.getModels()) {
            if (count!=brand.getModels().size()-1) {
                str = str + "{\"id\":\""+model.getId()+"\", \"name\":\""+model.getName()+"\", \"brand\":\""+model.getBrand().getId()+"\"},";
            } else {
                str = str + "{\"id\":\""+model.getId()+"\", \"name\":\""+model.getName()+"\", \"brand\":\""+model.getBrand().getId()+"\"}";
            }
            count++;
        }
        return str;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("windows-1251");
        List<Pts> listBrands = logic.select("Brand");

        // Получение списка брендов
        String strBrands="";
        int count = 0;
        for (int i = 0; i < listBrands.size(); i++) {
            Brand brand = (Brand) listBrands.get(i);
            String sub = getModels(brand);

            if (count!=listBrands.size()-1) {
                strBrands = strBrands + "{\"id\":\""+brand.getId()+"\", \"name\":\""+brand.getName()+"\", \"model\":["+sub+"]},";
            } else {
                strBrands = strBrands + "{\"id\":\""+brand.getId()+"\", \"name\":\""+brand.getName()+"\", \"model\":["+sub+"]}";
            }
            count++;
        }


        // Получение списка объявлений
        List<Pts> listAds = logic.select("Ads");
        String strAds = "";
        count= 0;
        for (int i = 0; i < listAds.size(); i++) {
            Ads ads = (Ads) listAds.get(i);
            String img = ads.getPhotos().iterator().next().getName();

            if  (count!=listAds.size()-1)  {
                strAds = strAds + "{\"id\":\""+ads.getId()+"\", \"name\":\""+ads.getName()+"\", \"brend\":\""+ads.getBrand().getName()+"\", \"model\":\""+ads.getModel().getName()+"\", \"img\":\""+img+"\",  \"date\":\""+ads.getCreated()+"\", \"active\":\""+ads.isActive()+"\", \"brandid\":\""+ads.getBrand().getId()+"\"}, ";
            } else {
                strAds = strAds + "{\"id\":\""+ads.getId()+"\", \"name\":\""+ads.getName()+"\", \"brend\":\""+ads.getBrand().getName()+"\", \"model\":\""+ads.getModel().getName()+"\", \"img\":\""+img+"\",  \"date\":\""+ads.getCreated()+"\", \"active\":\""+ads.isActive()+"\", \"brandid\":\""+ads.getBrand().getId()+"\"}";
            }
            count++;
        }

        strAds = "[" + strAds + "]";
        String js = "["+strBrands+", "+strAds+" ]";
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(js);
        writer.flush();


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
       if (req.getParameter("brand")!=null & req.getParameter("model")!=null & req.getParameter ("body")!=null & req.getParameter("ads")!=null) {

           int brand_id = Integer.parseInt(req.getParameter("brand"));
           int model_id = Integer.parseInt(req.getParameter("model"));
           int body_id = Integer.parseInt(req.getParameter("body"));
           String txt = req.getParameter("ads");
           String img = req.getParameter("file");

            Brand brand = new Brand(brand_id);
            Model model = new Model(model_id);
            Body body = new Body(body_id);

            Ads ads = new Ads();
            ads.setName(txt);
            ads.setCreated(new Date());
            ads.setActive(true);
            ads.setBrand(brand);
            ads.setModel(model);
            ads.setBody(body);
            logic.add(ads);

            Photo photo = new Photo();
            if (!img.isEmpty()) {
                photo.setName(img);
            } else {
                photo.setName("no-photo");
            }
            photo.setAds(new Ads(ads.getId()));
            logic.add(photo);

            /*
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                File folder = new File("images");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        File file = new File(folder + File.separator + item.getName());
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().read());
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            */
        }

        if (req.getParameter("active")!=null) {
            Boolean active = Boolean.parseBoolean(req.getParameter("active"));
            int id = Integer.parseInt(req.getParameter("id"));
            List<Pts> list = logic.select("Ads");

            for (int i = 0; i < list.size(); i++) {
                if (id == list.get(i).getId()) {
                    Ads ads = (Ads) list.get(i);
                    ads.setActive(active);
                    ads.setCreated(new Date());
                    logic.update(ads);
                    break;
                }
            }
        }

        doGet(req, resp);
    }

  }
