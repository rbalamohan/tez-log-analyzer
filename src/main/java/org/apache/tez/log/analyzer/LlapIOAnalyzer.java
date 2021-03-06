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

import java.io.IOException;

/**
 * Check if LLAP IO path is getting disabled.
 */
public class LlapIOAnalyzer extends BaseAnalyzer {

  private static final String UNSUPPORTED_COL_TYPE = "Unsupported column type encountered";
  private static final String DISABLE_LLAPIO = "Disabling LLAP IO";

  private StringBuilder sb = new StringBuilder();

  @Override
  public void process(String line) throws IOException {
    if (line.contains(UNSUPPORTED_COL_TYPE) || line.contains(DISABLE_LLAPIO)) {
      sb.append(line).append("\n");
    }
  }

  @Override
  public String getAnalysis() throws IOException {
    return sb.toString();
  }

  @Override
  public String getName() {
    return "LLAP - Check if IO path is getting disabled";
  }

  @Override
  public Object getResult() {
    return sb.toString();
  }
}
