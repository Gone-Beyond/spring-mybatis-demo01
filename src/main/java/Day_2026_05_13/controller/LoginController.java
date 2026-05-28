package Day_2026_05_13.controller;


import Day_2026_05_13.pojo.Emp;
import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.service.EmpService;
import Day_2026_05_13.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ServletComponentScan
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        Emp e = empService.selectByUsernameAndPassword(emp.getUsername(), emp.getPassword());

        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        } else {
            return Result.error("username "+"or"+" password is error");
        }


    }


}
