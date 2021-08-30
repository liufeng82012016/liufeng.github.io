package liufeng.io;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author Ailwyn
 * @Description: io 字符流读取文件
 * @Date 2021/1/6 11:25
 */
public class BufferReaderTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
            }).start();
        }
    }


    public static void readFile(String path, Integer emptyLines) throws IOException {
        if (emptyLines == null) {
            // 默认出现空行就断开
            emptyLines = 0;
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String str = bufferedReader.readLine();
        int count = 0;
        while (true) {
            if (StringUtils.isNotBlank(str)) {
                count = 0;
                // 处理逻辑
                parseStr(str);
                str = bufferedReader.readLine();
            } else {
                count++;
            }
            if (count > emptyLines) {
                break;
            }
        }
    }

    public static void parseStr(String str) {
        JSONArray jsonArray = JSONArray.parseArray(str);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BigDecimal origQtv = jsonObject.getBigDecimal("origQtv");
            BigDecimal executedQty = jsonObject.getBigDecimal("executedQty");
            try {
                if (origQtv.compareTo(executedQty) != 0) {
                    System.out.println(jsonObject);
                }
            } catch (Exception e) {
                System.out.println(jsonObject);
            }
        }
    }

}
