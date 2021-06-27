package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 20:01<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateBatch {
    //批量抓取
    @Test
    public void testBatch(){
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
                Set<LinkMan> setLinkMan = customer.getSetLinkMan();
                for(LinkMan linkMan : setLinkMan){
                    System.out.println(linkMan);
                }
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
