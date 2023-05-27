package org.com.zlk.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 表student对象
 */
@StudentTableAnnotation(tableName = "db_student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @StudentFieldAnnotation(columnName = "db_id", type = "int", length = 10)
    private int id;

    @StudentFieldAnnotation(columnName = "db_age", type = "int", length = 3)
    private int age;

    @StudentFieldAnnotation(columnName = "db_name", type = "varchar", length = 10)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
