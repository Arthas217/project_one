package org.com.zlk.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class US extends User {

    private String name;
    @Builder
    private US(int age, int id, String name) {
        super(id, age);
        this.name = name;
    }
}
