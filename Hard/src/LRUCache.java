/**
 * Created by youzhang on 2017/10/6.
 */
import java.util.Hashtable;
public class LRUCache {
    Hashtable<Integer, Node> cache = new Hashtable<Integer, Node>();
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        cache = new Hashtable<Integer, Node>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node current = cache.get(key);
        if(current==null) return -1;
        Node prev = current.prev;
        Node next = current.next;
        prev.next = next;
        next.prev = prev;
        Node temp = tail.prev;
        tail.prev = current;
        current.next = tail;
        current.prev = temp;
        temp.next = current;
        return current.value;
    }

    public void put(int key, int value) {
        Node current = cache.get(key);
        if(current==null){
            if(cache.size()==capacity){
                Node removeNode = head.next;
                Node next = removeNode.next;
                head.next = next;
                next.prev = head;
                cache.remove(removeNode.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            Node tailPrev = tail.prev;
            tail.prev = newNode;
            newNode.next = tail;
            newNode.prev = tailPrev;
            tailPrev.next = newNode;
        }
        else{
            current.value = value;
            get(key);
        }
    }
}

class Node{
    Node prev;
    Node next;
    int key;
    int value;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
