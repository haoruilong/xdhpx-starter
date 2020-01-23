package com.xdhpx.boot.starter.web.controller;

import com.xdhpx.boot.starter.aop.annotation.RequestLogAnnotation;
import com.xdhpx.boot.starter.common.utils.RedisTemplateUtil;
import com.xdhpx.boot.starter.web.entity.User;
import com.xdhpx.boot.starter.web.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 @RestController此注解相当于
 @ResponseBody +@Controller
 合在一起的作用
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;

//	@Autowired
//	private HelloService helloService;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

	    @GetMapping("/save")
		public User save() {
	        User user = new User();
	        String uuid = UUID.randomUUID().toString().replace("-", "");
	        user.setId(uuid);
	        user.setUserName("测试添加");
	        user.setPassword("123");
	        user.setSex((byte)1);
	        user.setAge(18);
	        user.setCreateTime(new Date());
	        userService.save(user);
	        return user;
	    }

		@ApiOperation(value = "获取所有",notes = "暂无")
		@RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
		@RequestLogAnnotation(moduleName = "web模块",descInfo="获取全部")
		public List<User> getAll(String aa,String bb) {
	    	List<User> list = userService.getAll();

            // 获取数据
            String key = "test0121";

			redisTemplateUtil.setCacheString(key,"测试数据");

			String value = redisTemplateUtil.getCacheString(key);
            System.out.println("获取缓存中key为" + key + "的值为：" + value);

            redisTemplateUtil.removeString(key);

			System.out.println(aa);
			System.out.println(bb);
//			System.out.println(helloService.sayHello());
			System.out.println("3333");
			return list;
	    }


	    @GetMapping("/getById")
		public User getById(String id) {
	        User user = userService.getById(id);
	        return user;
	    }

	    @GetMapping("/deleteById")
		public int deleteById(String id) {
	        return userService.deleteById(id);
	    }


}
