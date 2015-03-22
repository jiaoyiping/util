package com.jiaoyiping.util.java8;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA
 * USER: 焦一平
 * Date: 2015/2/15
 * Time: 17:08
 * To change this template use File | Settings | File Template
 */
public class StreamAPI {
    public static void main(String[] args) {
//        List<Person> personList = prepareDate();
//        //对集合的操作不需要再使用循环遍历了
//        Person p = personList.stream().filter(person->person.getAge()==5).findFirst().get();
//        System.out.println(p.getName());
    }
    static List<Person> prepareDate(){
        List<Person> personsList = new ArrayList<Person>();
        personsList.add(new Person("JIAO",5));
        personsList.add(new Person("BAI",20));

        return personsList;
    }

}
class   Person implements Serializable{
    static final long serialVersionUID = 42L;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;
}

