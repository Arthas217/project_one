package org.com.zlk.token;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * token工具类
 * @Date 2020/11/26
 */
public class TokenTools {
    /**
     * 生成token放入session
     */
    public static void createToken(HttpServletRequest request, String tokenServerkey){
        String token = TokenProccessor.getInstance().getToken();
        request.getSession().setAttribute(tokenServerkey, token);
    }

    /**
     * 移除token
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 判断请求参数中的token是否和session中一致
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request,String tokenClient,String tokenServerkey){
        String token_client = request.getParameter(tokenClient);
        if(StringUtils.isEmpty(token_client)){
            return false;
        }
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);
        if(StringUtils.isEmpty(token_server)){
            return false;
        }
        if(!token_server.equals(token_client)){
            return false;
        }
        return true;
    }

}
