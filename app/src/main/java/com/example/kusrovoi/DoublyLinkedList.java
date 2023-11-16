package com.example.kusrovoi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.widget.Toast;
public class DoublyLinkedList implements Serializable {
    public Node head;
    public Node tail;
    private Context context;
    public DoublyLinkedList() {
        this.context = context;
    }
    public void append(String data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public void insertBefore(String data, String number) {
        Node newNode = new Node(data);
        Node current = head;
        while (current != null) {
            if (current.data.equals(number)) {
                if (current.prev != null) {
                    current.prev.next = newNode;
                    newNode.prev = current.prev;
                } else {
                    head = newNode;
                }
                newNode.next = current;
                current.prev = newNode;
                return;
            }
            current = current.next;
        }
    }
    public void insertAfter(String data, String number) {
        Node newNode = new Node(data);
        Node current = head;
        while (current != null) {
            if (current.data.equals(number)) {
                if (current.next != null) {
                    current.next.prev = newNode;
                    newNode.next = current.next;
                } else {
                    tail = newNode;
                }
                newNode.prev = current;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
    }
    public void deleteMiddleNode(String prevNodeData, String currentNodeData, String nextNodeData) {
        Node current = head;
        while (current != null && current.next != null && current.next.next != null) {
            if (current.data.equals(prevNodeData) && current.next.data.equals(currentNodeData) && current.next.next.data.equals(nextNodeData)) {
                Node middleNode = current.next;
                current.next = middleNode.next;
                if (middleNode.next != null) {
                    middleNode.next.prev = current;
                } else {
                    tail = current;
                }
                return;
            }
            current = current.next;
        }
        showToast("Нет совпадений для удаления.");
    }
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void deleteNode(String value) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(value)) {
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                } else {
                    head = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }
                break;
            }
            currentNode = currentNode.next;
        }
    }
    public void sort() {
        List<Integer> nodeList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            nodeList.add(Integer.parseInt(current.data));
            current = current.next;
        }
        Collections.sort(nodeList);
        current = head;
        for (int value : nodeList) {
            current.data = Integer.toString(value);
            current = current.next;
        }
    }
    private int partition(Node[] array, int low, int high) {
        Node pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].data.compareTo(pivot.data) < 0) {
                i++;

                Node temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        Node temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
