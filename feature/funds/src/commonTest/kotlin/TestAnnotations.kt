/**
 * This file contains annotations that can be used to categorize tests.
 *
 * | Annotation | Description | Test Depth | Dimension |
 * |------------|-------------|------------|-----------|
 * | `UnitTest` | Unit | Behavior | A test that only exercises a single unit of code, satisfying dependencies with mocks. |
 * | `IntegrationTest` | Integration | Behavior | A test that exercises several units of code in combination, satisfying some dependencies with mocks. |
 * | `SystemTest` | System | Behavior | A test that uses all production components (but may use staging or test resources). |
 * | `UiTest` | Unit | Appearance | A test that verifies the appearance of a User Interface. |
 * | `UxTest` | Integration | Appearance | A test that verifies the behavior of a User Experience/Journey. |
 * | `EndToEndTest` | System | Appearance | A test that uses all production components (but may use staging or test resources). |
 *
 * when to use each:
 * |        | Unit | Integration | System |
 * |--------|------|-------------|--------|
 * | Behavior | Unit | Integration | System |
 * | Appearance | Ui | Ux | End-to-End |
 *
 */
package org.pointyware.painteddogs.feature.collections.core


/**
 * A test that only exercises a single unit of code, satisfying dependencies with mocks.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UnitTest

/**
 * A test that exercises several units of code in combination, satisfying some dependencies
 * with mocks.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class IntegrationTest

/**
 * A test that uses all production components (but may use staging or test resources).
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class SystemTest

/**
 * A test that verifies the appearance of a User Interface, using mocks to satisfy dependencies.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UiTest

/**
 * A test that verifies the behavior of a User Experience, satisfying some dependencies with mocks.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UxTest

/**
 * A test that verifies the behavior of a User Journey, using all production components (but may use staging or test resources).
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EndToEndTest
