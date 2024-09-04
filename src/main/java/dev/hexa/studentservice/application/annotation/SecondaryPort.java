package dev.hexa.studentservice.application.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

/**
 * In hexagonal architecture, a secondary port lets the application core to reach external dependencies.
 * A secondary port is implemented by secondary adapters.
 *
 * @see <a href="https://alistair.cockburn.us/hexagonal-architecture/">Hexagonal architecture</a>
 */
@Inherited
@Documented
public @interface SecondaryPort {
}
