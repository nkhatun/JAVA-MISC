package com.nkhatun.linkedlist;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LRUCache {
	
	private static int CAPACITY = 5;
	private static int count = 0;
	private static Node head;
	private static Map<Integer,Node> nodeMap = new HashMap<>();
	
	static class Node{
		int data;
		Node next;
		Node prev;
		Node(int data){
			this.data = data;
			this.next = this.prev = null;
		}
	}
	
	public static boolean isCacheFull(){
		count = count + 1;
		if(count <= CAPACITY) {
			return false;
		}
		return true;
	}
	public static boolean isCacheEmpty(){
		if(count == 0) {
			return true;
		}
		return false;
	}
	public static void addEntry(int data) {
		if(isCacheEmpty()) {
			Node new_node = new Node(data);
			head = new_node;
			count = count + 1;
			nodeMap.put(data, head);
		}
		
		else if(count < CAPACITY) {
			count = count + 1;
			if(nodeMap.containsKey(data)) {
				Node existing_node = nodeMap.get(data);
				// delete node
				deleteNode(existing_node);
				//add to head
				addToHead(existing_node);
			}
			else {
				Node new_node = new Node(data);
				//add to head
				addToHead(new_node);
			}
		}
		else {
			Node tail_node = head;
			while(tail_node.next != null) {
				tail_node = tail_node.next;
			}
			deleteNode(tail_node);
			nodeMap.remove(tail_node.data);
			count = count - 1;
			Node new_node = new Node(data);
			addToHead(new_node);
		}	
	}
	
	private static void addToHead(Node node) {
		node.next = head;
		head.prev = node;
		head = node;	
		nodeMap.put(node.data, head);
	}
	private static void deleteNode(Node existing_node) {
		if(existing_node.next != null) {
			existing_node.next.prev = existing_node.prev;
		}
		existing_node.prev.next = existing_node.next;
	}
	public static int getEntry(int data) {
		Node temp = nodeMap.get(data);
		if(temp != null) {
			addEntry(data);
			return data;
		}
		else {
			System.out.println("Entry not found");
			return -1;
		}
	}
	
	public static void displayCache() {
		Node temp_node = head;
		while(temp_node != null) {
			System.out.println(temp_node.data);
			temp_node = temp_node.next;
		}
		System.out.println("----------------");
	}
	
	public static void main(String args[]) {
		addEntry(1);
		addEntry(2);
		addEntry(3);
		addEntry(4);
		addEntry(5);
		displayCache();
		addEntry(6);
		displayCache();
		getEntry(1);
		getEntry(5);
		displayCache();
	}
}
