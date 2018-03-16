package com.tduhack.dsa;

import com.tduhack.HasFields;
import com.tduhack.JSON;
import com.tduhack.dsa.entity.Problem;
import org.junit.Ignore;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProblemsTest {

  @Test
  @Ignore
  public void syncProblemsData() throws Exception {
    final List<HasFields> problems = getAnnotatedProblems();

    final String filePath = "src/main/resources/problems.txt";
    final BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
    bw.write("Name,Level\n");
    for (final HasFields problem : problems) {
      final String name = problem.get(Problem.name);
      final int level = problem.get(Problem.level);
      bw.write(name + "," + level + "\n");
    }
    bw.close();
  }

  public List<HasFields> getAnnotatedProblems() {
    final Reflections reflections = new Reflections("com.tduhack.dsa");
    final Set<Class<?>> problemClasses = reflections.getTypesAnnotatedWith(ProblemAnnotation.class);
    return problemClasses.stream()
            .map(p -> p.getAnnotation(ProblemAnnotation.class))
            .map(this::transform)
            .collect(Collectors.toList());
  }

  private HasFields transform(final ProblemAnnotation annotation) {
    return JSON.create()
            .set(Problem.name, annotation.name())
            .set(Problem.level, annotation.level());
  }
}