/***/
package org.eclipse.ui.tests.performance;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

/**
* This test ContentDescriber added specifically for OpenNavigatorFolderTest.
*
*/
public final class ContentDescriberForTestsOnly implements ITextContentDescriber {

    /*
* this "simulated time" represents how long a content describer might
* take parsing a file. Offhand (i.e. just guessing) 2000 msecs would be "bad case" examples.
* 20 msecs would be near a good, optimistic case.
* I've picked 75 msecs for this test, to represent "normal" case
* (such as the HTML parser that gave rise to the original bug :)
*
* Note: unlike a "real" ContentDescriber, this one only effects "elapsed
* time", not "CPU Time", since all it is doing is sleeping.
*/
    private static final int SIMULATED_CALCULATION_TIME = 75;

    private static final QualifiedName[] SUPPORTED_OPTIONS = { IContentDescription.CHARSET, IContentDescription.BYTE_ORDER_MARK };

    @Override
    public int describe(InputStream contents, IContentDescription description) throws IOException {
        int result = IContentDescriber.INDETERMINATE;
        if (description == null) {
            result = computeValidity(contents);
        } else {
            calculateSupportedOptions(contents, description);
            // assummming we should return same 'validity' value we did
            // when called before. (technically, could be a performance issue
            // in future, so might want to check if any 'ol value would
            // be ok here.
            result = computeValidity(contents);
        }
        return result;
    }

    @Override
    public int describe(Reader contents, IContentDescription description) throws IOException {
        int result = IContentDescriber.INDETERMINATE;
        if (description == null) {
            result = computeValidity(contents);
        } else {
            calculateSupportedOptions(contents, description);
            // assummming we should return same 'validity' value we did
            // when called before. (technically, could be a performance issue
            // in future, so might want to check if hard coded 'valid' would
            // be ok here.
            result = computeValidity(contents);
        }
        return result;
    }

    @Override
    public QualifiedName[] getSupportedOptions() {
        return SUPPORTED_OPTIONS;
    }

    private void calculateSupportedOptions(InputStream contents, IContentDescription description) throws IOException {
        if (isRelevent(description)) {
            makeBusy();
        }
    }

    private void makeBusy() {
        // just take a long time doing nothing.
        try {
            Thread.sleep(SIMULATED_CALCULATION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
* @param contents
* @param description
* @throws IOException
*/
    private void calculateSupportedOptions(Reader contents, IContentDescription description) throws IOException {
        if (isRelevent(description)) {
            makeBusy();
        }
    }

    private int computeValidity(InputStream inputStream) {
        // (this may change once we add XHTML content type)
        return IContentDescriber.INDETERMINATE;
    }

    private int computeValidity(Reader reader) {
        // (this may change once we add XHTML content type)
        return IContentDescriber.INDETERMINATE;
    }

    /**
* @param description
* @return
*/
    private boolean isRelevent(IContentDescription description) {
        boolean result = false;
        if (description == null)
            result = false;
        else if (description.isRequested(IContentDescription.BYTE_ORDER_MARK))
            result = true;
        else if (description.isRequested(IContentDescription.CHARSET))
            result = true;
        return result;
    }
}
