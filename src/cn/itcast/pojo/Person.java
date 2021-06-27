package cn.itcast.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/4 21:36<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Person {
    private Integer person_id;
    private String person_name;
    private String person_password;
    //一个人可以有多个角色
    private Set<Role> setRole = new HashSet<Role>();

    public Set<Role> getSetRole() {
        return setRole;
    }

    public void setSetRole(Set<Role> setRole) {
        this.setRole = setRole;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_password() {
        return person_password;
    }

    public void setPerson_password(String person_password) {
        this.person_password = person_password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                ", person_password='" + person_password + '\'' +
                '}';
    }
}
