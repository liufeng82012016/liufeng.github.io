package com.my.liufeng.xst;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配合h5
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    List<JSONObject> bookList = new JSONArray()
            .fluentAdd(new JSONObject()
                    .fluentPut("id", 1)
                    .fluentPut("name", "西游记")
                    .fluentPut("author", "吴承恩")
                    .fluentPut("press", "北京图书出版社"))
            .fluentAdd(new JSONObject()
                    .fluentPut("id", 2)
                    .fluentPut("name", "西游记2")
                    .fluentPut("author", "吴承恩2")
                    .fluentPut("press", "北京图书出版社2"))
            .fluentAdd(new JSONObject()
                    .fluentPut("id", 3)
                    .fluentPut("name", "西游记3")
                    .fluentPut("author", "吴承恩3")
                    .fluentPut("press", "北京图书出版社3")).toJavaList(JSONObject.class);

    @GetMapping("list")
    public Object getBookList(Integer id) {
        if (id != null) {
            return new JSONObject()
                    .fluentPut("code", 200)
                    .fluentPut("data", bookList.stream().filter(jsonObject -> {
                        return id.equals(jsonObject.getInteger("id"));
                    }).collect(Collectors.toList()));
        }
        return new JSONObject()
                .fluentPut("code", 200)
                .fluentPut("data", bookList);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Integer id) {
        Iterator<JSONObject> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            JSONObject obj = (JSONObject) iterator.next();
            if (id == obj.getIntValue("id")) {
                iterator.remove();
                return new JSONObject()
                        .fluentPut("code", 200);
            }
        }
        return new JSONObject()
                .fluentPut("code", 1001)
                .fluentPut("msg", "图书不存在");

    }

    @RequestMapping("add")
    public Object add(@RequestParam String name, @RequestParam String author, @RequestParam String press) {
        bookList.add(new JSONObject()
                .fluentPut("id", bookList.get(bookList.size() - 1).getIntValue("id") + 1)
                .fluentPut("name", name)
                .fluentPut("author", author)
                .fluentPut("press", press));
        return new JSONObject()
                .fluentPut("code", 200);
    }

    @PostMapping("addBook")
    public Object add(@RequestBody JSONObject param) {
        bookList.add(param
                .fluentPut("id", bookList.get(bookList.size() - 1).getIntValue("id") + 1));
        return new JSONObject()
                .fluentPut("code", 200);
    }

    @PostMapping("uploadFile")
    public Object uploadFile(MultipartFile file) {
        if (file == null) {
            return new JSONObject()
                    .fluentPut("code", 1001)
                    .fluentPut("msg", "文件为空");
        }

        System.out.println(file.getSize());

        return new JSONObject()
                .fluentPut("code", 200)
                .fluentPut("url","https://www.books88.com/Books_Pic/20080509/L9787807072393.jpg");
    }
}
