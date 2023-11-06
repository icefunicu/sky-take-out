package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
    * 新增菜品和对应的口味
    * @param dto
    * */

    public void saveWithFlavor(DishDTO dto);
    /**
     * 菜品分页查询
     * @param dto
     * */
    PageResult pageQuery(DishPageQueryDTO dto);
    /**
     * 菜品批量删除
     * @param ids
     * */
    void deleteBatch(List<Long> ids);
    /**
     * 根据id查询菜品和口味数据
     * */

    DishVO getByIdWithFlavor(Long id);
    /**
     * 修改菜品信息和口味信息
     * */
    void updateWithFlavor(DishDTO dto);
    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
    /*
    * 条件查询菜品和口味
    * */
    List<DishVO> listWithFlavor(Dish dish);

    void startOrStop(Integer status, Long id);
}
