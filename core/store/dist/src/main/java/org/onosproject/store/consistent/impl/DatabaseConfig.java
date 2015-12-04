/*
 * Copyright 2015 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.store.consistent.impl;

import com.typesafe.config.ConfigValueFactory;
import net.kuujo.copycat.cluster.ClusterConfig;
import net.kuujo.copycat.cluster.internal.coordinator.CoordinatedResourceConfig;
import net.kuujo.copycat.protocol.Consistency;
import net.kuujo.copycat.resource.ResourceConfig;
import net.kuujo.copycat.state.StateLogConfig;
import net.kuujo.copycat.util.internal.Assert;

import java.util.Map;

/**
 * Database configuration.
 *
 */
public class DatabaseConfig extends ResourceConfig<DatabaseConfig> {
  private static final String DATABASE_CONSISTENCY = "consistency";

  private static final String DEFAULT_CONFIGURATION = "storage-defaults";
  private static final String CONFIGURATION = "storage";

  private String name;

  public DatabaseConfig() {
    super(CONFIGURATION, DEFAULT_CONFIGURATION);
  }

  public DatabaseConfig(Map<String, Object> config) {
    super(config, CONFIGURATION, DEFAULT_CONFIGURATION);
  }

  public DatabaseConfig(String resource) {
    super(resource, CONFIGURATION, DEFAULT_CONFIGURATION);
  }

  protected DatabaseConfig(DatabaseConfig config) {
    super(config);
  }

  @Override
  public DatabaseConfig copy() {
    return new DatabaseConfig(this);
  }

  /**
   * Sets the storage read consistency.
   *
   * @param consistency The storage read consistency.
   * @throws java.lang.NullPointerException If the consistency is {@code null}
   */
  public void setConsistency(String consistency) {
    this.config = config.withValue(DATABASE_CONSISTENCY,
            ConfigValueFactory.fromAnyRef(
                    Consistency.parse(Assert.isNotNull(consistency, "consistency")).toString()));
  }

  /**
   * Sets the storage read consistency.
   *
   * @param consistency The storage read consistency.
   * @throws java.lang.NullPointerException If the consistency is {@code null}
   */
  public void setConsistency(Consistency consistency) {
    this.config = config.withValue(DATABASE_CONSISTENCY,
            ConfigValueFactory.fromAnyRef(
                    Assert.isNotNull(consistency, "consistency").toString()));
  }

  /**
   * Returns the storage read consistency.
   *
   * @return The storage read consistency.
   */
  public Consistency getConsistency() {
    return Consistency.parse(config.getString(DATABASE_CONSISTENCY));
  }

  /**
   * Sets the storage read consistency, returning the configuration for method chaining.
   *
   * @param consistency The storage read consistency.
   * @return The storage configuration.
   * @throws java.lang.NullPointerException If the consistency is {@code null}
   */
  public DatabaseConfig withConsistency(String consistency) {
    setConsistency(consistency);
    return this;
  }

  /**
   * Sets the storage read consistency, returning the configuration for method chaining.
   *
   * @param consistency The storage read consistency.
   * @return The storage configuration.
   * @throws java.lang.NullPointerException If the consistency is {@code null}
   */
  public DatabaseConfig withConsistency(Consistency consistency) {
    setConsistency(consistency);
    return this;
  }

  /**
   * Returns the storage name.
   *
   * @return The storage name
   */
  public String getName() {
      return name;
  }

  /**
   * Sets the storage name, returning the configuration for method chaining.
   *
   * @param name The storage name
   * @return The storage configuration
   * @throws java.lang.NullPointerException If the name is {@code null}
   */
  public DatabaseConfig withName(String name) {
      setName(Assert.isNotNull(name, "name"));
      return this;
  }

  /**
   * Sets the storage name.
   *
   * @param name The storage name
   * @throws java.lang.NullPointerException If the name is {@code null}
   */
  public void setName(String name) {
      this.name = Assert.isNotNull(name, "name");
  }

  @Override
  public CoordinatedResourceConfig resolve(ClusterConfig cluster) {
    return new StateLogConfig(toMap())
      .resolve(cluster)
      .withResourceType(DefaultDatabase.class);
  }

}
