/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.FeedDAO;
import dao.ImageDAO;
import entity.Feed;
import entity.Image;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ImageLogic extends GenericLogic<Image,ImageDAO>{
    
    public static final String ID = "id";
    public static final String FEED_ID = "Feed_id";
    public static final String NAME = "name";
    public static final String PATH = "path";
    public static final String DATE = "date";
    
    public ImageLogic() {
        super(new ImageDAO());
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("id", "Feed_id","name","path","date");
    }

    @Override
    public List<String> getColumnCodes() {
       return Arrays.asList(ID, FEED_ID, NAME, PATH, DATE);
    }

    @Override
    public List<?> extractDataAsList(Image image) {
        return Arrays.asList(image.getId(),image.getFeedid().getId(),image.getName(),image.getPath(),image.getDate());
    }

    @Override
    public Image createEntity(Map<String, String[]> requestData) {
        Image image = new Image();
        if(requestData.containsKey(ImageLogic.ID)){
            image.setId(Integer.parseInt(requestData.get(ImageLogic.ID)[0]));
        }
        if(requestData.containsKey(ImageLogic.FEED_ID)){
            int feedId = Integer.parseInt(requestData.get(ImageLogic.FEED_ID)[0]);
            FeedDAO feedDAO = new FeedDAO();
            Feed feed = feedDAO.findById(feedId);
            if(feed != null){
                image.setFeedid(feed);
            }else{
                image.setFeedid(new Feed(1));
            }
        }
        if(requestData.containsKey(ImageLogic.NAME)){
            image.setName(requestData.get(ImageLogic.NAME)[0]);
        }
        if(requestData.containsKey(ImageLogic.PATH)){
            image.setPath(requestData.get(ImageLogic.PATH)[0]);
        }
        image.setDate(new Date(System.currentTimeMillis()));
        return image;
    }

    @Override
    public List<Image> getAll() {
        return get(()->dao().findAll());
    }

    @Override
    public Image getWithId(int id) {
        return get(()->dao().findByID(id));
    }
    
    public List<Image> getWithFeedId(int feedId){
        return get(()->dao().findByFeedId(feedId));
    }
    
    public List<Image> getWithName(String name){
        return get(()->dao().findByName(name));
    }
    
    public Image getWithPath(String path){
        return get(()->dao().findByPath(path));
    }
    
    public List<Image> getWithDate(String date){
        return get(()->dao().findByDate(date));
    }
    
    @Override
    public List<Image> search(String search) {
       return get(()->dao().findContaining(search));
    }
    
    public void addImage(Map<String, String[]> parameterMap){
       Image image = this.createEntity(parameterMap);
       this.add(image);
    }
}
