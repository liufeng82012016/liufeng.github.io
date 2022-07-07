package liufeng.jdk.jvm.compile.enums;

import liufeng.jdk.jvm.compile.constants.Constants;
import liufeng.jdk.jvm.compile.strategy.cp.ClassInfoStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.CpStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.DoubleStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.FieldStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.FloatStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.IntegerStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.InterfaceStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.LongStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.MethodStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.NameTypeStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.StringStrategy;
import liufeng.jdk.jvm.compile.strategy.cp.Utf8Strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * class类文件 常量池中一共
 */
public enum ConstantsForm {
    Utf8_info(1, new Utf8Strategy(), Constants.UNFIXED_LENGTH),
    Integer_info(3, new IntegerStrategy(),5),
    Float_info(4, new FloatStrategy(),5),
    Long_info(5, new LongStrategy(),9),
    Double_info(6, new DoubleStrategy(),9),
    Class_info(7, new ClassInfoStrategy(),3),
    String_info(8, new StringStrategy(),3),
    Fieldref_info(9, new FieldStrategy(),5),
    Methodref_info(10, new MethodStrategy(),5),
    InterfaceMethodref_info(11, new InterfaceStrategy(),5),
    NameAndType_info(12, new NameTypeStrategy(),5);

    /**
     * 唯一tag
     */
    private int tag;
    /**
     * 解析策略
     */
    private CpStrategy cpStrategy;

    /**
     * 占用字节数
     */
    private int length;


    private boolean print = true;

    ConstantsForm(int tag) {
        this.tag = tag;
    }

    ConstantsForm(int tag, CpStrategy cpStrategy) {
        this.tag = tag;
        this.cpStrategy = cpStrategy;
    }

    ConstantsForm(int tag, CpStrategy cpStrategy, int length) {
        this.tag = tag;
        this.cpStrategy = cpStrategy;
        this.length = length;
    }

    ConstantsForm() {
    }

    private static List<ConstantsItem> items = new LinkedList<>();

    /**
     * 将常量池的项加到常量池的表
     *
     * @param constantsForm 类型
     * @param value         值
     * @param length        占用长度（不包含tag）
     */
    public static void add(ConstantsForm constantsForm, Object value, int length) {
        if (items.isEmpty()) {
            // 第0个元素指向null，指向常量池第0个元素即表示没有引用
            items.add(null);
        }
        ConstantsItem constantsItem = new ConstantsItem();
        constantsItem.setLength(length);
        constantsItem.setType(constantsForm);
        constantsItem.setValue(value);
        if (constantsForm.isPrint()) {
            System.out.println(String.format("#%s:type=%s,value=%s,length=%s", +items.size(), constantsForm, value, length));
        }
        items.add(constantsItem);
        if (ConstantsForm.Long_info == constantsForm || ConstantsForm.Double_info == constantsForm) {
            // Long和Double占用2个槽位
            items.add(null);
        }
    }

    /**
     * 从常量池获取项
     *
     * @param index 下标
     */
    public static ConstantsItem getConstantItem(int index) {
        if (index < 1 || index >= items.size()) {
            throw new IllegalArgumentException("index错误");
        }
        ConstantsItem constantsItem = items.get(index);
        if (constantsItem == null) {
            // 正常不会走到这里，除非遍历
            constantsItem = items.get(index - 1);
        }
        return constantsItem;
    }

    /**
     * 检查常量池元素是否已解析完成
     *
     * @param counter 期望的常量池元素数量
     * @return true-已完成 false-未完成
     */
    public static boolean checkIfEnd(int counter) {
        return items.size() == counter;
    }


    public int getTag() {
        return tag;
    }

    public boolean isPrint() {
        return print;
    }

    /**
     * 根据tag获取对应的常量池结构，没有匹配上返回null
     */
    public static ConstantsForm getByTag(int tag) {
        for (ConstantsForm constantsForm : ConstantsForm.values()) {
            if (tag == constantsForm.tag) {
                return constantsForm;
            }
        }
        return null;
    }

    public int getLength() {
        return length;
    }

    public CpStrategy getCpStrategy() {
        return cpStrategy;
    }

    public static class ConstantsItem {
        private ConstantsForm type;
        private Object value;
        private int length;

        public ConstantsForm getType() {
            return type;
        }

        public void setType(ConstantsForm type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "ConstantsItem{" +
                    "type=" + type +
                    ", value=" + value +
                    ", length=" + length +
                    '}';
        }
    }


}
