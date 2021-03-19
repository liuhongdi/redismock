package com.redismock.demo.controller;

import com.redismock.demo.pojo.Goods;
import com.redismock.demo.service.GoodsService;


import javax.annotation.Resource;
import java.util.concurrent.ConcurrentMap;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    //商品详情 参数:商品id
    @GetMapping("/get")
    @ResponseBody
    public Goods goodsInfo(@RequestParam(value="goodsid",required = true,defaultValue = "0") Long goodsId) {
        Goods goods = goodsService.getOneGoodsById(goodsId);
        return goods;
    }


    @GetMapping("/set")
    @ResponseBody
    public Goods goodsInfoSet(@RequestParam(value="goodsid",required = true,defaultValue = "0") Long goodsId,
                              @RequestParam(value="goodsname",required = true,defaultValue = "") String goodsName) {
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsName(goodsName);
         goodsService.setOneGoods(goods);
        return goods;
    }

}

