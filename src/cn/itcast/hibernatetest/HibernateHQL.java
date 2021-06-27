package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 14:58<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateHQL {
    @Test
    public void testQueryAll(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer");
            List<Customer> list = query.list();
            for(Customer customer : list){
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
    //条件查询
    @Test
    public void testQueryConditions(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
//            Query query = session.createQuery("from Customer where cid = ? and custName = ?");
            Query query = session.createQuery("from Customer where custName like ?");
//            query.setParameter(0,1);
//            query.setParameter(1,"徐欣裕");
            query.setParameter(0,"%腾%");
            List<Customer> list = query.list();
            for(Customer customer : list){
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
    //排序查询
    @Test
    public void testSortQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer order by cid asc");//desc
            List<Customer> list = query.list();
            for(Customer customer : list){
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
    //分页查询
    @Test
    public void testLimitQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer");
            query.setFirstResult(1);
            query.setMaxResults(2);
            List<Customer> list = query.list();
            for(Customer customer : list){
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
    //投影查询
    @Test
    public void testShadowQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("select cid,custName from Customer");
            List<Object[]> list = query.list();
            for(Object[] object : list){
                System.out.println(Arrays.toString(object));
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
    //聚集函数
    @Test
    public void testFunction(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Customer");
            Object o = query.uniqueResult();
            Long obj = (Long) o;
            int count = obj.intValue();
            System.out.println(count);

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
