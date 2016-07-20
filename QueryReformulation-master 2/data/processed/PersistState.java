/***/
package org.eclipse.e4.ui.di;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Parts can specify this annotation on one of the methods to tag it as the
* method that performs the "visual state persit" operation
* <p>
* This annotation must not be applied to more than one method on a class. If multiple methods of
* the class are tagged with this this annotation, only one of them will be called to persist visual
* state.
* </p>
* @since 1.0
*/
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistState {
}
