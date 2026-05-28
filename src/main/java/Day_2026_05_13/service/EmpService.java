package Day_2026_05_13.service;

import Day_2026_05_13.pojo.Emp;
import Day_2026_05_13.pojo.PageBean;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工业务层接口
 */
public interface EmpService {




    
    void create(Emp emp);

    Emp selectByID(Integer id);

    void update(Emp emp);

    PageBean page(Integer page, Integer pageSize, String name, Integer gender, LocalDate start, LocalDate end);

    void delete(List<Integer> ids);



    Emp selectByUsernameAndPassword(String username, String password);

    void deleteByDeptID(Integer id);
}