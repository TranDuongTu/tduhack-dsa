package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ProblemAnnotation(name = "Tries", level = 4)
public class Tries<V> {
  
  private Node root;
  private final Alphabet alphabet;
  
  public Tries(final Alphabet alphabet) {
    this.alphabet = alphabet;
  }
  
  public void put(final String key, final V value) {
    root = put(root, key, value, 0);
  }

  private Node put(Node node, String key, V value, int d) {
    if (node == null) node = new Node();
    if (d == key.length()) {
      node.value = value;
      return node;
    }
    final Character nextChar = key.charAt(d);
    final int index = alphabet.index(nextChar);
    node.children[index] = put(node.children[index], key, value, d + 1);
    return node;
  }

  public V get(final String key) {
    final Node node = get(root, key, 0);
    return node == null ? null : node.value;
  }

  private Node get(Node node, String key, int d) {
    if (node == null) return null;
    if (key.length() == d) return node;
    final Character nextChar = key.charAt(d);
    final int index = alphabet.index(nextChar);
    return get(node.children[index], key, d + 1);
  }

  public void delete(final String key) {
    root = delete(root, key, 0);
  }

  private Node delete(Node node, String key, int d) {
    if (node == null) return null;
    if (key.length() == d) {
      node.value = null;
    } else {
      final Character nextChar = key.charAt(d);
      final int index = alphabet.index(nextChar);
      node.children[index] = delete(node.children[index], key, d + 1);
    }
    
    if (node.value != null) return node;
    for (int i = 0; i < alphabet.R(); i++) {
      if (node.children[i] != null) {
        return node;
      }
    }
    return null;
  }

  public boolean contains(final String key) {
    return get(key) != null;
  }
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public String longestPrefixOf(final String s) {
    final int length = search(root, s, 0, 0);
    return s.substring(0, length);
  }

  private int search(Node node, String s, int d, int length) {
    if (node == null) return length;
    if (node.value != null) length = d;
    if (s.length() == d) return length;
    final char nextChar = s.charAt(d);
    final int index = alphabet.index(nextChar);
    return search(node.children[index], s, d + 1, length);
  }

  public Iterable<String> keysWithPrefix(final String key) {
    final List<String> result = new ArrayList<>();
    collect(get(root, key, 0), key, result);
    return result;
  }

  private void collect(Node node, final String key, List<String> keys) {
    if (node == null) return;
    if (node.value != null) keys.add(key);
    for (int i = 0; i < node.children.length; i++) {
      collect(node.children[i], key + alphabet.charAt(i), keys);
    }
  }

  public int size() {
    return size(root);
  }

  private int size(Node node) {
    if (node == null) return 0;
    int count = 0;
    if (node.value != null) count++;
    for (int i = 0; i < alphabet.R(); i++) {
      count += size(node.children[i]);
    }
    return count;
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }
  
  private class Node {
    V value;
    Node[] children = (Node[]) Array.newInstance(Node.class, alphabet.R());
  }
  
  public interface Alphabet {
    int R();
    int index(final char character);
    char charAt(final int index);
  }
}
