package com.redismock.demo.service.impl;

import com.redismock.demo.pojo.Goods;
import com.redismock.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private RedisTemplate redis1Template;

    //得到一件商品的信息
    @Override
    public Goods getOneGoodsById(Long goodsId) {
        Goods goodsOne;

            System.out.println("get data from redis");
            Object goodsr = redis1Template.opsForValue().get("goods_"+String.valueOf(goodsId));
            if (goodsr == null) {
                goodsOne = null;
            } else {
                if (goodsr.equals("-1")) {
                    goodsOne = null;
                } else {
                    goodsOne = (Goods)goodsr;
                }
            }
        return goodsOne;
    }

    @Override
    public void setOneGoods(Goods goods){
        redis1Template.opsForValue().set("goods_"+String.valueOf(goods.getGoodsId()),goods,600, TimeUnit.SECONDS);
    }
}
