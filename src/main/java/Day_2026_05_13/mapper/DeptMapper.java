package Day_2026_05_13.mapper;

import Day_2026_05_13.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门 Mapper 接口
 */
@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门
     */
    List<Dept> list();

    void deleteByID(Integer id);
}