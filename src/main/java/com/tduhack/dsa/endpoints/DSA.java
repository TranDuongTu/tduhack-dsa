package com.tduhack.dsa.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.tduhack.HasFields;
import com.tduhack.appengine.Bean;
import com.tduhack.appengine.DataStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Api(name = "dsa", version = "v1")
public class DSA {

  @ApiMethod(name = "all_problems", path = "problems", httpMethod = ApiMethod.HttpMethod.GET)
  public ProblemList allProblems() {
    final ProblemList problemList = new ProblemList();
    final List<Problem> problems = new DataStore().query("Problem").stream()
            .map(this::transform).collect(Collectors.toList());
    problemList.setProblems(problems);
    return problemList;
  }

  @ApiMethod(name = "problems_with_level", path = "problems/{level}", httpMethod = ApiMethod.HttpMethod.GET)
  public ProblemList problemsWithLevel(final @Named("level") Integer level) {
    final ProblemList problemList = new ProblemList();
    final List<Bean> problems = new DataStore().query("Problem").toList();
    final List<HasFields> selectedProblems = findProblemsWithGivenLevelSum(problems, level);
    problemList.setProblems(selectedProblems.stream().map(this::transform).collect(Collectors.toList()));
    return problemList;
  }

  private List<HasFields> findProblemsWithGivenLevelSum(final List<Bean> problems, final int levelSum) {
    Collections.shuffle(problems);
    final List<HasFields> result = new ArrayList<>();
    long start = 0, end = 0, currentSum = problems.get(0).get(com.tduhack.dsa.entity.Problem.level);
    while (end < problems.size()) {
      if (currentSum == levelSum) {
        LongStream.range(start, end + 1).forEach(i -> result.add(problems.get((int) i)));
        break;
      } else if (currentSum > levelSum && start < end) {
        currentSum -= problems.get((int) start++).get(com.tduhack.dsa.entity.Problem.level);
      } else {
        currentSum += problems.get((int) ++end).get(com.tduhack.dsa.entity.Problem.level);
      }
    }
    return result;
  }

  private Problem transform(final HasFields hasFields) {
    final Problem problem = new Problem();
    problem.setName(hasFields.get(com.tduhack.dsa.entity.Problem.name));
    problem.setLevel(hasFields.get(com.tduhack.dsa.entity.Problem.level));
    return problem;
  }
}
