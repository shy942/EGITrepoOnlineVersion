/***/
package org.eclipse.jface.tests.viewers;

import org.eclipse.jface.tests.viewers.ViewerComparatorTest.Team;
import org.eclipse.jface.tests.viewers.ViewerComparatorTest.TeamMember;

/**
* @since 3.2
*
*/
public class ComparatorModelChange {

    private int fKind;

    private Team fParent;

    private TeamMember[] fChildren;

    public static final int KIND_MASK = 0x0F;

    public static final int INSERT = 1;

    public static final int REMOVE = 2;

    public static final int STRUCTURE_CHANGE = 3;

    public static final int NON_STRUCTURE_CHANGE = 4;

    public static final int REVEAL = 16;

    public static final int SELECT = 32;

    public  ComparatorModelChange(int kind, Team parent) {
        this(kind, parent, new TeamMember[0]);
    }

    public  ComparatorModelChange(int kind, Team parent, TeamMember[] children) {
        fKind = kind;
        fParent = parent;
        fChildren = children;
    }

    public  ComparatorModelChange(int kind, Team parent, TeamMember child) {
        this(kind, parent, new TeamMember[] { child });
    }

    public TeamMember[] getChildren() {
        return fChildren;
    }

    public int getKind() {
        return fKind & KIND_MASK;
    }

    public int getModifiers() {
        return fKind & ~KIND_MASK;
    }

    public Team getParent() {
        return fParent;
    }
}
