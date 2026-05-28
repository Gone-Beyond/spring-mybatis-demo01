package Day_2026_05_13.exception;


import Day_2026_05_13.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex (Exception ex) {
        ex.printStackTrace();
        return Result.error("Exception");
    }
}
