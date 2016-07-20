/***/
package org.eclipse.jface.tests.viewers;

public class TestModelChange {

    private int fKind;

    private TestElement fParent;

    private TestElement[] fChildren;

    public static final int KIND_MASK = 0x0F;

    public static final int INSERT = 1;

    public static final int REMOVE = 2;

    public static final int STRUCTURE_CHANGE = 3;

    public static final int NON_STRUCTURE_CHANGE = 4;

    public static final int REVEAL = 16;

    public static final int SELECT = 32;

    public  TestModelChange(int kind, TestElement parent) {
        this(kind, parent, new TestElement[0]);
    }

    public  TestModelChange(int kind, TestElement parent, TestElement[] children) {
        fKind = kind;
        fParent = parent;
        fChildren = children;
    }

    public  TestModelChange(int kind, TestElement parent, TestElement child) {
        this(kind, parent, new TestElement[] { child });
    }

    public TestElement[] getChildren() {
        return fChildren;
    }

    public int getKind() {
        return fKind & KIND_MASK;
    }

    public int getModifiers() {
        return fKind & ~KIND_MASK;
    }

    public TestElement getParent() {
        return fParent;
    }
}
