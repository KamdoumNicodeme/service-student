package dev.hexa.studentservice.application.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

/**
 * In hexagonal architecture, a secondary adapter is an adapter that is driven by the application.
 * For example, a database or a bucket are different secondary adapters.
 *
 * @see <a href="https://alistair.cockburn.us/hexagonal-architecture/">Hexagonal architecture</a>
 */
@Inherited
@Documented
public @interface SecondaryAdapter {
}
