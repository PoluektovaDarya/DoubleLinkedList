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
            // Проверяем совпадение трех подряд идущих элементов
            if (current.data.equals(prevNodeData) && current.next.data.equals(currentNodeData) && current.next.next.data.equals(nextNodeData)) {
                // Нашли совпадение, удаляем средний элемент
                Node middleNode = current.next;
                current.next = middleNode.next;
                if (middleNode.next != null) {
                    middleNode.next.prev = current;
                } else {
                    // Если middleNode был последним элементом, обновляем tail
                    tail = current;
                }
                return;
            }

            current = current.next;
        }

        // Не нашли совпадение
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
        // Преобразование узлов в список
        List<Integer> nodeList = new ArrayList<>();
        Node current = head;

        while (current != null) {
            nodeList.add(Integer.parseInt(current.data));
            current = current.next;
        }

        // Сортировка списка
        Collections.sort(nodeList);

        // Обновление значений узлов
        current = head;
        for (int value : nodeList) {
            current.data = Integer.toString(value);
            current = current.next;
        }
    }

    private Node[] convertToArray() {
        Node current = head;
        int size = 0;

        // Count the number of elements in the linked list
        while (current != null) {
            size++;
            current = current.next;
        }

        // Create an array to hold the elements
        Node[] array = new Node[size];
        current = head;
        int index = 0;

        // Copy elements to the array
        while (current != null) {
            array[index++] = current;
            current = current.next;
        }

        return array;
    }

    private void rebuildList(Node[] array) {
        head = array[0];
        tail = array[array.length - 1];

        for (int i = 0; i < array.length - 1; i++) {
            array[i].next = array[i + 1];
            array[i + 1].prev = array[i];
        }

        array[array.length - 1].next = null;
    }

    private void quickSort(Node[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
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
