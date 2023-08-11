package org.com.zlk.chxg.basic.copy;

/**
 * @author 会游泳的蚂蚁
 * @description: 浅拷贝
 * @date 2023/8/11 11:39
 */
public class ShallowCopy {

    public static void main(String[] args) {
        Address address = new Address("China");
        Person person = new Person("Chxg", address);

        // 浅拷贝(对于基本类型复制，对于引用类型只复制对象的引用，而不复制引用所指向的对象本身)
        Person shallowPerson = new Person(person.getName(), person.getAddress());

        // 新对象的基本属性值和原始对象的基本属性值相同
        System.out.println("原始对象  基本类型 name值：" + person.getName());
        System.out.println("新对象    基本类型 name值：" + shallowPerson.getName());

        // 对于引用类型的属性，新对象和原始对象共享同一个引用
        System.out.println("修改前person的引用属性address引用值：" + person.getAddress());
        System.out.println("修改前shallowPerson的引用属性address引用值：" + shallowPerson.getAddress());
        System.out.println("新对象和原始对象address共享同一个引用 ，比较的是引用值相同:" + person.getAddress().equals(shallowPerson.getAddress()));

        //新对象和原始对象引用不同
        System.out.println("新对象和原始对象不共享引用，引用的值不同" + person.equals(shallowPerson));

        System.out.println("-----浅拷贝修改person值为： name:Bob-------------");
        System.out.println("-----浅拷贝修改person值为： city:Los Angeles-------------");
        person.setName("Bob");
        person.getAddress().setCity("Los Angeles");

        // shallowPerson的引用属性会受到影响
        System.out.println("shallowPerson的基本属性name不受到影响" + shallowPerson.getName());
        System.out.println("shallowPerson的引用属性city会受到影响" + shallowPerson.getAddress().getCity());

        System.out.println("person的引用属性address引用值：" + person.getAddress());
        System.out.println("shallowPerson的引用属性address引用值：" + shallowPerson.getAddress());
        System.out.println("shallowPerson的引用属性address不受到影响，引用值相同？" + shallowPerson.getAddress().equals(person.getAddress()));
        System.out.println("如果你希望此时比较两个Address对象的属性值是否相等，你需要在Address类中重写equals方法，并根据属性值来进行比较 todo....." );


    }
}
