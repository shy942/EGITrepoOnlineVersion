/***/
package org.eclipse.e4.ui.workbench.modeling;

import java.util.Collection;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
* @noreference This interface is not intended to be referenced by clients.
* @since 1.0
*/
public interface ISaveHandler {

    public enum Save implements  {

        YES() {
        }
        , NO() {
        }
        , CANCEL() {
        }
        ;
    }

    public boolean save(MPart dirtyPart, boolean confirm);

    public boolean saveParts(Collection<MPart> dirtyParts, boolean confirm);

    public Save promptToSave(MPart dirtyPart);

    public Save[] promptToSave(Collection<MPart> dirtyParts);
}
