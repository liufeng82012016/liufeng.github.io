### 常用结构
1. 变量
   1. 定义变量：my_name="zhangsan"; 相当于定义一个变量名为'my_name'，值为'zhangsan'。
   2. 修改变量为只读：readOnly my_name;
   3. 变量取值：echo ${my_name};然后变量my_name打印到控制台，变量取值可不用大括号，一般会使用，结构更加清晰。
2. 数组

### 常用函数
1. echo 打印
2. 
### 常用语句
1. for循环
```shell
    # 数组多个元素用空格隔开
    arr=(12 '')
    length=${#arr}
    echo "长度为：$length"
    # for 遍历
    for item in ${arr[*]}
    do
     git pull $item
    done
```
2. if
3. 