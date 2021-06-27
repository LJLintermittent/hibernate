package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/4 16:51<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
//一对多级联保存
public class HibernateOneToMany {
     @Test
    public void testAddDemo1(){
         SessionFactory sessionFactory = null;
         Session session = null;
         Transaction transaction = null;
         try {
             sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
              transaction = session.beginTransaction();
             Customer customer = new Customer();
             customer.setCustName("徐欣裕");
             customer.setCustLevel("vip");
             customer.setCustSource("西安");
             customer.setCustPhone("123456");
             customer.setCustMobile("123456");

             LinkMan linkMan = new LinkMan();
             linkMan.setLkm_name("李佳乐");
             linkMan.setLkm_gender("男");
             linkMan.setLkm_phone("123456");

             customer.getSetLinkMan().add(linkMan);
             linkMan.setCustomer(customer);
             session.save(customer);
             session.save(linkMan);

             transaction.commit();
         } catch (HibernateException e) {
             e.printStackTrace();
             transaction.rollback();
         }finally {
             session.close();
             sessionFactory.close();
         }


     }
     @Test
    public void testAddDemo2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
//            Customer customer = new Customer();
//            customer.setCustName("腾讯");
//            customer.setCustLevel("vip");
//            customer.setCustSource("深圳");
//            customer.setCustPhone("147258");
//            customer.setCustMobile("147258");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("小张");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("123456");
//            customer.getSetLinkMan().add(linkMan);
//            session.save(customer);
            session.save(linkMan);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }


    }
    @Test
    public void testDeleteDemo(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, 3);
            session.delete(customer);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }


    }
    @Test
    public void testUpdate(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, 6);
            LinkMan linkMan = session.get(LinkMan.class, 1);
            customer.getSetLinkMan().add(linkMan);
            linkMan.setCustomer(customer);

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
