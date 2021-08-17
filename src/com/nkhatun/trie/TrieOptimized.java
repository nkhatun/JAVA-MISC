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
			this.isENdOfWord = false;
			this.children = new HashMap<Character, TrieNode>();
		}
	}

	public static void insertKey(String word) {
		TrieNode temp = root;
		for (char c : word.toCharArray()) {
			if (!temp.children.containsKey(c)) {
				temp.children.put(c, new TrieNode());
			}
			temp = temp.children.get(c);
		}
		temp.isENdOfWord = true;
	}

	public static boolean search(String word) {
		TrieNode temp = root;
		for (char c : word.toCharArray()) {
			if (!temp.children.containsKey(c)) {
				return false;
			}
			temp = temp.children.get(c);
		}
		return temp.isENdOfWord;
	}

	public static boolean isEmpty(TrieNode root) {
		if (root.children != null && root.children.size() > 0) {
			return false;
		}
		return true;
	}
	public static boolean delete(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}
		TrieNode deleteBelow = null;
		char deleteChar = '\0';

		TrieNode parent = root;
		for (int i = 0; i < word.length(); i++) {
			char cur = word.charAt(i);
			TrieNode child = parent.children.get(cur);
			if (child == null) {
				return false;
			}
			if (parent.children.size() > 1 || parent.isENdOfWord) {
				deleteBelow = parent;
				deleteChar = cur;
			}
			parent = child;
		}
		if (!parent.isENdOfWord) {
			return false;
		}

		if (parent.children.isEmpty()) {
			deleteBelow.children.remove(deleteChar);
		} else {
			parent.isENdOfWord = false;
		}
		return true;
	}
	public static void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not present in trien");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.children.get(ch);
			if (child.children.size() == 1) {
				current.children.remove(ch);
				return;
			} else {
				current = child;
			}
		}
		current.isENdOfWord = false;
	}

	public static boolean startsWith(String prefix) {
		if (prefix == null) {
			return false;
		}

		TrieNode temp = root;
		for (char ch : prefix.toCharArray()) {
			TrieNode child = temp.children.get(ch);
			if (child == null) {
				return false;
			}
			temp = temp.children.get(ch);
		}
		return true;
	}

	public static void displayContent(TrieNode root, StringBuilder str) {
		if (root.isENdOfWord || root.children.isEmpty()) {
			System.out.println(str);
		}

		root.children.entrySet().forEach(children -> {
			displayContent(children.getValue(), new StringBuilder(str)
					.append((children.getKey().toString())));
		});
	}

	public static void main(String args[]) {
		root = new TrieNode();
		insertKey("abc");
		insertKey("the");
		insertKey("and");
		insertKey("dear");
		System.out.println("Before............");
		displayContent(root, new StringBuilder(""));
		delete("and");
		System.out.println("After removal............");
		displayContent(root, new StringBuilder(""));
		System.out.println(search("and"));
		System.out.println(search("abc"));
	}
}
