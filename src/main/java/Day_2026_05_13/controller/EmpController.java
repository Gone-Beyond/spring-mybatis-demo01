package Day_2026_05_13.controller;

import Day_2026_05_13.anno.Log;
import Day_2026_05_13.pojo.Emp;
import Day_2026_05_13.pojo.PageBean;
import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工控制层
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     *
     *分页查询
     *
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        PageBean pageBean = empService.page(page, pageSize,name, gender, start, end);

        return Result.success(pageBean);
    }



    /**
     *
     * 添加一条员工
     *
     * @return
     */
    @Log
    @PostMapping
    public Result create(@RequestBody Emp emp) {
        log.info("添加一条员工 emp: {}", emp);
        empService.create(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectByID(@PathVariable Integer id) {
        log.info("查询一条员工 id: {}", id);
        Emp emp = empService.selectByID(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable(name = "ids")List<Integer> ids) {
        empService.delete(ids);
        return Result.success();
    }




}