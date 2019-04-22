package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, CacheNode> cache;
    private int size;
    private int capacity;
    private CacheNode head, tail;

    public LRUCache(int capacity){
        cache = new HashMap<Integer, CacheNode>();
        this.size = 0;
        this.capacity = capacity;

        head = new CacheNode();
        head.pre = null;

        tail = new CacheNode();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    public void set(int key, int value){
        CacheNode node = cache.get(key);
        if (node == null){
            CacheNode newNode = new CacheNode();
            newNode.key = key;
            newNode.value = value;
            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++size;

            if (size > capacity){
                CacheNode tail = this.popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            this.moveToHead(node);
        }
        System.out.println(String.format("set(%d, %d): ", key, value) + this.toString() );
    }

    public int get(int key){
        CacheNode node = cache.get(key);
        if (node == null){
            return -1;
        }
        this.moveToHead(node);

        System.out.println(String.format("get(%d): ", key) + this.toString());
        return node.value;
    }

    /**
     * 只在头节点之后创建新节点
     * @param node
     */
    private void addNode(CacheNode node){
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private CacheNode popTail(){
        CacheNode node = tail.pre;
        this.removeNode(node);
        return node;
    }

    private void removeNode(CacheNode node){
        CacheNode preNode = node.pre;
        CacheNode nextNode = node.next;

        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    private void moveToHead(CacheNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        CacheNode node = head.next;
        while (node.next != null){
            buffer.append(String.format("(%d,%d)  ", node.key, node.value));
            node = node.next;
        }
        return buffer.toString();
    }

    class CacheNode{
        CacheNode pre;
        CacheNode next;
        Integer key;
        Integer value;
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.set(4, 4);
        lru.set(3, 3);
        lru.get(4);
        lru.set(2, 2);
        lru.get(3);
        lru.set(1, 1);
        lru.set(4, 4);
        lru.set(2, 2);
    }

}
