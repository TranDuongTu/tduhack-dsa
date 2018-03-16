package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Binary Search Tree", level = 3)
public class BST<Key extends Comparable<Key>, Value> {

  private Node root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return size(root);
  }

  private int size(Node node) {
    return node == null ? 0 : node.size;
  }

  public boolean contains(final Key key) {
    return get(root, key) != null;
  }

  private Node get(Node node, Key key) {
    if (node == null) return null;
    final int compare = key.compareTo(node.key);
    if (compare < 0) return get(node.left, key);
    else if (compare > 0) return get(node.right, key);
    else return node;
  }

  public Value get(final Key key) {
    final Node node = get(root, key);
    return node == null ? null : node.value;
  }

  public void put(final Key key, final Value value) {
    if (value == null) {
      delete(key);
      return;
    }
    this.root = put(root, key, value);
  }

  private Node put(Node node, Key key, Value value) {
    if (node == null) return new Node(key, value);
    final int compare = key.compareTo(node.key);
    if (compare < 0) node.left = put(node.left, key, value);
    else if (compare > 0) node.right = put(node.right, key, value);
    else node.value = value;
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  public void delete(final Key key) {
    root = delete(root, key);
  }

  private Node delete(Node node, Key key) {
    if (node == null) return null;
    final int compare = key.compareTo(node.key);
    if (compare < 0) node.left = delete(node.left, key);
    else if (compare > 0) node.right = delete(node.right, key);
    else {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      final Node t = node;
      node = min(t.right);
      node.right = deleteMin(t.right);
      node.left = t.left;
    }
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }

  private Node deleteMin(Node node) {
    if (node.left == null) return node.right;
    node.left = deleteMin(node.left);
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  public Value min() {
    return min(root).value;
  }

  private Node min(Node node) {
    if (node == null) return null;
    return node.left == null ? node : min(node.left);
  }

  private class Node {
    public Key key;
    public Value value;
    public Node left, right;
    public int size;

    public Node(final Key key, final Value value) {
      this.key = key;
      this.value = value;
    }
  }
}
