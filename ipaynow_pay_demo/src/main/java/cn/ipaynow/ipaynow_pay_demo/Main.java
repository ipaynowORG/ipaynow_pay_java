package cn.ipaynow.ipaynow_pay_demo;


import cn.ipaynow.ipaynow_pay_demo.util.DevExit;
import cn.ipaynow.ipaynow_pay_demo.util.SpringHelper;

public class Main  {
    
//    public static final String serverIp4Test = "192.168.107.116";
    public static final String serverIp4Test = "127.0.0.1";
    
    
    public static SpringHelper springHelper;
    public static void main(String [] args){
        
        springHelper = new SpringHelper(
              new String[]{
                      "classpath*:config/context/demo.xml"}
              );
        DevExit.devExit();
    
    }
}
