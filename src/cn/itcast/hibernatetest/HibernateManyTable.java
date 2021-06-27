package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 19:06<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateManyTable {
    //HQL内连接查询（得到的数据是数组）
    @Test
    public void testInner(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
             sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer c inner join c.setLinkMan");
            List<Object[]> list = query.list();
            for (Object[] objects : list){
                System.out.println(Arrays.toString(objects));
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    //HQL迫切内连接查询(得到的数据是对象)
    @Test
    public void testInnerFetch(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
            List<Customer> list = query.list();
            for(Customer customer :list){
                System.out.println(customer);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    //HQL左外连接查询（得到的数据是数组）
    @Test
    public void testLeftOuter(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
            List<Object[]> list = query.list();
            for (Object[] objects : list){
                System.out.println(Arrays.toString(objects));
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    //HQL右外连接查询（得到的数据是数组）
    @Test
    public void testRightOuter(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer c right outer join c.setLinkMan");
            List<Object[]> list = query.list();
            for (Object[] objects : list){
                System.out.println(Arrays.toString(objects));
            }

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
