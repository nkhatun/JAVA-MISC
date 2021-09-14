package com.nkhatun.trie;

public class CountInversions {
    static int inversionCount;
    static class Node{
        Node left;
        Node right;
        int count;
    }
    static Node makeNewNode()
    {
        Node temp = new Node();
        temp.count = 1;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    public static void insertElement(int num, Node root){
        for (int i= 63 ;i >= 0;i--){
            int a = num & (1 << i);
            if(a != 0){
                if(root.left != null){
                    inversionCount += root.left.count;
                }
                if(root.right != null){
                    root = root.right;
                    root.count += 1;
                }
                else{
                    Node temp = makeNewNode();
                    root.right = temp;
                    root = root.right;
                }
            }
            else{
                if (root.left != null)
                {
                    root = root.left;
                    root.count++;
                }
                else
                {
                    Node temp = makeNewNode();
                    root.left = temp;
                    root = root.left;
                }
            }
        }
    }

    static int getInvCount(int arr[], int n)
    {
        Node head = makeNewNode();
        inversionCount = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            insertElement(arr[i], head);
        }

        return inversionCount;
    }
    public static void main(String[] args)
    {
        int arr[] = { 8, 4, 2, 1 };
        int n = arr.length;

        System.out.print("Number of inversions are : "
                + getInvCount(arr, n));
    }

}
