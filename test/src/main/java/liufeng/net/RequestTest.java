package liufeng.net;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/1/7 9:13
 */
public class RequestTest {

    /**
     * 根据ip获取区号
     */
    @Test
    public String getIp(String ip) {
        String result = HttpUtil.get("https://ipapi.com/ip_api.php?ip=" + ip);
        String callingCode = "1";
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject location = jsonObject.getJSONObject("location");
            if (location != null) {
                callingCode = location.getString("calling_code");
            }
            JSONObject currency = jsonObject.getJSONObject("currency");
            String fiat = currency.getString("code");
        }
        return callingCode;
    }
}
