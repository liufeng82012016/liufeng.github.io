package liufeng.algo.leecode.difficult;

import org.junit.Test;

public class MergeKLists_23 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void convertInputs() {
        String input = "[[1,4,5],[1,3,4],[2,6]]";
        System.out.println(input
                .replaceAll("\\[", "{")
                .replaceAll("]", "}"));
    }

    @Test
    public void mergeKLists() {
        int[][] inputs = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] listNodes = new ListNode[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            ListNode listNode = null, currentNode = null;
            for (int j = 0; j < inputs[0].length; j++) {
                try {
                    if (listNode == null) {
                        currentNode = new ListNode(inputs[i][j]);
                        listNode = currentNode;
                    } else {
                        ListNode nextNode = new ListNode(inputs[i][j]);
                        currentNode.next = nextNode;
                        currentNode = nextNode;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            listNodes[i] = listNode;
        }
        print(mergeKLists(listNodes));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists);
    }


    private ListNode merge(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        }
        int validLength = lists.length;
        while (true) {
            int middle = validLength / 2;
            for (int i = 0; i < middle; i++) {
                lists[i] = merge(lists[i], lists[validLength - i - 1]);
                lists[validLength - i - 1] = null;
            }
            if (validLength % 2 == 0) {
                // 偶数，都处理完
                validLength /= 2;
                if (validLength == 1) {
                    return lists[0];
                }
            } else {
                // 奇数
                validLength = (validLength / 2 + 1);
            }
        }
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode result = null, currentNode = null;
        while (true) {
            int value;
            if (left == null && right == null) {
                break;
            } else if (left != null && right == null) {
                value = (left.val);
                left = left.next;
            } else if (left == null && right != null) {
                value = (right.val);
                right = right.next;
            } else {
                if (left.val < right.val) {
                    value = (left.val);
                    left = left.next;
                } else {
                    value = (right.val);
                    right = right.next;
                }
            }
            if (currentNode == null) {
                currentNode = new ListNode(value);
                result = currentNode;
            } else {
                ListNode nextNode = new ListNode(value);
                currentNode.next = nextNode;
                currentNode = nextNode;
            }
        }
        return result;
    }

    private void print(ListNode listNode) {
        while (true) {
            System.out.print(listNode.val + ",");
            if (listNode.next == null) {
                break;
            }
            listNode = listNode.next;
        }
        System.out.println();
    }

}
