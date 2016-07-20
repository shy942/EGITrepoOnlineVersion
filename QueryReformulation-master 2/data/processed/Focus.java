/***/
package org.eclipse.e4.ui.di;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Parts can specify this annotation on one of its methods to tag it as the
* method to be invoked when it has been granted focus.
* <p>
* This annotation must not be applied to more than one method on a class. If
* multiple methods of the class are tagged with this annotation, only one of
* them will be called to grant focus to the part.
* </p>
*
* @since 1.0
*/
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Focus {
}
