package org.com.zlk.chxg.basic.copy;

/**
 * @author 会游泳的蚂蚁
 * @description: 深拷贝
 * @date 2023/8/11 11:39
 */
public class DeepCopy {

    public static void main(String[] args) {

        Address address = new Address("China");
        Person person = new Person("Chxg", address);

        // 深拷贝(对于基本类型复制，同事对于引用类型复制引用所指向的对象本身)
        Address deepCopiedAddress = new Address(person.getAddress().getCity());
        Person deepPerson = new Person(person.getName(), deepCopiedAddress);

        // 新对象的基本属性值和原始对象的基本属性值相同
        System.out.println("原始对象  基本类型 name值：" + person.getName());
        System.out.println("新对象    基本类型 name值：" + deepPerson.getName());

        // 对于引用类型的属性，新对象和原始对象共享同一个引用
        System.out.println("修改前person的引用属性address引用值：" + person.getAddress());
        System.out.println("修改前deepPerson的引用属性address引用值：" + deepPerson.getAddress());
        System.out.println("新对象和原始对象address独立 ，比较的是引用值不相同:" + person.getAddress().equals(deepPerson.getAddress()));

        //新对象和原始对象引用不同
        System.out.println("新对象和原始对象不同享引用，引用的值不同" + person.equals(deepPerson));

        System.out.println("-----深拷贝修改person值为： name:Bob-------------");
        System.out.println("-----深拷贝修改person值为： city:Los Angeles-------------");
        person.setName("Bob");
        person.getAddress().setCity("Los Angeles");

        // deepPerson的引用属性不会受到影响
        System.out.println("deepPerson的基本属性name不受到影响" + deepPerson.getName());
        System.out.println("deepPerson的引用属性city不受到影响" + deepPerson.getAddress().getCity());

        System.out.println("person的引用属性address引用值：" + person.getAddress());
        System.out.println("deepPerson的引用属性address引用值：" + deepPerson.getAddress());
        System.out.println("deepPerson的引用属性address和person引用属性address独立，引用值相同？" + deepPerson.getAddress().equals(person.getAddress()));


    }
}
