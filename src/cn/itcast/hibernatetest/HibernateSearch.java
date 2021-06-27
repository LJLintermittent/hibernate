package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 19:37<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateSearch {
    //立即查询
    @Test
    public void testSearchGet(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, 2);
            System.out.println(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    //延迟查询
    @Test
    public void testSearchLoad(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.load(User.class, 2);
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
}
