package org.com.zlk.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@StudentTableAnnotation(tableName = "db_student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @StudentFieldAnnotation(columnName = "db_id",type = "int",length = 10)
    private int id;

    @StudentFieldAnnotation(columnName = "db_age",type = "int",length = 3)
    private int age;

    @StudentFieldAnnotation(columnName = "db_name",type = "varchar",length = 10)
    private String name;


}
