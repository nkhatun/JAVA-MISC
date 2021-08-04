package com.nkhatun.trie;

import java.util.*;
import java.util.Map.Entry;

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

	/** Deletes a word from the trie if present, and return true if the word is deleted successfully. */
	public static boolean delete(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}

		// All nodes below 'deleteBelow' and on the path starting with 'deleteChar' (including itself) will be deleted if needed
		TrieNode deleteBelow = null;
		char deleteChar = '\0';

		// Search to ensure word is present
		TrieNode parent = root;
		for (int i = 0; i < word.length(); i++) {
			char cur = word.charAt(i);

			TrieNode child = parent.children.get(cur); // Check if having a TrieNode associated with 'cur'
			if (child == null) { // null if 'word' is way too long or its prefix doesn't appear in the Trie
				return false;
			}

			if (parent.children.size() > 1 || parent.isENdOfWord) { // Update 'deleteBelow' and 'deleteChar'
				deleteBelow = parent;
				deleteChar = cur;
			}

			parent = child;
		}

		if (!parent.isENdOfWord) { // word isn't in trie
			return false;
		}

		if (parent.children.isEmpty()) {
			deleteBelow.children.remove(deleteChar);
		} else {
			parent.isENdOfWord = false; // Delete word by mark it as not the end of a word
		}

		return true;
	}

	public static TrieNode getNode(TrieNode root,char c){
		if(root == null){return null;}
		TrieNode temp = root;
		for (Entry<Character, TrieNode> f : root.children.entrySet()) {
			if (f.getKey() == c) {
				temp = f.getValue();
			}
		}
		return temp;
	}

	public static void main(String args[]) {
		root = new TrieNode();
		insertKey("abc");
		insertKey("the");
		insertKey("and");
//		display(root, new StringBuilder(""), 0);
		System.out.println(search("a"));
		System.out.println(search("bc"));
		System.out.println("Before............");
//		display(root, new StringBuilder(""), 0);
		delete("abc");
		System.out.println("After removal............");
		display(root, new StringBuilder(""), 0);
	}
}
