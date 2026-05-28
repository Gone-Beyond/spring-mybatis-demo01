package Day_2026_05_13.pojo;

/**
 * 后端统一响应结果类
 *
 * code：状态码，1 表示成功，0 表示失败
 * msg：提示信息
 * data：真正返回给前端的数据
 */
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功响应，带数据
     */
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    /**
     * 成功响应，不带数据
     */
    public static Result success() {
        return new Result(1, "success", null);
    }

    /**
     * 失败响应
     */
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}