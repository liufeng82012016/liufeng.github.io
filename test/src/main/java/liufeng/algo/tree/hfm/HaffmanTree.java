package liufeng.algo.tree.hfm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

/**
 * 哈夫曼树
 */
public class HaffmanTree<T> {
    /**
     * 根结点
     */
    private HfmNode<T> root;

    /**
     * 最小字节点到根结点的长度
     */
    private int length;

    public HaffmanTree(Map<T, Integer> map) {
        // 构造节点
        LinkedList<HfmNode<T>> list = new LinkedList<>();
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            list.add(new HfmNode<>(entry.getKey(), entry.getValue()));
        }
        // 按照值从小到大排序
        Collections.sort(list);
        // 构建树
        while (true) {
            // 取出前2个节点，因为从小到大排序，前2个元素一定是最小的
            HfmNode<T> currentNode = list.remove(1);
            HfmNode<T> preNode = list.remove(0);
            // 生成父节点
            HfmNode<T> parent = new HfmNode<>(null, currentNode.getNumber() + preNode.getNumber());
            parent.setLeftChild(preNode);
            parent.setRightChild(currentNode);
            // 如果列表没有元素，表示只剩下根结点
            if (list.size() == 0) {
                root = parent;
                System.out.println("length: " + length);
                return;
            }
            // 合并一次，如果没有到根结点，记录高度加1
            length++;
            // 如果当前元素比列表最大值还大，将元素添加到列表尾部
            if (parent.getNumber() > list.getLast().getNumber()) {
                list.add(parent);
                continue;
            }
            // 遍历列表，将parent添加到合适的位置
            for (int j = 0; j < list.size(); j++) {
                if (parent.getNumber() < list.get(j).getNumber()) {
                    list.add(Math.max(j - 1, 0), parent);
                    break;
                }
            }
        }
    }


    public HfmNode<T> getNode(int number) {
        HfmNode<T> node = getNode(root, number);
        if (node != null && node.getValue() == null) {
            // 如果没有对应值，标识这是一个虚拟节点
            node = null;
        }
        return node;
    }

    /**
     * 根据数值获取对应的节点
     *
     * @param root   根节点
     * @param number 数字
     * @return 对应的hfm节点
     */
    private HfmNode<T> getNode(HfmNode<T> root, int number) {
        // 判断根结点是否为null
        if (root == null) {
            return null;
        }
        // 判断输入数值
        int v = root.getNumber();
        if (number > v || number < 0) {
            return null;
        }
        // 递归的查询左右节点
        HfmNode<T> leftChild = root.getLeftChild();
        if (leftChild != null) {
            HfmNode<T> node = getNode(leftChild, number);
            if (node != null) {
                return node;
            }
        }
        return getNode(root.getRightChild(), number);
    }

    /**
     * 根据数字获取对应节点的二进制编码，获取不到返回null
     */
    public String getCode(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean search = search(root, number, stringBuilder);
        if (search) {
            stringBuilder.reverse();
        }
        return search ? stringBuilder.toString() : null;
    }

    /**
     * 根据数值获取对应的节点编码
     *
     * @param node   上一个节点
     * @param number 数字
     * @return 对应的hfm节点
     */
    private boolean search(HfmNode<T> node, int number, StringBuilder stringBuilder) {
        // 判断根结点是否为null
        if (node == null) {
            return false;
        }
        // 判断输入数值
        int v = node.getNumber();
        if (number > v || number < 0) {
            // 输入数值大于最大值，或者小于0
            return false;
        } else if (number == v) {
            // 命中，虚拟节点返回命中，真实节点正确返回
            return node.getValue() != null;
        }
        // 递归的查询左右节点
        HfmNode<T> leftChild = node.getLeftChild();
        if (leftChild != null) {
            boolean search = search(leftChild, number, stringBuilder);
            if (search) {
                // 命中左边字节点
                stringBuilder.append("0");
                return search;
            }
        }
        boolean search = search(node.getRightChild(), number, stringBuilder);
        if (search) {
            // 命中右边字节点
            stringBuilder.append("1");
        }
        return search;
    }

    /**
     * 根据编码获取对应的节点，找不到返回null
     *
     * @param code 二进制编码
     */
    public HfmNode<T> getByCode(String code) {
        if (code.length() > length || root == null) {
            return null;
        }
        HfmNode<T> node = root;
        for (char c : code.toCharArray()) {
            if (node == null) {
                return null;
            }
            // 0左1右
            if ('0' == c) {
                node = node.getLeftChild();
            } else if ('1' == c) {
                node = node.getRightChild();
            } else {
                throw new RuntimeException("不能识别的二进制编码：" + c);
            }
        }
        // 如果命中，检查value是否不为空
        return node == null ? null : (node.getValue() != null ? node : null);
    }

    public void print() {
        root.print();
    }


}
