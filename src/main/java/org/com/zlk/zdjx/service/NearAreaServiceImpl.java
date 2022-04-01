package org.com.zlk.zdjx.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/3/10 16:48
 */
@Service
public class NearAreaServiceImpl implements NearAreaService {

    @Autowired
    RedisService redisService;

    @Autowired
    GilService gilService;

    @Override
    public JSONObject getZoneBusiness(String cityCode) {
        String zoneBusiness = redisService.getZoneBusiness(cityCode);
        if (StringUtils.isNotBlank(zoneBusiness)) {
            return JSONObject.parseObject(zoneBusiness);
        }
        String zoneByCityCode = gilService.selectZoneByCityCode(cityCode);
        String hotBusinessByCityCode = gilService.selectHotBusinessByCityCode(cityCode);
        String storeActivityByCityCode = gilService.selectStoreActivityByCityCode(cityCode);

        warpperZoneHot(zoneByCityCode,hotBusinessByCityCode,storeActivityByCityCode);

        return null;
    }

    private void warpperZoneHot(String zoneByCityCode, String hotBusinessByCityCode, String storeActivityByCityCode) {

    }
}
