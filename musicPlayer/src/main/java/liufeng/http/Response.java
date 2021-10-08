package liufeng.http;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/7/22 18:09
 */
public class Response<T> {
    private Integer code;
    private String msg;
    private T Data;

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

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public static Response success() {
        Response response = new Response();
        response.setCode(0);
        response.setMsg("请求成功");
        return response;
    }

    public static Response fail() {
        Response response = new Response();
        response.setCode(1);
        response.setMsg("请求失败");
        return response;
    }
}
