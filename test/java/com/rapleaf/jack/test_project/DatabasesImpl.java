
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project;

import com.rapleaf.jack.BaseDatabaseConnection;
import com.rapleaf.jack.DatabaseConnection;
import com.rapleaf.jack.test_project.database_1.IDatabase1;
import com.rapleaf.jack.test_project.database_1.impl.Database1Impl;
import com.rapleaf.jack.tracking.PostQueryAction;
import com.rapleaf.jack.tracking.NoOpAction;

public class DatabasesImpl implements IDatabases {
  private IDatabase1 database1;

  public DatabasesImpl() {
  }

  public DatabasesImpl(BaseDatabaseConnection database1_connection) {
    this(new NoOpAction(), database1_connection);
  }

  public DatabasesImpl(PostQueryAction postQueryAction, BaseDatabaseConnection database1_connection) {
    this.database1 = new Database1Impl(database1_connection, this, postQueryAction);
  }

  public IDatabase1 getDatabase1() {
    if (database1 == null) {
      this.database1 = new Database1Impl(new DatabaseConnection("database1"), this, new NoOpAction());
    }
    return database1;
  }
}
