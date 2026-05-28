package Day_2026_05_13.service.impl;

import Day_2026_05_13.mapper.DeptMapper;
import Day_2026_05_13.mapper.EmpMapper;
import Day_2026_05_13.pojo.Dept;
import Day_2026_05_13.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门业务层实现类
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        empMapper.deleteByDeptID(id);

        deptMapper.deleteByID(id);


    }



}