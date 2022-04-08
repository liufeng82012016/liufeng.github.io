### MySQL


#### char和varchar的区别
1. char存储时，如果字符数不足，末尾以空格填充
2. char获取时，会去掉尾部的空格，varchar不会
3. char的内存占用和编码格式有关

#### text和blob
1. text保存字符数据，blob保存二进制数据