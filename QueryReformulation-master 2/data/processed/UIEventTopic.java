/***/
package org.eclipse.e4.ui.di;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
* This annotation can be applied to arguments and fields that want to receive notifications on the
* specified event topic. Method containing such arguments will be called on the main thread.
* <p>
* Normally EventTopic annotations will be marked as optional. Those annotations establish a link
* rather then provide a value at the time of injection.
* </p>
* <p>
* Example usage:
*
* <pre>
*   public class Car {
*     &#064;void handle(@Optional @UIEventTopic("org/eclipse/e4/some/event/topic") Payload payload);
*     ...
*   }
* </pre>
*
* </p>
* @since 1.0
*/
@Qualifier
@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UIEventTopic {

    // event id
    String value() default "";
}
