<%-- 
    Document   : ImageView
    Created on : 2019-10-22, 23:42:55
    Author     : lenovo
--%>


<%@page import="entity.Image"%>
<%@page import="java.util.List"%>
<%@page import="scraper.Sort"%>
<%@page import="scraper.Scraper"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="logic.ImageLogic"%>
<%@page import="scraper.Post"%>
<%@page import="java.util.function.Consumer"%>
<%@page import="common.FileUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ImageView Page</title>
    </head>
    <body>
        <h1>This is ImageView!</h1>
        <%
            String destinationDir = System.getProperty("user.home")+"\\Documents\\Reddit Images\\";
            FileUtility.createDirectory(destinationDir);
             //create a lambda that accepts post
            Consumer<Post> saveImage = (Post post) -> {
            //if post is an image and SFW
            if (post.isImage() && !post.isOver18()) {
                //get the url for the image which is unique
                String url = post.getUrl();
                //get img name
                String fileName = FileUtility.getFileName(url);
                //the path of img
                String imgSrc = destinationDir+fileName;
                //check if an img file has already exist
                ImageLogic imageLogic = new ImageLogic();
                //check and see if an image already exists
                if(imageLogic.getWithPath(imgSrc)==null){
                    //save it in img directory
                    Map<String, String[]> newImageParam = new HashMap<>();
                    newImageParam.put(ImageLogic.FEED_ID, new String[]{"4"});
                    newImageParam.put(ImageLogic.NAME, new String[]{fileName});
                    newImageParam.put(ImageLogic.PATH, new String[]{imgSrc});
                    imageLogic.addImage(newImageParam);
                    FileUtility.downloadAndSaveFile(url, destinationDir);
                }     
             }
             };
            
              //create a new scraper
            Scraper scrap = new Scraper();
            //authenticate and set up a page for wallpaper subreddit with 5 posts soreted by HOT order
            scrap.authenticate().buildRedditPagesConfig("Wallpaper", 5, Sort.HOT);
            //get the next page 3 times and save the images.
            scrap.requestNextPage().proccessNextPage(saveImage);
            scrap.requestNextPage().proccessNextPage(saveImage);
            scrap.requestNextPage().proccessNextPage(saveImage);
            
            ImageLogic imageLogic = new ImageLogic();
            List<Image> images = imageLogic.getAll();
            for(Image image:images){
                String imgPath = FileUtility.getFileName(image.getPath());
                out.println("<h3>"+imgPath+"</h3>");
                out.println("<div><img style='height:200px;width:300px;' src=\"ImageDelivery?path="+imgPath+"\" alt=\"Image Not Found\"/></div>");
            }
        %>
    </body>
</html>
