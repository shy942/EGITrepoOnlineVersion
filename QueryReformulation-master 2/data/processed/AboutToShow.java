/***/
package org.eclipse.e4.ui.di;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Use this annotation to provide MMenuElements to the list of dynamically shown entries
* within a DynamicMenuContributionItem. Usage in contribution class:
* <p>
* {@literal @}AboutToShow<br>
* public void aboutToShow(List&lt;MMenuElement&gt; items) { }
*
* @see org.eclipse.jface.action.IMenuListener
* @since 1.0
*/
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AboutToShow {
}
