## JDK使用笔记
1. collection
    1. subList方法左开右闭[fromIndex,toIndex)，返回元素包含fromIndex，toIndex最大为list.size(),否则将抛出IndexOutBountException；
        如果需要截取列表后5个元素，可以传参subList(list.size()-5,list.size());
    2.  
    