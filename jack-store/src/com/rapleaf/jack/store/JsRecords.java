package com.rapleaf.jack.store;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class JsRecords implements Iterable<JsRecord> {

  private static final JsRecords EMPTY_RECORDS = new JsRecords(Collections.emptyList());

  private final List<JsRecord> jsRecords;

  public JsRecords(List<JsRecord> jsRecords) {
    this.jsRecords = jsRecords;
  }

  public static JsRecords empty() {
    return EMPTY_RECORDS;
  }

  @Override
  public Iterator<JsRecord> iterator() {
    return jsRecords.iterator();
  }

  public List<JsRecord> getRecords() {
    return jsRecords;
  }

  public JsRecord getOnly() {
    Preconditions.checkState(jsRecords.size() == 1, "There are more than one (%s) records", jsRecords.size());
    return jsRecords.get(0);
  }

  public JsRecord getFirst() {
    Preconditions.checkState(jsRecords.size() >= 1, "No record exists");
    return jsRecords.get(0);
  }

  public JsRecord get(int index) {
    Preconditions.checkState(jsRecords.size() > index, "There are only %s record(s), index %s is out of bound", jsRecords.size(), index);
    return jsRecords.get(index);
  }

  public boolean isEmpty() {
    return jsRecords.isEmpty();
  }

  public int size() {
    return jsRecords.size();
  }

  public Stream<JsRecord> stream() {
    return jsRecords.stream();
  }

  @Override
  public int hashCode() {
    return jsRecords.hashCode();
  }

  @Override
  public String toString() {
    return JsRecords.class.getSimpleName() +
        "{" +
        "records=[" + Joiner.on(",").join(jsRecords) + "]" +
        "}";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof JsRecords)) {
      return false;
    }

    JsRecords that = (JsRecords)other;
    return this.jsRecords.equals(that.jsRecords);
  }
}
