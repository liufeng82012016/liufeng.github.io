package liufeng.jdk.jvm.compile.table;

/**
 * class文件 字段表（方法表和字段表的格式一致）
 * Field表结构
 * access flag u2
 * name index u2
 * descriptor index u2
 * attributes count u2
 * attributes 若干
 */
public class FieldInfo {
    /**
     * 访问标识
     */
    private int flag;
    /**
     * 名，指向常量池的一个引用
     */
    private int nameIndex;
    /**
     * 描述符，指向常量池的一个引用
     */
    private int descriptorIndex;
    /**
     * 属性数量
     */
    private int count;

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

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public static class FieldAttribute {

    }

    @Override
    public String toString() {
        return "FieldTable{" +
                "flag=" + flag +
                ", nameIndex=" + nameIndex +
                ", count=" + count +
                ", descriptorIndex=" + descriptorIndex +
                '}';
    }
}
