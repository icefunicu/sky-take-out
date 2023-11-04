package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 增添菜品和口味
     * */
    @Transactional//事务注解
    public void saveWithFlavor(DishDTO dto) {
        Dish dish = new Dish();
        //对象拷贝
        BeanUtils.copyProperties(dto,dish);
        //向菜品表插入1条数据
        dishMapper.insert(dish);
        Long id = dish.getId();
        //向口味表插入N条数据
        List<DishFlavor> flavors = dto.getFlavors();
        if(flavors!=null&&flavors.size()>0){
            flavors.forEach(dishFlavor->{
                dishFlavor.setDishId(id);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }
    /**
     * 菜品分页查询
     * */

    public PageResult pageQuery(DishPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());

        Page<DishVO> page=dishMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }
    /**
     * 菜品批量删除
     * */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //判断当前菜品是否能删除-是否存在起售中的菜品
        for (Long id : ids){
           Dish dish =  dishMapper.getById(id);
           if (dish.getStatus()== StatusConstant.ENABLE){
               //起售中
               throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
           }
        }
        //判断是否被套餐关联
        List<Long> setMealIdsByDishIds = setmealDishMapper.getSetMealIdsByDishIds(ids);
        if (setMealIdsByDishIds!=null && setMealIdsByDishIds.size()>0){
            //被关联
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //删除菜品表中的菜品数据
//        for (Long id : ids){
//            dishMapper.deleteById(id);
//            //删除口味表中的口味数据
//            dishFlavorMapper.deleteByDishId(id);
//        }
        //根据菜品Id集合批量删除菜品数据
        //sql:delete from dish where id in (?,?,?)
        dishMapper.deleteByIds(ids);
        //根据菜品Id集合批量删除口味数据
        //sql:delete from dish_flavor where dish_id in (?,?,?)
        dishFlavorMapper.deleteByDishIds(ids);
    }
    /**
     * 根据id查询菜品和口味数据
     * */

    public DishVO getByIdWithFlavor(Long id) {
        //根据id查询菜品数据
        Dish dish = dishMapper.getById(id);
        //根据菜品id查询口味数据
        List<DishFlavor> flavors =  dishFlavorMapper.getByDishId(id);
        //将查询到的数据封装到VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    @Transactional
    public void updateWithFlavor(DishDTO dto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dto,dish);

        //修改菜品表基本信息
        dishMapper.update(dish);
        //删除原有口味
        dishFlavorMapper.deleteByDishId(dto.getId());
        //插入口味信息
        List<DishFlavor> flavors = dto.getFlavors();
        if(flavors!=null&&flavors.size()>0){
            flavors.forEach(dishFlavor->{
                dishFlavor.setDishId(dto.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }

    }


}
