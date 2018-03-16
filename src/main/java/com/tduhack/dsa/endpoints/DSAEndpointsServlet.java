package com.tduhack.dsa.endpoints;

import com.tduhack.HasFields;
import com.tduhack.HasName;
import com.tduhack.JSON;
import com.tduhack.Strings;
import com.tduhack.appengine.DataStore;
import com.tduhack.appengine.Store;
import com.tduhack.dsa.entity.Problem;

import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DSAEndpointsServlet extends HttpServlet {

  private static final Logger logger = Logger.getLogger(DSAEndpointsServlet.class.getName());

  @Override
  public void init() {
    syncProblems();
  }

  private void syncProblems() {
    logger.log(Level.INFO, "Syncing annotated problems");
    final Store store = new DataStore();
    final Set<String> savedProblems = store.query("Problem").stream()
            .map(p -> p.get(HasName.name))
            .collect(Collectors.toSet());
    final List<HasFields> problems = getAllProblems();
    for (final HasFields problem : problems) {
      final String name = problem.get(Problem.name);
      final int level = problem.get(Problem.level);
      if (!savedProblems.contains(name)) {
        logger.log(Level.INFO, "Saving problem: " + name);
        store.save(store.create("Problem").set(Problem.name, name).set(Problem.level, level));
      }
    }
  }

  public static List<HasFields> getAllProblems() {
    final InputStream inputStream = DSAEndpointsServlet.class.getResourceAsStream("/problems.txt");
    final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    try {
      br.readLine();
      String buff;
      final List<HasFields> problems = new ArrayList<>();
      while (Strings.isNotEmpty(buff = br.readLine())) {
        final String[] tokens = buff.split(",");
        final String name = tokens[0].trim();
        final int level = Integer.valueOf(tokens[1]);
        problems.add(JSON.create().set(Problem.name, name).set(Problem.level, level));
      }
      return problems;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
