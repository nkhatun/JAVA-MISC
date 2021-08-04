package com.nkhatun.trie;

public class SimpleTrie {
	static TrieNode root;
	static final int ALPHABET = 26;

	public static class TrieNode {
		boolean isENdOfWord;
		TrieNode[] children = new TrieNode[ALPHABET];

		TrieNode() {
			isENdOfWord = false;
			for (int i = 0; i < ALPHABET; i++) {
				children[i] = null;
			}
		}
	}

	public static void insertKey(String word) {
		int len = word.length();
		int level;
		int index;
		TrieNode temp = root;
		for (level = 0; level < len; level++) {
			index = word.charAt(level) - 'a';
			if (temp.children[index] == null) {
				temp.children[index] = new TrieNode();
			}
			temp = temp.children[index];
		}
		temp.isENdOfWord = true;
	}

	public static void display(TrieNode root, char[] str, int depth) {
		int level;
		if (root.isENdOfWord) {
			displayNode(str,depth);
		}
		for (level = 0; level < ALPHABET; level++) {
			if (root.children[level] != null) {
				str[depth] = (char) (level + 'a');
				display(root.children[level], str,
						depth + 1);
			}
		}
	}
	public static void displayNode(char[] str, int depth) {
			for(int i= 0;i<depth;i++) {
				System.out.println(str[i]);
			}
	}
	
	public static boolean search(String word) {
		int len = word.length();
		int level;
		int index;
		TrieNode temp = root;
		for(level = 0; level < len; level++) {
			index = word.charAt(level) - 'a';
			if(temp.children[index] == null) {
				return false;
			}
			temp = temp.children[index];
		}
		return temp.isENdOfWord;
	}
	
	public static TrieNode remove(TrieNode root, String word, int depth) {
		if(root == null) {
			return null;
		}
		int len = word.length();
		if(len == depth) {
			if(root.isENdOfWord) {
				root.isENdOfWord = false;
			}
			if(isEmpty(root)) {
				root = null;
			}
			 return root;
		}
		
		int index = word.charAt(depth) - 'a';
		root.children[index] = remove(root.children[index], word, depth+1);
		if(!root.isENdOfWord && isEmpty(root)) {
			root = null;
		}	
		return root;
	}
	
	public static boolean isEmpty(TrieNode root) {
		for(int level = 0; level < ALPHABET; level++) {
			if(root.children[level] != null) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		root = new TrieNode();
		insertKey("a");
		insertKey("the");
		insertKey("abc");
		char[] str = new char[200];
		System.out.println("Before............");
		display(root, str, 0);
		remove(root,"abc",0);
		System.out.println("After............");
		display(root, str, 0);
		System.out.println(search("a"));
	}

}
