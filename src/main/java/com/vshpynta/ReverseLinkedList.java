package com.vshpynta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ReverseLinkedList {

    //https://leetcode.com/problems/reverse-linked-list/
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        var prev = (ListNode) null;
        var curr = head;
        var next = curr.next;
        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        return curr;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
