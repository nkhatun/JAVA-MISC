package com.nkhatun.trie;

import com.nkhatun.trie.TrieOptimized.TrieNode;

public class CountDistinctStringSuffix {

	static class SuffixTrieNode
    {
		
    }
	
	public static void insertSuffixTrie(String word) {
		if(word.length() == 0 ) {
			return;
		}
		for(int i = 0;i < word.length()+1;i++) {
			insertKey(word.substring(0,i));
		}
	}
	public static void insertKey(String word) {
		TrieNode temp = null;//root;
		for (char c : word.toCharArray()) {
			if (!temp.children.containsKey(c)) {
				temp.children.put(c, new TrieNode());
			}
			temp = temp.children.get(c);
		}
		temp.isENdOfWord = true;
	}
	public static void countSuffixNode(TrieNode root, StringBuilder count) {
		if (root.isENdOfWord || root.children.isEmpty()) {
			System.out.println(count);
		}
		root.children.entrySet().forEach(children -> {
			countSuffixNode(children.getValue(), count.append(children.getKey().toString()));
		});
	}
	
	public static void main(String[] args) {

	}

}
