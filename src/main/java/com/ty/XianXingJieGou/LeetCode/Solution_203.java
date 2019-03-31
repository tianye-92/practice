package com.ty.XianXingJieGou.LeetCode;

/**
 * leetCode: 203. 移除链表元素
 * @ClassName Solution_203
 * @Author tianye
 * @Date 2019/3/13 22:47
 * @Version 1.0
 */
public class Solution_203 {

    public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }

    // 使用正常节点实现
    public ListNode removeElements(ListNode head, int val) {
        // 判断第一个元素是否为需删除的元素
        while (head != null && head.val == val){
            // 删除当前元素
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 判断链表是否还有元素
        if (head == null){
            return null;
        }

        // 删除中间元素
        ListNode prev = head;
        while (prev.next != null){
            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else {
                prev = prev.next;
            }
        }
        return head;
    }

    // 使用虚拟头节点实现
    public ListNode removeElements2(ListNode head, int val) {
        // 新建一个虚拟头节点,只是用于删除元素
        ListNode dummNode = new ListNode(-1);
        dummNode.next = head;
        // 使用一个临时节点来做删除操作,删除的还是dummNode这个链表中的元素,但是这个链表的头节点还是dumNode.next
        ListNode prev = dummNode;
        while (prev.next != null){
            if (prev.next.val == val){
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }
        return dummNode.next;

        // 下面这种写法为什么不可以,因为dummNode一直在改变自身,当前这个链表遍历一遍完之后,dummNode指向的就是链表中的最后一个元素了
//        ListNode dummNode = new ListNode(-1);
//        dummNode.next = head;
//
//        while (dummNode.next != null){
//            if (dummNode.next.val == val){
//                dummNode.next = dummNode.next.next;
//            }else {
//                dummNode = dummNode.next;
//            }
//        }
//        return dummNode;
    }

    // 使用递归实现
    public ListNode removeElements3(ListNode head, int val) {
        // 递归的终止条件
        if (head == null){
            return null;
        }
        // 递归调用
        ListNode res = removeElements3(head.next,val);
        if (head.val == val){
            // 如果当前元素为删除元素,则直接返回上次递归返回的链表
            return res;
        }else {
            // 如果当前元素不为删除元素,则当前元素.next为上次递归返回的链表(就是把当前元素挂在递归返回的链表头)
            head.next = res;
            // 然后返回组成的新链表
            return head;
        }
    }

    // 递归的另一种写法
    public ListNode removeElements4(ListNode head, int val) {
        // 递归的终止条件
        if (head == null){
            return null;
        }
        // 递归调用
        // 使用head接收递归返回的链表
        head.next = removeElements4(head.next,val);
        return head.val == val ? head.next : head;
    }
}
