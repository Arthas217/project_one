package org.com.zlk.msxf.shortConnection;


import org.apache.commons.lang.StringUtils;

import java.time.Duration;
import java.time.Instant;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/12/13 18:35
 */
public class ConnectionServiceImpl {

    public static long getMessageId(ConnRequest request) {
        Instant current = Instant.now();
        Instant minus = current.minus(Duration.ofDays(3));
        long threeBefore = minus.toEpochMilli();
        String lastMaxMsgId = request.getLastMaxMsgId();
        if (!StringUtils.isBlank(lastMaxMsgId)) {
            long last = Long.valueOf(lastMaxMsgId) / 1000000;
            if (last > threeBefore) {
                return Long.valueOf(lastMaxMsgId);
            }
        }
        return threeBefore * 1000000;
    }
}
