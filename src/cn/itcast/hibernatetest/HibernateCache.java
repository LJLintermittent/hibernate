package cn.itcast.hibernatetest;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/2 16:54<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateCache {
    @Test
    public void testCache(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class, 2);
        System.out.println(user1);
        User user2 = session.get(User.class, 2);
        System.out.println(user2);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
