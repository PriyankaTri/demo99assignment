package com.demo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TestConfig {

    Properties props;

    public TestConfig(){
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            props = new Properties();
            props.load(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUserName(){
        String uname =  props.getProperty("username");
        return uname;
    }

    public String getPassword(){
        String pass = props.getProperty("password");
        return pass;
    }

    public String getChromeDriver(){
        String chromepath = props.getProperty("chromepath");
        return chromepath;
    }

    public String getEdgeDriver(){
        String edgepath = props.getProperty("edgepath");
        return edgepath;
    }
}
