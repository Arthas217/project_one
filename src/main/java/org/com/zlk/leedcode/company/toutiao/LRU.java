package org.com.zlk.leedcode.company.toutiao;

import java.util.Hashtable;

/**
 * 哈希表 + 双向链表
 */
public class LRU {

    /**
     * 双向链表
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    /**
     * 初始化size和容量、头尾指针互指向
     */
    public LRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        // head.prev = null;

        tail = new DLinkedNode();
        // tail.next = null;

        head.next = tail;
        tail.prev = head;
    }


    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();

    /**
     * 从哈希表获取key（不存在返回-1）
     * 存在移动元素（删掉此元素、添加到头结点后）返回改节点值
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {return -1;}
        // move the accessed node to the head;
        moveToHead(node);
        return node.value;
    }

    /**
     * 存储kv到哈希表
     * 如果哈希表里有key，更新value，把kv节点移到head后
     * 如果哈希表里没有key，存储kv到cache中 、链表中添加此节点、size++
     * 如果size>容量值 删除链表tail节点、把对应kv从cache中删掉
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);
            ++size;
            if(size > capacity) {
                // pop the tail
                DLinkedNode tailNode = popTail();
                cache.remove(tailNode.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 从头结点后添加node
     */
    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * 链表中移除某元素
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }


    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }

    /**
     * Pop the current tail.
     */
    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }







}
