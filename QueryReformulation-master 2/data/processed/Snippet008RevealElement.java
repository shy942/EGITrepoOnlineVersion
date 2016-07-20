/***/
package org.eclipse.jface.snippets.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
* Scroll a Viewer 99th element
*
*/
public class Snippet008RevealElement {

    public class MyModel {

        public int counter;

        public  MyModel(int counter) {
            this.counter = counter;
        }

        @Override
        public String toString() {
            return "Item " + this.counter;
        }
    }

    public  Snippet008RevealElement(Shell shell) {
        final TableViewer v = new TableViewer(shell);
        v.setLabelProvider(new LabelProvider());
        v.setContentProvider(ArrayContentProvider.getInstance());
        MyModel[] model = createModel();
        v.setInput(model);
        v.getTable().setLinesVisible(true);
        v.reveal(model[99]);
    }

    private MyModel[] createModel() {
        MyModel[] elements = new MyModel[100];
        for (int i = 0; i < 100; i++) {
            elements[i] = new MyModel(i);
        }
        return elements;
    }

    /**
* @param args
*/
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        new Snippet008RevealElement(shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
