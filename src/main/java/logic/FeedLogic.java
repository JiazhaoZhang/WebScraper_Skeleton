/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.FeedDAO;
import entity.Feed;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getColumnCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> extractDataAsList(Feed e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Feed createEntity(Map<String, String[]> requestData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Feed> getAll() {
        return get(()->dao().findAll());
    }

    @Override
    public Feed getWithId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
