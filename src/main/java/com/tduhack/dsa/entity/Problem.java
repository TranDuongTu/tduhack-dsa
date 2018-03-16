package com.tduhack.dsa.entity;

import com.tduhack.Field;
import com.tduhack.HasName;

public interface Problem extends HasName {
  Field<Integer> level = new Field<>("level", Integer.class);
}
