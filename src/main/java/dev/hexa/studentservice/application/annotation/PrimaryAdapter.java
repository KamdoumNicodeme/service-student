package dev.hexa.studentservice.application.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

/**
 * In hexagonal architecture, a primary adapter is an adapter that drives the application.
 * For example, a controller or a Kafka listener are different primary adapters.
 *
 * @see <a href="https://alistair.cockburn.us/hexagonal-architecture/">Hexagonal architecture</a>
 */
@Inherited
@Documented
public @interface PrimaryAdapter {
}
