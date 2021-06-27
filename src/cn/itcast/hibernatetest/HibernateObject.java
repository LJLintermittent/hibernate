package cn.itcast.hibernatetest;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import java.net.SocketTimeoutException;
import java.util.Set;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/5 14:43<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateObject {
    //对象导航查询
    @Test
    public void testObjectQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
             sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, 6);
            Set<LinkMan> setLinkMan = customer.getSetLinkMan();
            if(setLinkMan.size() == 0){
                System.out.println("该客户没有联系人");
            }else {
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
