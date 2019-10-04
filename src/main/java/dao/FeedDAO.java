/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Feed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zhang
 */
public class FeedDAO extends GenericDAO<Feed>{
    
    public FeedDAO() {
        super(Feed.class);
    }
    
    public List<Feed> findAll(){
        return findResults("Feed.findAll", null);
    }
    
    public Feed findById(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        return findResult("Feed.findById",map);
    }
    
    public Feed findByName(String name){
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        return findResult("Feed.findByName",map);
    }
}
