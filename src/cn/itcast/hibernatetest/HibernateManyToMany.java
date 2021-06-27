package cn.itcast.hibernatetest;

import cn.itcast.pojo.Person;
import cn.itcast.pojo.Role;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/4 22:05<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class HibernateManyToMany {
    @Test
    //多对多级联保存
    public void testSave(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
             sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();
             //添加两个用户，为每个用户添加两个角色
            Person person1 = new Person();
            person1.setPerson_name("李佳乐");
            person1.setPerson_password("123456");

            Person person2 = new Person();
            person2.setPerson_name("徐欣裕");
            person2.setPerson_password("123456");

            Role role1 = new Role();
            role1.setRole_name("总经理");
            role1.setRole_memo("总经理");

            Role role2 = new Role();
            role2.setRole_name("秘书");
            role2.setRole_memo("秘书");

            Role role3 = new Role();
            role3.setRole_name("产品经理");
            role3.setRole_memo("产品经理");
            //模拟多对多关系
            //person1 ---r1/r2
            person1.getSetRole().add(role1);
            person1.getSetRole().add(role2);
            //person2 ---r2/r3
            person2.getSetRole().add(role2);
            person2.getSetRole().add(role3);

            session.save(person1);
            session.save(person2);

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
    //多对多级联删除（一般不用）
    public void testDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Person person = session.get(Person.class, 1);
            session.delete(person);
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
    //维护第三张表
    public void testTable(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //让用户1增加一个角色3
//            Person person = session.get(Person.class, 1);
//            Role role = session.get(Role.class, 3);
//            person.getSetRole().add(role);
            //让用户2减少一个角色3
            Person person = session.get(Person.class, 2);
            Role role = session.get(Role.class, 3);
            person.getSetRole().remove(role);


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
