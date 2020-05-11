package org.com.zlk.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Sub extends Base{

    int num;
    @Builder
    private Sub(int value, int num) {
        super(value);
        this.num = num;
    }
}
