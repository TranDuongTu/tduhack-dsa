package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.List;

@ProblemAnnotation(name = "Flatten list of lists", level = 2)
public class FlattenList {
  
  public static List<Object> flatten(final List<Object> list) {
    final List<Object> result = new ArrayList<>();
    flattenRecursive(list, result);
    return result;
  }

  private static void flattenRecursive(List<Object> list, final List<Object> result) {
    for (final Object element : list) {
      if (element instanceof List) {
        flattenRecursive((List<Object>) element, result);
      } else {
        result.add(element);
      }
    }
  }
}
