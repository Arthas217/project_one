package org.com.zlk.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@TableZlk(tableName = "db_student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @FieldZlk(columnName = "db_id",type = "int",length = 10)
    private int id;

    @FieldZlk(columnName = "db_age",type = "int",length = 3)
    private int age;

    @FieldZlk(columnName = "db_name",type = "varchar",length = 10)
    private String name;


}
