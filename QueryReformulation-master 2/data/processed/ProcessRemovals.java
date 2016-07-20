/***/
package org.eclipse.e4.ui.workbench.lifecycle;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Use this annotation to describe methods that will participate in the application lifecycle. This
* method will be called after the {@link ProcessAdditions} calls are done.
*/
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessRemovals {
}
