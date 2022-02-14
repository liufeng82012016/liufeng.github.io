package liufeng.algo.tree.hfm;

import java.util.Objects;

/**
 * 哈夫曼树节点
 */
public class HfmNode<T> implements Comparable<HfmNode<T>> {

    /**
     * 树保存的对象
     */
    private final T value;
    /**
     * value的权
     */
    private final Integer number;
    /**
     * 左侧子节点
     */
    private HfmNode<T> leftChild;
    /**
     * 右侧子节点
     */
    private HfmNode<T> rightChild;


    public T getValue() {
        return value;
    }


    public int getNumber() {
        return number;
    }

    public HfmNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HfmNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public HfmNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(HfmNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public HfmNode(T value, Integer number) {
        Objects.requireNonNull(number);
        this.value = value;
        this.number = number;
    }

    @Override
    public int compareTo(HfmNode o) {
        return this.number.compareTo(o.number);
    }

    //    @Override
//    public String toString() {
//        return value + ":" + number + "\n"
//                + (leftChild == null ? "" : (leftChild.value + ":" + leftChild.number)) + "\t" + (rightChild == null ? "" : (rightChild.value + ":" + rightChild));
//    }
    @Override
    public String toString() {
        return (value + ":" + number);
    }

    public void print() {
        System.out.println(this);
        if (leftChild != null) {
            leftChild.print();
        }
        if (rightChild != null) {
            rightChild.print();
        }
    }
}
