package org.com.zlk.zdjx.service;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/3/10 17:15
 */
public interface GilService {

    String selectZoneByCityCode(String cityCode);

    String selectHotBusinessByCityCode(String cityCode);

    String selectStoreActivityByCityCode(String cityCode);
}
