package liufeng.net;

import com.alibaba.fastjson.JSONObject;
import com.jobwen.common.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/1/7 9:13
 */
public class RequestTest {

    /**
     * 根据ip获取区号
     * @param ip
     * @return
     */
    public static String getIp(String ip) {
        String result = HttpUtil.get("https://ipapi.com/ip_api.php?ip=" + ip, RequestConfig.custom()
                .setConnectTimeout(30 * 1000)
                .setSocketTimeout(30 * 1000)
                .setConnectionRequestTimeout(3 * 1000)
                .build());
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
