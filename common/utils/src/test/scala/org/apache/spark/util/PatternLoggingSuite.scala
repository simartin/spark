/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.util

import org.scalatest.BeforeAndAfterAll

import org.apache.spark.internal.Logging

class PatternLoggingSuite extends LoggingSuiteBase with BeforeAndAfterAll {

  override protected def logFilePath: String = "target/pattern.log"

  override def beforeAll(): Unit = Logging.disableStructuredLogging()

  override def afterAll(): Unit = Logging.enableStructuredLogging()

  override def expectedPatternForBasicMsg(level: String): String =
    s""".*$level PatternLoggingSuite: This is a log message\n"""

  override def expectedPatternForMsgWithMDC(level: String): String =
    s""".*$level PatternLoggingSuite: Lost executor 1.\n"""

  override def expectedPatternForMsgWithMDCAndException(level: String): String =
    s""".*$level PatternLoggingSuite: Error in executor 1.\njava.lang.RuntimeException: OOM\n.*"""
}
