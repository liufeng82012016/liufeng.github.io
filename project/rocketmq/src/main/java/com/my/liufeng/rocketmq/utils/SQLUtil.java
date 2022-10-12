package com.my.liufeng.rocketmq.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.my.liufeng.rocketmq.entity.Product;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 根据实体类生成建表SQL
 *
 * @author liufeng
 */
public class SQLUtil {

    public static void createSQL(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        // 校验
        if (declaredFields.length == 0) {
            throw new RuntimeException(clazz.getSimpleName() + "实体类无字段");
        }
        TableName annotation = clazz.getAnnotation(TableName.class);
        if (annotation == null) {
            throw new RuntimeException(clazz.getSimpleName() + "未指定表名");
        }
        String tableName = annotation.value();
        if (tableName == null || tableName.length() == 0) {
            throw new RuntimeException(clazz.getSimpleName() + "未指定表名");
        }
        // 开始生成
        StringBuilder stringBuilder = new StringBuilder("create table `")
                .append(tableName)
                .append("`( ");
        boolean first = true;
        for (Field field : declaredFields) {
            Class<?> fieldClass = field.getType();
            // 转小驼峰
            stringBuilder.append("\n")
                    .append("`")
                    .append(fromCamelCase(field.getName()))
                    .append("`");
            if (String.class == fieldClass) {
                stringBuilder.append(" varchar(64) ");
            } else if (Long.class == fieldClass) {
                stringBuilder.append(" bigint ");
            } else if (Integer.class == fieldClass) {
                stringBuilder.append(" int ");
            } else if (Date.class == fieldClass) {
                stringBuilder.append(" datetime ");
            } else {
                throw new RuntimeException("unknown field type: " + fieldClass.getSimpleName());
            }
            TableId idAnnotation = field.getAnnotation(TableId.class);
            if (idAnnotation != null) {
                stringBuilder.append(" primary key");
                if (IdType.AUTO == idAnnotation.type()) {
                    stringBuilder.append(" AUTO_INCREMENT ");
                }
                stringBuilder.append(" comment '主键'");
            }
            if (first && declaredFields.length > 1) {
                first = false;
                stringBuilder.append(" ,");
            }
        }

        stringBuilder.append("\n)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPRESSED COMMENT='';");
        System.out.println(stringBuilder);
    }

    /**
     * 将字符串由小驼峰转下划线
     * @param fieldName
     * @return
     */
    private static String fromCamelCase(String fieldName) {
        if (fieldName == null || fieldName.length() == 0) {
            return fieldName;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (65 <= c && c <= 90) {
                stringBuilder.append("_")
                        .append((char)(c + 32));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        createSQL(Product.class);
    }
}
