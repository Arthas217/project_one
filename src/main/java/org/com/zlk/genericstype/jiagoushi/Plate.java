package org.com.zlk.genericstype.jiagoushi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 泛型类（盘子）
 * @author 会游泳的蚂蚁
 * @date 2024/1/24 12:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plate<T> {

    private T something;

}
