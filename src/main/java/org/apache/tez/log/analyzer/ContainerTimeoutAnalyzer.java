/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tez.log.analyzer;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainerTimeoutAnalyzer extends BaseAnalyzer {

  private static final Pattern container =
      Pattern.compile("Container (.*) timed out");
  private StringBuilder sb = new StringBuilder();
  private List<String> containers = Lists.newLinkedList();

  @Override
  public void process(String line) throws IOException {
    Matcher matcher = container.matcher(line);
    while (matcher.find()) {
      sb.append(line).append("\n");
      containers.add(matcher.group(1));
    }
  }

  @Override
  public String getAnalysis() throws IOException {
    return sb.toString();
  }

  @Override
  public String getName() {
    return "Container time outs";
  }

  @Override
  public Object getResult() {
    return Collections.unmodifiableList(containers);
  }
}
