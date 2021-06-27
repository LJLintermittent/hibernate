package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 15:58<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateQBC {
    //查询所有
    @Test
    public void testQueryAll(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
             sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> list = criteria.list();
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
            Criteria criteria = session.createCriteria(Customer.class);
//            criteria.add(Restrictions.eq("cid",1));
//            criteria.add(Restrictions.eq("custName","徐欣裕"));
            criteria.add(Restrictions.like("custName","%腾%"));
            List<Customer> list = criteria.list();
            for(Customer customer : list){
//                System.out.println(customer.getCid()+"::"+customer.getCustName());
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
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.addOrder(Order.desc("cid"));
            List<Customer> list = criteria.list();
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
            Criteria criteria = session.createCriteria(Customer.class);
            //开始位置计算公式：（当前页-1）*每页记录数
            criteria.setFirstResult(1);
            criteria.setMaxResults(2);

            List<Customer> list = criteria.list();
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
    //统计查询
    @Test
    public void testCountQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.setProjection(Projections.rowCount());
            Object o = criteria.uniqueResult();
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
    //离线查询
    @Test
    public void testDetachedQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            List<User> list = criteria.list();
            for(User user : list){
                System.out.println(user);
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
