package org.com.zlk.annotation.lombook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Man extends Person {

    private String name;
    @Builder
    private Man(int age, int id, String name) {
        super(id, age);
        this.name = name;
    }
}
