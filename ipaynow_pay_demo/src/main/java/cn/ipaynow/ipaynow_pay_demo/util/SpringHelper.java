package cn.ipaynow.ipaynow_pay_demo.util;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringHelper {
    private AbstractApplicationContext ctx;
    
    
    private static String defaultPath = "classpath*:config/context/*.xml";
    
    
    public SpringHelper(){
        this(defaultPath);
    }
    public SpringHelper(String... path){
        getCtx(path);
    }
    
    public <T> T  getBean(Class<T> cls){
        return (T)ctx.getBean(cls);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T  getBean(Class<T> cls,String beanName){
        return (T)ctx.getBean(beanName);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T  getBean(Class<T> cls,String beanName,Object... args){
        return (T)ctx.getBean(beanName, args);
    }
    
    
    private void getCtx(String... path){
        if(ctx == null){
            synchronized(SpringHelper.class){
                if(ctx == null){
                    ctx = new ClassPathXmlApplicationContext(path);
                    ctx.registerShutdownHook();
                }
            }
        }
    }
    
}
