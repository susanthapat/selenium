// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.grid.node.local;

import com.beust.jcommander.Parameter;

import org.openqa.selenium.grid.config.Config;
import org.openqa.selenium.grid.config.ConfigValue;
import org.openqa.selenium.remote.http.HttpClient;

import java.net.URI;

public class NodeFlags {

  @Parameter(names = {"--distributor", "-d"}, description = "Address of the distributor.")
  @ConfigValue(section = "distributor", name = "host")
  private URI distributorServer;

  @Parameter(
      names = "--distributor-port",
      description = "Port on which the distributor is listening.")
  @ConfigValue(section = "distributor", name = "port")
  private int distributorServerPort;

  @Parameter(
      names = "--distributor-host",
      description = "Port on which the distributor is listening.")
  @ConfigValue(section = "distributor", name = "hostname")
  private String distributorServerHost;

  @Parameter(names = {"--sessions", "-s"}, description = "Address of the session map server.")
  @ConfigValue(section = "sessions", name = "host")
  private URI sessionServer;

  @Parameter(
      names = "--sessions-port",
      description = "Port on which the sesion map server is listening.")
  @ConfigValue(section = "sessions", name = "port")
  private int sessionServerPort;

  @Parameter(
      names = "--sessions-host",
      description = "Port on which the sesion map server is listening.")
  @ConfigValue(section = "sessions", name = "hostname")
  private String sessionServerHost;

  @Parameter(
      names = {"--detect-drivers"},
      description = "Autodetect which drivers are available on the current system, and add them to the node.")
  @ConfigValue(section = "node", name = "detect-drivers")
  private boolean autoconfigure;

  public void configure(
      Config config,
      HttpClient.Factory httpClientFactory,
      LocalNode.Builder node) {
    if (!config.getBool("node", "detect-drivers").orElse(false)) {
      return;
    }

    AutoConfigureNode.addSystemDrivers(httpClientFactory, node);
  }
}
