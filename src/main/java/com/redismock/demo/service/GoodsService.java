package com.redismock.demo.service;

import com.redismock.demo.pojo.Goods;

public interface GoodsService {
    public Goods getOneGoodsById(Long goodsId);
    public void setOneGoods(Goods goods);
}