package com.daocao.controller;

import com.daocao.respose.Result;
import com.daocao.unils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/sysMenu")
public class Redis {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("save")
    public Result test(){
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "1");
        map.put("key2", "2");
        System.out.println(map);
        //redisTemplate.opsForHash().putAll("test", map);
        redisUtil.add("test1",map);
        return new Result(200,"test");
    }
}