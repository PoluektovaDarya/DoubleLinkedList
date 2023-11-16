package com.example.kusrovoi;
import java.io.Serializable;
class Node implements Serializable {
    public String data;
    public Node next;
    public Node prev;

    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}