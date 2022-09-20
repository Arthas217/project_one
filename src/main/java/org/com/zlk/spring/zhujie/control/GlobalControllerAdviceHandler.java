package org.com.zlk.spring.zhujie.control;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @ControllerAdvice注解是Spring3.2中新增的注解，作用是给Controller控制器添加统一的操作或处理。
 * 用法主要有三点 1.全局异常处理：2.全局数据预处理：3.全局数据绑定
 * @Date 2022/9/20 08:57
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerAdviceHandler {


    /**
     * 此注解主要是自定义异常功能
     * 用于捕获Controller中抛出的指定类型的异常，从而达到不同类型的异常区别处理的目的。
     *
     * @param e
     * @return
     * @throws UnsupportedEncodingException
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(Exception e) throws UnsupportedEncodingException {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult("5000", null, ErrorMessage.SYSTEM_EXCEPTION);

        //如果是业务逻辑异常，返回具体的错误码与提示信息，即可以自定义多种异常类型
        if (e instanceof BadRequestException) {
            BadRequestException badRequestException = (BadRequestException) e;
            setData(result, badRequestException.getCode(), badRequestException.getErrorMsg(), badRequestException.getInnerCode(), e);
        } else if (e instanceof TestException) {
            TestException testException = (TestException) e;
            setData(result, testException.getCode(), testException.getErrorMsg(), testException.getInnerCode(), e);
        } else {
            String msg = e.getMessage();
            if (!ContMsgExpUtil.isContMsg(msg)) {
                msg = ErrorMessage.SYSTEM_EXCEPTION + msg;
            }
            result.setMessage(msg);
            log.error("errorMsg={},innerCode={},exception={}", e.getMessage(), 5000, e);
        }
        return JSONObject.toJSON(result);//正式返回给前端信息
    }

    void setData(RestResult result, int code, String errorMsg, String innerCode, Exception e) throws UnsupportedEncodingException {
        result.setMessage(errorMsg);
        result.setCode(innerCode);
        log.error("errorMsg={},innerCode={},exception={}", errorMsg, innerCode, e);
        log.info("已经爆出异常了啊！！！！！！");
    }


    /**
     * 应用到所有被@RequestMapping注解的方法，在其执行之前初始化数据绑定器
     * 用于request中自定义参数解析方式进行注册，从而达到自定义指定格式参数的目的。
     */
    @InitBinder
    public void globalInitBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @InitBinder("nan")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("nan.");
    }

    @InitBinder("nv")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("nv.");
    }


    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * 其注解的方法将会在目标Controller方法执行之前执行。
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("zlk", "hello world");
    }

    @ModelAttribute(value = "msg")
    public String globalModelAttribute() {
        System.out.println("hello word");
        return "msg";
    }

    @ModelAttribute(name = "person")
    public Map<String, Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 19);
        map.put("gender", "男");
        return map;
    }


}
