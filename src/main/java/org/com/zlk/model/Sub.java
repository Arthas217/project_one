package org.com.zlk.model;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Sub extends Base {

    int num;

    @Builder
    private Sub(int value, int num) {
        super(value);
        this.num = num;
    }

    @Override
    public String toString() {
        return "Sub{" +
                "num=" + num +
                ",value=" + getValue() +
                '}';
    }
}
