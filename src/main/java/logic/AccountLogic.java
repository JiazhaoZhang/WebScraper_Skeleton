/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.AccountDAO;
import entity.Account;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 *
 * @author lenovo
 */
public class AccountLogic extends GenericLogic<Account, AccountDAO> {

    public static final String ID = "id";
    public static final String DISPLAY_NAME = "display_name";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    public AccountLogic() {
        super(new AccountDAO());
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("id", "display_name","user", "password");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID,DISPLAY_NAME,USER,PASSWORD);
    }

    @Override
    public List<?> extractDataAsList(Account account) {
       return Arrays.asList(account.getId(),account.getDisplayName(),account.getUser(),account.getPassword());
    }

    @Override
    public Account createEntity(Map<String, String[]> parameterMap) {
       Account a = new Account();
       if(parameterMap.containsKey(ID)){
           a.setId(Integer.parseInt(parameterMap.get(ID)[0]));
       }
       if(parameterMap.containsKey(DISPLAY_NAME)){
           a.setDisplayName(parameterMap.get(DISPLAY_NAME)[0]);
       }
       if(parameterMap.containsKey(USER)){
           //error checking
           if(parameterMap.get(USER)[0].length()==0 || parameterMap.get(USER)[0].length()>255 ){
               return null;
           }
           a.setUser(parameterMap.get(USER)[0]);
       }
       if(parameterMap.containsKey(PASSWORD)){
           a.setPassword(parameterMap.get(PASSWORD)[0]);
       }
       return a;
    }

    @Override
    public List<Account> getAll() {
        return this.get(()->dao().findAll());
    }

    @Override
    public Account getWithId(int id) {
        return this.get(()->dao().findById(id));
    }

}
