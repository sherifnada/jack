
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project.database_1.impl;

import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import com.rapleaf.jack.AbstractDatabaseModel;
import com.rapleaf.jack.BaseDatabaseConnection;
import com.rapleaf.jack.queries.WhereConstraint;
import com.rapleaf.jack.queries.WhereClause;
import com.rapleaf.jack.test_project.database_1.iface.ITestStorePersistence;
import com.rapleaf.jack.test_project.database_1.models.TestStore;
import com.rapleaf.jack.test_project.database_1.query.TestStoreQueryBuilder;
import com.rapleaf.jack.test_project.database_1.query.TestStoreDeleteBuilder;

import com.rapleaf.jack.test_project.IDatabases;

public class BaseTestStorePersistenceImpl extends AbstractDatabaseModel<TestStore> implements ITestStorePersistence {
  private final IDatabases databases;

  public BaseTestStorePersistenceImpl(BaseDatabaseConnection conn, IDatabases databases) {
    super(conn, "test_store", Arrays.<String>asList("entry_type", "entry_scope", "entry_key", "entry_value", "created_at", "updated_at"));
    this.databases = databases;
  }

  @Override
  public TestStore create(Map<Enum, Object> fieldsMap) throws IOException {
    Integer entry_type = (Integer) fieldsMap.get(TestStore._Fields.entry_type);
    Long entry_scope = (Long) fieldsMap.get(TestStore._Fields.entry_scope);
    String entry_key = (String) fieldsMap.get(TestStore._Fields.entry_key);
    String entry_value = (String) fieldsMap.get(TestStore._Fields.entry_value);
    Long created_at = (Long) fieldsMap.get(TestStore._Fields.created_at);
    Long updated_at = (Long) fieldsMap.get(TestStore._Fields.updated_at);
    return create(entry_type, entry_scope, entry_key, entry_value, created_at, updated_at);
  }

  public TestStore create(final Integer entry_type, final Long entry_scope, final String entry_key, final String entry_value, final Long created_at, final Long updated_at) throws IOException {
    StatementCreator statementCreator = new StatementCreator() {
      private final List<String> nonNullFields = new ArrayList<>();
      private final List<AttrSetter> statementSetters = new ArrayList<>();

      {
        int index = 1;

        if (entry_type != null) {
          nonNullFields.add("entry_type");
          int fieldIndex0 = index++;
          statementSetters.add(stmt -> stmt.setInt(fieldIndex0, entry_type));
        }

        if (entry_scope != null) {
          nonNullFields.add("entry_scope");
          int fieldIndex1 = index++;
          statementSetters.add(stmt -> stmt.setLong(fieldIndex1, entry_scope));
        }

        if (entry_key != null) {
          nonNullFields.add("entry_key");
          int fieldIndex2 = index++;
          statementSetters.add(stmt -> stmt.setString(fieldIndex2, entry_key));
        }

        if (entry_value != null) {
          nonNullFields.add("entry_value");
          int fieldIndex3 = index++;
          statementSetters.add(stmt -> stmt.setString(fieldIndex3, entry_value));
        }

        if (created_at != null) {
          nonNullFields.add("created_at");
          int fieldIndex4 = index++;
          statementSetters.add(stmt -> stmt.setTimestamp(fieldIndex4, new Timestamp(created_at)));
        }

        if (updated_at != null) {
          nonNullFields.add("updated_at");
          int fieldIndex5 = index++;
          statementSetters.add(stmt -> stmt.setTimestamp(fieldIndex5, new Timestamp(updated_at)));
        }
      }

      @Override
      public String getStatement() {
        return getInsertStatement(nonNullFields);
      }

      @Override
      public void setStatement(PreparedStatement statement) throws SQLException {
        for (AttrSetter setter : statementSetters) {
          setter.set(statement);
        }
      }
    };

    long __id = realCreate(statementCreator);
    TestStore newInst = new TestStore(__id, entry_type, entry_scope, entry_key, entry_value, created_at, updated_at, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }

  public TestStore create() throws IOException {
    StatementCreator statementCreator = new StatementCreator() {
      private final List<String> nonNullFields = new ArrayList<>();
      private final List<AttrSetter> statementSetters = new ArrayList<>();

      @Override
      public String getStatement() {
        return getInsertStatement(nonNullFields);
      }

      @Override
      public void setStatement(PreparedStatement statement) throws SQLException {
        for (AttrSetter setter : statementSetters) {
          setter.set(statement);
        }
      }
    };

    long __id = realCreate(statementCreator);
    TestStore newInst = new TestStore(__id, null, null, null, null, null, null, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }

  public TestStore createDefaultInstance() throws IOException {
    return create();
  }

  public List<TestStore> find(Map<Enum, Object> fieldsMap) throws IOException {
    return find(null, fieldsMap);
  }

  public List<TestStore> find(Set<Long> ids, Map<Enum, Object> fieldsMap) throws IOException {
    List<TestStore> foundList = new ArrayList<TestStore>();

    if (fieldsMap == null || fieldsMap.isEmpty()) {
      return foundList;
    }

    StringBuilder statementString = new StringBuilder();
    statementString.append("SELECT * FROM test_store WHERE (");
    List<Object> nonNullValues = new ArrayList<Object>();
    List<TestStore._Fields> nonNullValueFields = new ArrayList<TestStore._Fields>();

    Iterator<Map.Entry<Enum, Object>> iter = fieldsMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<Enum, Object> entry = iter.next();
      Enum field = entry.getKey();
      Object value = entry.getValue();

      String queryValue = value != null ? " = ? " : " IS NULL";
      if (value != null) {
        nonNullValueFields.add((TestStore._Fields) field);
        nonNullValues.add(value);
      }

      statementString.append(field).append(queryValue);
      if (iter.hasNext()) {
        statementString.append(" AND ");
      }
    }
    if (ids != null) statementString.append(" AND ").append(getIdSetCondition(ids));
    statementString.append(")");

    int retryCount = 0;
    PreparedStatement preparedStatement;

    while (true) {
      preparedStatement = getPreparedStatement(statementString.toString());

      for (int i = 0; i < nonNullValues.size(); i++) {
        TestStore._Fields field = nonNullValueFields.get(i);
        try {
          switch (field) {
            case entry_type:
              preparedStatement.setInt(i+1, (Integer) nonNullValues.get(i));
              break;
            case entry_scope:
              preparedStatement.setLong(i+1, (Long) nonNullValues.get(i));
              break;
            case entry_key:
              preparedStatement.setString(i+1, (String) nonNullValues.get(i));
              break;
            case entry_value:
              preparedStatement.setString(i+1, (String) nonNullValues.get(i));
              break;
            case created_at:
              preparedStatement.setTimestamp(i+1, new Timestamp((Long) nonNullValues.get(i)));
              break;
            case updated_at:
              preparedStatement.setTimestamp(i+1, new Timestamp((Long) nonNullValues.get(i)));
              break;
          }
        } catch (SQLException e) {
          throw new IOException(e);
        }
      }

      try {
        executeQuery(foundList, preparedStatement);
        return foundList;
      } catch (SQLRecoverableException e) {
        if (++retryCount > AbstractDatabaseModel.MAX_CONNECTION_RETRIES) {
          throw new IOException(e);
        }
      } catch (SQLException e) {
        throw new IOException(e);
      }
    }
  }

  @Override
  protected void setStatementParameters(PreparedStatement preparedStatement, WhereClause whereClause) throws IOException {
    int index = 0;
    for (WhereConstraint constraint : whereClause.getWhereConstraints()) {
      for (Object parameter : constraint.getParameters()) {
        if (parameter == null) {
          continue;
        }
        try {
          if (constraint.isId()) {
            preparedStatement.setLong(++index, (Long)parameter);
          } else {
            TestStore._Fields field = (TestStore._Fields)constraint.getField();
            switch (field) {
              case entry_type:
                preparedStatement.setInt(++index, (Integer) parameter);
                break;
              case entry_scope:
                preparedStatement.setLong(++index, (Long) parameter);
                break;
              case entry_key:
                preparedStatement.setString(++index, (String) parameter);
                break;
              case entry_value:
                preparedStatement.setString(++index, (String) parameter);
                break;
              case created_at:
                preparedStatement.setTimestamp(++index, new Timestamp((Long) parameter));
                break;
              case updated_at:
                preparedStatement.setTimestamp(++index, new Timestamp((Long) parameter));
                break;
            }
          }
        } catch (SQLException e) {
          throw new IOException(e);
        }
      }
    }
  }

  @Override
  protected void setAttrs(TestStore model, PreparedStatement stmt, boolean setNull) throws SQLException {
    int index = 1;
    if (setNull && model.getEntryType() == null) {
      stmt.setNull(index++, java.sql.Types.INTEGER);
    } else if (model.getEntryType() != null) {
      stmt.setInt(index++, model.getEntryType());
    }
    if (setNull && model.getEntryScope() == null) {
      stmt.setNull(index++, java.sql.Types.INTEGER);
    } else if (model.getEntryScope() != null) {
      stmt.setLong(index++, model.getEntryScope());
    }
    if (setNull && model.getEntryKey() == null) {
      stmt.setNull(index++, java.sql.Types.CHAR);
    } else if (model.getEntryKey() != null) {
      stmt.setString(index++, model.getEntryKey());
    }
    if (setNull && model.getEntryValue() == null) {
      stmt.setNull(index++, java.sql.Types.CHAR);
    } else if (model.getEntryValue() != null) {
      stmt.setString(index++, model.getEntryValue());
    }
    if (setNull && model.getCreatedAt() == null) {
      stmt.setNull(index++, java.sql.Types.DATE);
    } else if (model.getCreatedAt() != null) {
      stmt.setTimestamp(index++, new Timestamp(model.getCreatedAt()));
    }
    if (setNull && model.getUpdatedAt() == null) {
      stmt.setNull(index++, java.sql.Types.DATE);
    } else if (model.getUpdatedAt() != null) {
      stmt.setTimestamp(index++, new Timestamp(model.getUpdatedAt()));
    }
    stmt.setLong(index, model.getId());
  }

  @Override
  protected TestStore instanceFromResultSet(ResultSet rs, Set<Enum> selectedFields) throws SQLException {
    boolean allFields = selectedFields == null || selectedFields.isEmpty();
    long id = rs.getLong("id");
    return new TestStore(id,
      allFields || selectedFields.contains(TestStore._Fields.entry_type) ? getIntOrNull(rs, "entry_type") : null,
      allFields || selectedFields.contains(TestStore._Fields.entry_scope) ? getLongOrNull(rs, "entry_scope") : null,
      allFields || selectedFields.contains(TestStore._Fields.entry_key) ? rs.getString("entry_key") : null,
      allFields || selectedFields.contains(TestStore._Fields.entry_value) ? rs.getString("entry_value") : null,
      allFields || selectedFields.contains(TestStore._Fields.created_at) ? getDateAsLong(rs, "created_at") : null,
      allFields || selectedFields.contains(TestStore._Fields.updated_at) ? getDateAsLong(rs, "updated_at") : null,
      databases
    );
  }

  public List<TestStore> findByEntryType(final Integer value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.entry_type, value));
  }

  public List<TestStore> findByEntryScope(final Long value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.entry_scope, value));
  }

  public List<TestStore> findByEntryKey(final String value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.entry_key, value));
  }

  public List<TestStore> findByEntryValue(final String value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.entry_value, value));
  }

  public List<TestStore> findByCreatedAt(final Long value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.created_at, value));
  }

  public List<TestStore> findByUpdatedAt(final Long value) throws IOException {
    return find(Collections.<Enum, Object>singletonMap(TestStore._Fields.updated_at, value));
  }

  public TestStoreQueryBuilder query() {
    return new TestStoreQueryBuilder(this);
  }

  public TestStoreDeleteBuilder delete() {
    return new TestStoreDeleteBuilder(this);
  }
}
