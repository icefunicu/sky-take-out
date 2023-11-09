package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /*
    * 添加购物车
    * */
    void addShoppingCart(ShoppingCartDTO dto);
    /*
    * 查看购物车
    * */
    List<ShoppingCart> showShoppingCart();
    /*
    * 减少购物车
    * */
    void subShoppingCart(ShoppingCartDTO dto);
    /*
    * 清空购物车
    * */
    void cleanShoppingCart();
}
