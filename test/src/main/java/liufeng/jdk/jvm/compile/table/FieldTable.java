package liufeng.jdk.jvm.compile.table;

/**
 * class文件 字段表
 * Field表结构
 * access flag u2
 * name index u2
 * descriptor index u2
 * attributes count u2
 * attributes 若干
 */
public class FieldTable {
    private int flag;
    private int nameIndex;
    private int count;
    private int descriptorIndex;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class FieldAttribute {

    }
}
