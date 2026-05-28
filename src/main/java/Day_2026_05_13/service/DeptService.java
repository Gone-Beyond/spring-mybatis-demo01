package Day_2026_05_13.service;

import Day_2026_05_13.pojo.Dept;

import java.util.List;

/**
 * 部门业务层接口
 */
public interface DeptService {

    /**
     * 查询全部部门
     */
    List<Dept> list();

    void delete(Integer id);
}