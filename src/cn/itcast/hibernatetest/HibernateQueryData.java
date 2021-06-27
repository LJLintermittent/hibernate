package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;
import sun.rmi.server.UnicastServerRef2;

import java.util.Arrays;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/4 11:02<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateQueryData {
    @Test
    public void testQuery(){
        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer");
            List<Customer> list = query.list();
            for(Customer customer  : list){
                System.out.println(customer);
            }

            transaction.commit();
        } catch (Exception e) {
          transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    @Test
    public void testCriteria(){
        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            List<User> list = criteria.list();
            for(User user : list){
                System.out.println(user);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
    @Test
    public void testSQLQuery(){
        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
            //把数组变成对象
            sqlQuery.addEntity(User.class);
            List<User> list = sqlQuery.list();
            for(User user : list){
                System.out.println(user);
            }
//            List<Object[]> list = sqlQuery.list();
//            for(Object[] objects :list){
//                System.out.println(Arrays.toString(objects));
//            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }

}
