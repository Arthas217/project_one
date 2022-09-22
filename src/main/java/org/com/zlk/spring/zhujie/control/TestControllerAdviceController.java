package org.com.zlk.spring.zhujie.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://blog.csdn.net/nandao158/article/details/112663873
 * @Date 2022/9/20 09:25
 */
@Slf4j
@RequestMapping("/nandao")
@RestController
public class TestControllerAdviceController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void users(Date date) {
        System.out.println(date); // Tue May 02 00:00:00 CST 2019
    }

    @RequestMapping("/indexMap")
    public int indexMap(ModelMap modelMap) {
        System.out.println(modelMap.get("nandao"));
        Object nandao = modelMap.get("nandao");
        Object md = modelMap.get("md");
        //      JSONObject jsonObject = (JSONObject) JSON.toJSON(modelMap);
        //  JSONObject jsonObject = (JSONObject) JSON.toJSON(nandao);
        Map map = (HashMap) md;
        String age = ((HashMap) md).get("gender").toString();
        log.info(age);

        String s = String.valueOf(nandao);

        return 1;
    }

    // 也可以通过@ModelAttribute获取
    @RequestMapping("/indexAttribute")
    public Object indexAttribute(@ModelAttribute("words") String words) {
        System.out.println(words);
        return words;
    }

    //通过get方法获取数据
    @GetMapping("/hello")
    public String hello(Model model) {
        throw TestException.le(333, "参数为空");
      /*  Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;*/
        //  return "hello controller advice";
    }

    @PostMapping("/book")
    public void book(@ModelAttribute("b") Book book) {
        System.out.println(book);
        throw TestException.le("参数为空");
        //  System.out.println(author);
    }

}
