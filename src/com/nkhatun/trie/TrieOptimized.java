package com.nkhatun.trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.nkhatun.trie.SimpleTrie.TrieNode;

public class TrieOptimized {
	static TrieNode root;

	public static class TrieNode {
		boolean isENdOfWord;
		Map<Character, TrieNode> children;
		TrieNode() {
			isENdOfWord = false;
			children = new HashMap<Character, TrieNode>();
		}
	}

	public static void insertKey(String word) {
		int len = word.length();
		TrieNode temp = root;
		for (char c : word.toCharArray()) {
			if (!temp.children.containsKey(c)) {
				temp.children.put(c, new TrieNode());
			}
			temp = temp.children.get(c);
		}
		temp.isENdOfWord = true;
	}

	public static void display(TrieNode root, StringBuilder sequence,
			int depth) {
		if (root == null) {
			return;
		}
		if (root.isENdOfWord) {
			displayNode(sequence, depth);
		}
		TrieNode temp = root;
		if (temp.children != null && temp.children.size() > 0) {
			temp.children.entrySet().forEach(children -> {
				sequence.append(children.getKey());
				display(children.getValue(), sequence, depth + 1);
			});
		}
	}
	
	public static boolean search(String word) {
		TrieNode temp = root;
		for (char c : word.toCharArray()) {
			if(!temp.children.containsKey(c)) {
				return false;
			}
			temp = temp.children.get(c);
		}
		return temp.isENdOfWord;
	}

	public static void displayNode(StringBuilder sb, int depth) {
		String str = sb.toString();
		int len = str.length();
		int start = len - depth;
		for (int i = start; i < len; i++) {
			System.out.println(str.charAt(i));
		}
	}
	
	public static boolean isEmpty(TrieNode root) {
		if(root.children != null && root.children.size() > 0) {
			return false;
		}		
		return true;
	}
	public static void main(String args[]) {
		root = new TrieNode();
		insertKey("a");
		insertKey("the");
		insertKey("abc");
		display(root, new StringBuilder(""), 0);
		System.out.println(search("a"));
		System.out.println(search("bc"));
		System.out.println("Before............");
		display(root, new StringBuilder(""), 0);
	
	}
}
