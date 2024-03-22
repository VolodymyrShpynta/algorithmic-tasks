package com.vshpynta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoLists {

    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoListsRecursive(list1, list2.next);
        return list2;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        var dummyHead = new ListNode();
        var node = dummyHead;
        var node1 = list1;
        var node2 = list2;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                node.next = node1;
                node = node1;
                node1 = node1.next;
            } else {
                node.next = node2;
                node = node2;
                node2 = node2.next;
            }
        }

        if (node1 != null) {
            node.next = node1;
        } else {
            node.next = node2;
        }

        return dummyHead.next;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
