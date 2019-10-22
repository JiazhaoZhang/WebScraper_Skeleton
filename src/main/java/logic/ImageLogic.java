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
        if(requestData.containsKey(ID)){
            image.setId(Integer.parseInt(requestData.get(ID)[0]));
        }
        if(requestData.containsKey(FEED_ID)){
            int feedId = Integer.parseInt(requestData.get(FEED_ID)[0]);
            FeedDAO feedDAO = new FeedDAO();
            Feed feed = feedDAO.findById(feedId);
            if(feed != null){
                image.setFeedid(feed);
            }
        }
        if(requestData.containsKey(NAME)){
            image.setName(requestData.get(NAME)[0]);
        }
        if(requestData.containsKey(PATH)){
            image.setPath(requestData.get(PATH)[0]);
        }
        if(requestData.containsKey(DATE)){
            image.setPath(requestData.get(DATE)[0]);
        }
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
}
