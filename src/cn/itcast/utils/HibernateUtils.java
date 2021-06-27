package cn.itcast.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/1 19:56<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateUtils {
    static Configuration configuration = null;
    static SessionFactory sessionFactory = null;

    static {
        //加载核心配置文件
         configuration = new Configuration();
         configuration.configure();
         sessionFactory = configuration.buildSessionFactory();
    }
    public static Session getSessionObject(){
        return sessionFactory.getCurrentSession();

    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;

    }

    public static void main(String[] args) {

    }
}
