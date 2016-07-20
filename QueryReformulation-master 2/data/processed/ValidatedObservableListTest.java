/***/
package org.eclipse.core.tests.internal.databinding.observable;

import java.util.ArrayList;
import junit.framework.Test;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.validation.ValidatedObservableList;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.conformance.MutableObservableListContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

public class ValidatedObservableListTest extends AbstractDefaultRealmTestCase {

    public static Test suite() {
        return MutableObservableListContractTest.suite(new Delegate());
    }

    static class Delegate extends AbstractObservableCollectionContractDelegate {

        private Object elementType = new Object();

        @Override
        public IObservableCollection createObservableCollection(Realm realm, int elementCount) {
            IObservableList target = new WritableList(realm, new ArrayList(), elementType);
            for (int i = 0; i < elementCount; i++) target.add(createElement(target));
            IObservableValue validationStatus = new WritableValue(realm, ValidationStatus.ok(), IStatus.class);
            return new ValidatedObservableListStub(target, validationStatus);
        }

        @Override
        public Object createElement(IObservableCollection collection) {
            return new Object();
        }

        @Override
        public Object getElementType(IObservableCollection collection) {
            return elementType;
        }

        @Override
        public void change(IObservable observable) {
            ValidatedObservableListStub validated = (ValidatedObservableListStub) observable;
            validated.target.add(createElement(validated));
        }

        @Override
        public void setStale(IObservable observable, boolean stale) {
            ValidatedObservableListStub validated = (ValidatedObservableListStub) observable;
            if (stale) {
                validated.validationStatus.setValue(ValidationStatus.error("error"));
                validated.target.add(createElement(validated));
            } else {
                validated.validationStatus.setValue(ValidationStatus.ok());
            }
        }
    }

    static class ValidatedObservableListStub extends ValidatedObservableList {

        IObservableList target;

        IObservableValue validationStatus;

         ValidatedObservableListStub(IObservableList target, IObservableValue validationStatus) {
            super(target, validationStatus);
            this.target = target;
            this.validationStatus = validationStatus;
        }
    }
}
