package cn.itcast.hibernatetest;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/1 19:24<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateDemo {
    @Test
    public void testAdd(){
//        Configuration cfg = new Configuration();
//        cfg.configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUsername("李佳乐");
        user.setPassword("123456");
        user.setAddress("西安");
//        User user1 = new User();
//        user1.setUsername("徐欣裕");
//        user1.setPassword("123456");
//        user1.setAddress("西安");
        session.save(user);
//        session.save(user1);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testQueryById(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 2);
        System.out.println(user);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }
    @Test
    public void testUpdate(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 2);
        user.setPassword("520520");
        session.update(user);
        System.out.println(user);
        transaction.commit();
        session.close();
        sessionFactory.close();


    }
    @Test
    public void testDelete(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1);
        session.delete(user);
//        User user = new User();
//        user.setId(2);
//        session.delete(user);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
