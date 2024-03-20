package com.vshpynta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class DeleteNode {

    //https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        var next = node.next;
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListNode {
        int val;
        ReverseLinkedList.ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
