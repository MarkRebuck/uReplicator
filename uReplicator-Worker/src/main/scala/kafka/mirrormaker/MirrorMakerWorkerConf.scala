/**
 * Copyright (C) 2015-2017 Uber Technologies, Inc. (streaming-data@uber.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.mirrormaker

import joptsimple.{ArgumentAcceptingOptionSpec, OptionParser, OptionSpecBuilder}

class MirrorMakerWorkerConf {

  private val parser: OptionParser = new OptionParser
  private var consumerConfigOpt: ArgumentAcceptingOptionSpec[String] = null
  private var producerConfigOpt: ArgumentAcceptingOptionSpec[String] = null
  private var helixConfigOpt: ArgumentAcceptingOptionSpec[String] = null
  private var offsetCommitIntervalMsOpt: ArgumentAcceptingOptionSpec[Integer] = null
  private var abortOnSendFailureOpt: ArgumentAcceptingOptionSpec[String] = null
  private var topicMappingsOpt: ArgumentAcceptingOptionSpec[String] = null
  private var helpOpt: OptionSpecBuilder = null

  def getParser: OptionParser = {
    consumerConfigOpt = parser.accepts("consumer.config",
      "Embedded consumer config for consuming from the source cluster.")
      .withRequiredArg()
      .describedAs("config file")
      .ofType(classOf[String])

    producerConfigOpt = parser.accepts("producer.config",
      "Embedded producer config.")
      .withRequiredArg()
      .describedAs("config file")
      .ofType(classOf[String])

    helixConfigOpt = parser.accepts("helix.config",
      "Embedded helix config.")
      .withRequiredArg()
      .describedAs("config file")
      .ofType(classOf[String])

    offsetCommitIntervalMsOpt = parser.accepts("offset.commit.interval.ms",
      "Offset commit interval in ms")
      .withRequiredArg()
      .describedAs("offset commit interval in millisecond")
      .ofType(classOf[java.lang.Integer])
      .defaultsTo(60000)

    abortOnSendFailureOpt = parser.accepts("abort.on.send.failure",
      "Configure the mirror maker to exit on a failed send.")
      .withRequiredArg()
      .describedAs("Stop the entire mirror maker when a send failure occurs")
      .ofType(classOf[String])
      .defaultsTo("true")

    topicMappingsOpt = parser.accepts("topic.mappings",
      "Path to file containing line deliminated mappings of topics to consume from and produce to.")
      .withRequiredArg()
      .describedAs("Path to mappings file")
      .ofType(classOf[String])

    helpOpt = parser.accepts("help", "Print this message.")

    parser
  }

  def getConsumerConfigOpt: ArgumentAcceptingOptionSpec[String] = {
    consumerConfigOpt
  }

  def getProducerConfigOpt: ArgumentAcceptingOptionSpec[String] = {
    producerConfigOpt
  }

  def getHelixConfigOpt: ArgumentAcceptingOptionSpec[String] = {
    helixConfigOpt
  }

  def getOffsetCommitIntervalMsOpt: ArgumentAcceptingOptionSpec[Integer] = {
    offsetCommitIntervalMsOpt
  }

  def getAbortOnSendFailureOpt: ArgumentAcceptingOptionSpec[String] = {
    abortOnSendFailureOpt
  }

  def getTopicMappingsOpt: ArgumentAcceptingOptionSpec[String] = {
    topicMappingsOpt
  }

  def getHelpOpt: OptionSpecBuilder = {
    helpOpt
  }

}
