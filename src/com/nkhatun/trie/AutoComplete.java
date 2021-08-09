package com.nkhatun.trie;

import java.util.ArrayList;
import java.util.List;


public class AutoComplete {

	public static List<String> prefixWith(String prefix) {
		List<String> matchedList = new ArrayList<String>();
		if (prefix == null) {
			return null;
		}
		TrieOptimized.TrieNode temp = TrieOptimized.root;
		StringBuilder curr = new StringBuilder();
		for (char ch : prefix.toCharArray()) {
			temp = temp.children.get(ch);
			if (temp == null) {
				return matchedList;
			}
			curr.append(ch);
		}
		prefixRecur(temp, matchedList, curr);

		return matchedList;
	}

	private static void prefixRecur(TrieOptimized.TrieNode temp, List<String> matchedList,
			StringBuilder curr) {
		if (temp.isENdOfWord) {
			matchedList.add(curr.toString());
		}
		if (temp.children == null || temp.children.isEmpty()) {
			return;
		}
		temp.children.entrySet().forEach(children -> {
			prefixRecur(children.getValue(), matchedList,
					curr.append(children.getKey()));
			curr.setLength(curr.length() - 1);
		});
	}

	public static void main(String[] args) {
		TrieOptimized.root = new TrieOptimized.TrieNode();
		TrieOptimized.insertKey("abc");
		TrieOptimized.insertKey("the");
		TrieOptimized.insertKey("dark");
		TrieOptimized.insertKey("and");
		TrieOptimized.insertKey("dear");
		System.out.println("Search prefix............");
		System.out.println(prefixWith("da"));
	}

}
