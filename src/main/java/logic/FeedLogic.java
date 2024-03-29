/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.FeedDAO;
import entity.Feed;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zhang
 */
public class FeedLogic extends GenericLogic<Feed, FeedDAO>{
    
    public static final String ID = "id";
    public static final String PATH = "path";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String HOST_ID = "host_id";
    
    public FeedLogic(){
        super(new FeedDAO());
    }

    @Override
    public List<String> getColumnNames() {
       return Arrays.asList("id","path","type","name","Host_id");
    }

    @Override
    public List<String> getColumnCodes() {
       return Arrays.asList(ID,PATH,TYPE,NAME,HOST_ID);
    }

    @Override
    public List<?> extractDataAsList(Feed f) {
       return Arrays.asList(f.getId(),f.getPath(),f.getType(),f.getName(),f.getHostid().getId());
    }

    @Override
    public Feed createEntity(Map<String, String[]> requestData) {
        Feed feed = new Feed();
        if(requestData.containsKey(ID)){
            feed.setId(Integer.parseInt(requestData.get(ID)[0]));
        }
        if(requestData.containsKey(PATH)){
            if(requestData.get(PATH)[0].equals("")){
                throw new RuntimeException("Path should not be empty");
            }
            feed.setPath(requestData.get(PATH)[0]);
        }
            
        if(requestData.containsKey(TYPE)){
            feed.setType(requestData.get(TYPE)[0]);
        }else{
            throw new RuntimeException("Type should not be empty");
        }
        if(requestData.containsKey(NAME)){
            feed.setName(requestData.get(NAME)[0]);
        }else{
            throw new RuntimeException("Name should not be empty");
        }
        if(requestData.containsKey(HOST_ID)){
           feed.setHostid(new HostLogic().getWithId(Integer.parseInt(requestData.get(HOST_ID)[0])));
        }
        return feed;
    }

    @Override
    public List<Feed> getAll() {
        return get(()->dao().findAll());
    }

    @Override
    public Feed getWithId(int id) {
        /*throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. */
        return get(()->dao().findById(id));
    }
    
    public Feed getWithPath(String path){
        return get(()->dao().findByPath(path));
    }
}

/*
class FeedSupplier<Feed>{
    
    @Override
    public Feed get(){
        return dao().findById(id);
    }
}*/