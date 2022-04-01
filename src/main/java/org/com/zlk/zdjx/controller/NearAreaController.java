package org.com.zlk.zdjx.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.com.zlk.zdjx.service.NearAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 附近优惠
 * @Date 2022/3/10 16:46
 */
@RestController
@RequestMapping(value = "/index")
public class NearAreaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NearAreaController.class);

    @Autowired
    private NearAreaService nearAreaService;

    @RequestMapping(value = "/select/zoneBusiness/")
    public JSONObject selectZoneBusinessByCityCode(@RequestParam(value = "cityCode", required = false) String cityCode) {
        LOGGER.info(" /index/select/zoneBusiness interface param is {}", cityCode);
        if (StringUtils.isBlank(cityCode)) {
            return new JSONObject();
        }
        cityCode = cityCode.replace(" ", "");
        JSONObject zoneBusiness = nearAreaService.getZoneBusiness(cityCode);
        return zoneBusiness;
    }


}
