package liufeng.jdk.jvm.compile.enums;

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
    Utf8_info(1, new Utf8Strategy()),
    Integer_info(3, new IntegerStrategy()),
    Float_info(4, new FloatStrategy()),
    Long_info(5, new LongStrategy()),
    Double_info(6, new DoubleStrategy()),
    Class_info(7, new ClassInfoStrategy()),
    String_info(8, new StringStrategy()),
    Fieldref_info(9, new FieldStrategy()),
    Methodref_info(10, new MethodStrategy()),
    InterfaceMethodref_info(11, new InterfaceStrategy()),
    NameAndType_info(12, new NameTypeStrategy());

    /**
     * 唯一tag
     */
    private int tag;
    /**
     * 解析策略
     */
    private CpStrategy cpStrategy;


    private boolean print = true;

    ConstantsForm(int tag) {
        this.tag = tag;
    }

    ConstantsForm(int tag, CpStrategy cpStrategy) {
        this.tag = tag;
        this.cpStrategy = cpStrategy;
    }


    ConstantsForm() {
    }

    private static List<ConstantsItem> items = new LinkedList<>();

    public static void add(ConstantsForm constantsForm, Object value, int length) {
        ConstantsItem constantsItem = new ConstantsItem();
        constantsItem.setLength(length);
        constantsItem.setType(constantsForm);
        constantsItem.setValue(value);
        items.add(constantsItem);
        if (constantsForm.isPrint()) {
            System.out.println("size: " + items.size() + " ,add value , type=" + constantsForm + ",value=" + value + ",length=" + length);
        }
    }


    public int getTag() {
        return tag;
    }

    public boolean isPrint() {
        return print;
    }

    /**
     * 根据tag获取对应的常量池结构
     */
    public static ConstantsForm getByTag(int tag) {
        for (ConstantsForm constantsForm : ConstantsForm.values()) {
            if (tag == constantsForm.tag) {
                return constantsForm;
            }
        }
        throw new RuntimeException("unknown constants pool tag:" + tag);
    }

    public CpStrategy getCpStrategy() {
        return cpStrategy;
    }

    static class ConstantsItem {
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

    }


}
