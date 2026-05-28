package Day_2026_05_13.mapper;

import Day_2026_05_13.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工 Mapper 接口
 */
@Mapper
public interface EmpMapper {





    void create(Emp emp);

    Emp selectByID(Integer id);

    void update(Emp emp);

    List<Emp> list(@Param("name") String name,
                   @Param("gender") Integer gender,
                   @Param("start") LocalDate start,
                   @Param("end") LocalDate end);


    void delete(@Param("ids") List<Integer> ids);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(String username, String password);

    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptID(Integer id);
}