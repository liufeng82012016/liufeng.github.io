package liufeng.jdk.jvm.compile.table;

import java.util.List;

/**
 * 数量默认1，非1在()标出
 * u2 attribute_name_index
 * u4 attribute_length
 * u2 max_stack
 * u2 max_locals
 * u4 code_length
 * u1 code(code_length)
 * u2 exception_table_length
 * 变长 exception_table(exception_table_length)
 * u2 attributes_count
 * 变长 attributes(attributes_count)
 */
public class CodeInfo {
    private int nameIndex;
    private int attributeLength;
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    // todo 暂定String
    private List<String> codes;
    private int exceptionsLength;
    // todo 暂定String
    private List<String> exceptions;
    private int attributesCount;
    // todo 暂定String
    private List<String> attributes;

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public int getExceptionsLength() {
        return exceptionsLength;
    }

    public void setExceptionsLength(int exceptionsLength) {
        this.exceptionsLength = exceptionsLength;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
