package cn.itcast.hibernatetest;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/2 21:36<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateTransaction {
    @Test
    public void testTransaction(){

        Session session = null;
        Transaction transaction = null;
        try {
            //得到与本地线程绑定的session
            session = HibernateUtils.getSessionObject();
            transaction = session.beginTransaction();
            User user = new User();
            user.setUsername("赵坤");
            user.setPassword("123456");
            user.setAddress("西安");
            session.save(user);
//            int i = 10/0;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
//            session.close();
//            sessionFactory.close();
        }

    }
}
