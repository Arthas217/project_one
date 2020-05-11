package org.com.zlk.annotation.lombook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 4818918354326165041L;
    private Integer id;
    private Integer age;
}
