package com.sky.mapper;


import com.sky.annotation.AutoFile;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     * */
    @AutoFile(value = OperationType.INSERT)
    void insertBatch(List<DishFlavor> flavors);
    /**
     * 根据id删除口味
     * */
    @Delete("delete from dish_flavor where dish_id=#{dishId};")
    void deleteByDishId(Long id);
    /**
     * 根据菜品id集合批量删除口味
     * */
    void deleteByDishIds(List<Long> dishIds);
}
