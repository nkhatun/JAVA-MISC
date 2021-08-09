package com.nkhatun.trie;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import com.nkhatun.trie.TrieOptimized.TrieNode;

public class LongestCommonPrefix {

	private static void insert(TrieOptimized.TrieNode head, String str) {
		TrieOptimized.TrieNode curr = head;
		for (char c : str.toCharArray()) {
			curr.children.putIfAbsent(c, new TrieOptimized.TrieNode());
			curr = curr.children.get(c);
		}
		curr.isENdOfWord = true;
	}
	
	public static String findLCP(List<String> dict) {
		TrieOptimized.TrieNode root = new TrieOptimized.TrieNode();
		for (String s : dict) {
			insert(root,s);
		}
		StringBuilder lcp = new StringBuilder();
		TrieOptimized.TrieNode temp = root;
		
		while(temp != null && !temp.isENdOfWord && temp.children.size() == 1) {
			for(Entry<Character, TrieNode> entry: temp.children.entrySet()) {
				lcp.append(entry.getKey());
				temp = entry.getValue();
			}
		}
		return lcp.toString();
	}
	
	 public static void main (String[] args)
	    {
	        List<String> dict = Arrays.asList("code", "coder");
	 
	        System.out.println("The longest common prefix is " + findLCP(dict));
	    }

}
