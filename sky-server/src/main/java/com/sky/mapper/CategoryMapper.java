package com.sky.mapper;

import com.github.pagehelper.Page;
<<<<<<< HEAD
import com.sky.annotation.AutoFile;
=======
>>>>>>> 4dc56e4a529fc371177dba168fabd0fc2e2e27e6
import com.sky.enumeration.OperationType;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入数据
     * @param category
     */
<<<<<<< HEAD
    @AutoFile(value = OperationType.INSERT)
=======
>>>>>>> 4dc56e4a529fc371177dba168fabd0fc2e2e27e6
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id修改分类
     * @param category
     */
<<<<<<< HEAD
    @AutoFile(value = OperationType.UPDATE)
=======
>>>>>>> 4dc56e4a529fc371177dba168fabd0fc2e2e27e6
    void update(Category category);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
