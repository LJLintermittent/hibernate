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
public class Role {
    private Integer role_id;
    private String role_name;
    private String role_memo;
    //一个角色可以对应多个人
    private Set<Person> setPerson = new HashSet<Person>();

    public Set<Person> getSetPerson() {
        return setPerson;
    }

    public void setSetPerson(Set<Person> setPerson) {
        this.setPerson = setPerson;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_memo() {
        return role_memo;
    }

    public void setRole_memo(String role_memo) {
        this.role_memo = role_memo;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_memo='" + role_memo + '\'' +
                '}';
    }
}
