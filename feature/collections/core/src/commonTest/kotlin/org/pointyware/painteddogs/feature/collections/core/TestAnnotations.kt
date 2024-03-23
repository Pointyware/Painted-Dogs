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
annotation class EndToEndTest

/**
 * A test that verifies the appearance of a User Interface.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UiTest

/**
 * A test that verifies the behavior of a User Experience/Journey.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UxTest
