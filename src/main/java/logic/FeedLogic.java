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
    public static final String HOST_ID = "hostId";
    
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
       return Arrays.asList(f.getId(),f.getPath(),f.getType(),f.getName());
    }

    @Override
    public Feed createEntity(Map<String, String[]> requestData) {
        Feed feed = new Feed();
        if(requestData.containsKey(ID)){
            feed.setId(Integer.parseInt(requestData.get(ID)[0]));
        }
        feed.setPath(requestData.get(PATH)[0]);
        feed.setType(requestData.get(TYPE)[0]);
        feed.setName(requestData.get(NAME)[0]);
        if(requestData.containsKey(HOST_ID)){
           feed.setHostid(new HostLogic().getHostWithName(requestData.get(HOST_ID)[0]));
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
    
}

/*
class FeedSupplier<Feed>{
    
    @Override
    public Feed get(){
        return dao().findById(id);
    }
}*/