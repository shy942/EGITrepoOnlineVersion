/***/
package org.eclipse.e4.core.commands.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Use this annotation to tag a method to call the handler setEnabled method.
* <p>
* This annotation must not be applied to more than one method per class. If several class methods
* are tagged with this annotation, only one of them will be called.
* </p>
*
* @since 1.3
* @noreference
*/
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SetEnabled {
}
