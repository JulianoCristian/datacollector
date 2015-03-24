/*
 * (c) 2015 StreamSets, Inc. All rights reserved. May not
 * be copied, modified, or distributed in whole or part without
 * written consent of StreamSets, Inc.
 */

package com.streamsets.pipeline.stage.origin.http;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum Groups implements Label {
  HTTP("HTTP"),
  OAUTH("OAuth"),
  JSON("JSON")
  ;

  private final String label;

  private Groups(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }
}
