/*
 * Copyright 2018 StreamSets Inc.
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

package com.streamsets.pipeline.stage.origin.pulsar;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Dependency;
import com.streamsets.pipeline.lib.pulsar.config.BasePulsarConfig;

import java.util.List;

public class PulsarSourceConfig extends BasePulsarConfig {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Pulsar Subscription Name",
      description = "The name of the Pulsar subscription that will forward messages from the corresponding Pulsar " +
          "topic to subscribed consumers",
      displayPosition = 20,
      defaultValue = "sdc-subscription",
      group = "PULSAR"
  )
  public String subscriptionName;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "Consume from multiple topics",
      description = "Select this option to consume messages from multiple Pulsar topics",
      displayPosition = 50,
      defaultValue = "false",
      group = "PULSAR"
  )
  public boolean multiTopic;

  /* Pulsar is not working correctly when using pattern to create a consumer subscribed to topics matching that pattern.
     Thus this section will remain disabled until Pulsar fixes this issue. topicsList ConfigDef annotation has two
     properties commented which also depend on the issue of topics pattern. When uncommenting this seciton also
     uncomment those lines (79 and 80 currently).

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "Use Pulsar Topic Pattern",
      description = "Select this option to use a pattern to select the list of topics from which message will be " +
          "retrieved",
      displayPosition = 60,
      group = "PULSAR"
  )
  public boolean usePatternForTopic;


  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Pulsar Topics Pattern",
      description = "Pattern used to select the list of Pulsar topics from which messages have to be retrieved. " +
          "Example: persistent://public/default/sdc-.* would match topics like 'sdc-topic' or 'sdc-data'",
      displayPosition = 70,
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      defaultValue = "sdc-.*",
      dependencies = {
          @Dependency(
              configName = "multiTopic",
              triggeredByValues = "true"
          )//,
//          @Dependency(
//              configName = "usePatternForTopic",
//              triggeredByValues = "true"
//          )
      },
      group = "PULSAR"
  )
  public String topicsPattern;
  */

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      label = "Pulsar Topics List",
      description = "List of Pulsar topics from which messages have to be retrieved",
      displayPosition = 70,
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      dependencies = {
          @Dependency(
              configName = "multiTopic",
              triggeredByValues = "true"
          )//,
//          @Dependency(
//              configName = "usePatternForTopic",
//              triggeredByValues = "false"
//          )
      },
      group = "PULSAR"
  )
  public List<String> topicsList;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Pulsar Destination Topic",
      description = "Pulsar topic from which messages have to be retrieved",
      displayPosition = 70,
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      dependencies = {
          @Dependency(
              configName = "multiTopic",
              triggeredByValues = "false"
          )
      },
      group = "PULSAR"
  )
  public String destinationTopic;

}
