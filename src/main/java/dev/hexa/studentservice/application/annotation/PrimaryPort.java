package dev.hexa.studentservice.application.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

/**
 * In hexagonal architecture, a primary port lets the application core to expose features.
 * A primary port is used by primary adapters.
 *
 * @see <a href="https://alistair.cockburn.us/hexagonal-architecture/">Hexagonal architecture</a>
 */
@Inherited
@Documented
public @interface PrimaryPort {
}
