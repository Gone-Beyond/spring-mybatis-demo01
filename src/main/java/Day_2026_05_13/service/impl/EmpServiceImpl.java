package Day_2026_05_13.service.impl;

import Day_2026_05_13.mapper.EmpMapper;
import Day_2026_05_13.pojo.Emp;
import Day_2026_05_13.pojo.PageBean;
import Day_2026_05_13.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工业务层实现类
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;




    @Override
    public void create(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.create(emp);
    }

    @Override
    public Emp selectByID(Integer id) {
        return empMapper.selectByID(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer gender, LocalDate start, LocalDate end) {

        PageHelper.startPage(page, pageSize);

        List<Emp> list = empMapper.list(name, gender, start, end);
        Page<Emp> p = (Page<Emp>) list;

        return new PageBean(p.getTotal(), p.getResult());

    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public Emp selectByUsernameAndPassword(String username, String password) {
        Emp e = empMapper.selectByUsernameAndPassword(username, password);
        return e;
    }

    @Override
    public void deleteByDeptID(Integer id) {
        empMapper.deleteByDeptID(id);
    }


}