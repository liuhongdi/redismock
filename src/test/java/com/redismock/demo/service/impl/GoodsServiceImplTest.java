package com.redismock.demo.service.impl;

import com.redismock.demo.pojo.Goods;
import com.redismock.demo.service.GoodsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.jayway.jsonpath.JsonPath;


@SpringBootTest
class GoodsServiceImplTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    private static RedisServer redisServer;


    @BeforeAll
    static void startRedis() {
        redisServer = RedisServer.builder()
                .port(6379)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();
    }

    /**
     * 析构方法之后执行，停止Redis.
     */
    @AfterAll
    static void stopRedis() {
        redisServer.stop();
    }

    @Test
    @DisplayName("测试从嵌入redis写入并读取数据")
    void getOneGoodsById() {
        Goods gset = new Goods();
        gset.setGoodsId(5L);
        gset.setGoodsName("test5");
        goodsService.setOneGoods(gset);

        Goods goods = goodsService.getOneGoodsById(5L);
        System.out.println(goods);
        assertThat(goods.getGoodsId(), equalTo(5L));
        assertThat(goods.getGoodsName(), equalTo("test5"));
    }

}