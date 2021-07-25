/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.model;

/**
 *
 * @author Rapiii
 */
public class LoginModel {
    
    //deklarasi variable
    private static String username;
    private static String password;
    
    public LoginModel(){
        
    }
    //melakukan getter and setter
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginModel.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginModel.password = password;
    }
    
    
}
