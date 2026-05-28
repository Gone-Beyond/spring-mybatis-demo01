package Day_2026_05_13.controller;

import Day_2026_05_13.anno.Log;
import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        return Result.success(deptService.list());
    }

    /**
     * 删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除部门 id: {}", id);
        deptService.delete(id);
        return Result.success();
    }
}