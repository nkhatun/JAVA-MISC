package com.nkhatun.trie;

import java.util.HashMap;
import java.util.Map;


public class Dictionary {
	static DictionaryNode root;

	public static class DictionaryNode {
		boolean isENdOfWord;
		Map<Character, DictionaryNode> children;
		String meaning;
		DictionaryNode() {
			this.isENdOfWord = false;
			this.children = new HashMap<Character, DictionaryNode>();
		}
	}
	public static void insertKey(DictionaryNode root, String word,
			String meaning) {
		if (root == null) {
			root = new DictionaryNode();
		}
		DictionaryNode temp = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!temp.children.containsKey(c)) {
				temp.children.put(c, new DictionaryNode());
			}
			temp = temp.children.get(c);
		}
		temp.isENdOfWord = true;
		temp.meaning = meaning;
	}

	public static String getMeaning(DictionaryNode root, String word) {
		if (root == null) {
			return "";
		}
		DictionaryNode temp = root;
		for (int i = 0; i < word.length(); i++) {
			temp = temp.children.get(word.charAt(i));
	        if (temp == null)
	            return "";
		}
		if (temp.isENdOfWord) {
	        return temp.meaning;
		}
	    return "";
	}
	public static void main(String args[]) {
		root = new DictionaryNode();
		insertKey(root,"dear","zindagi");
		insertKey(root,"one","all");
		System.out.println(getMeaning(root,"one"));
	}
}
