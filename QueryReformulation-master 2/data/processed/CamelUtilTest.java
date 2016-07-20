/***/
package org.eclipse.ui.tests.quickaccess;

import junit.framework.TestCase;
import org.eclipse.ui.internal.quickaccess.CamelUtil;

public class CamelUtilTest extends TestCase {

    public void testIsIgnoredForCamelCase() {
        assertEquals(true, CamelUtil.isSeparatorForCamelCase(' '));
        assertEquals(true, CamelUtil.isSeparatorForCamelCase('.'));
        assertEquals(true, CamelUtil.isSeparatorForCamelCase('-'));
        assertEquals(true, CamelUtil.isSeparatorForCamelCase('/'));
        assertEquals(true, CamelUtil.isSeparatorForCamelCase('*'));
        assertEquals(false, CamelUtil.isSeparatorForCamelCase('a'));
        assertEquals(false, CamelUtil.isSeparatorForCamelCase('A'));
        assertEquals(false, CamelUtil.isSeparatorForCamelCase('1'));
    }

    public void testGetCamelCase() {
        assertEquals("", CamelUtil.getCamelCase(""));
        assertEquals("a", CamelUtil.getCamelCase("a"));
        assertEquals("ab", CamelUtil.getCamelCase("a b"));
        assertEquals("at", CamelUtil.getCamelCase("any thing"));
        assertEquals("cc", CamelUtil.getCamelCase("CamelCase"));
        assertEquals("csm", CamelUtil.getCamelCase("call Some Method"));
        assertEquals("sjree", CamelUtil.getCamelCase("SomeJREExample"));
        assertEquals("sjree", CamelUtil.getCamelCase("SomeJRE - Example"));
    }

    public void testGetNextCamelIndex() {
        assertEquals(-1, CamelUtil.getNextCamelIndex("", 0));
        assertEquals(1, CamelUtil.getNextCamelIndex("aB", 0));
        assertEquals(3, CamelUtil.getNextCamelIndex("ab c", 0));
        assertEquals(2, CamelUtil.getNextCamelIndex("a b ", 0));
        assertEquals(2, CamelUtil.getNextCamelIndex("a b ", 1));
    }

    public void testGetCamelCaseIndices() {
        assertArrayEquals(new int[][] {}, CamelUtil.getCamelCaseIndices("some string", 0, 0));
        assertArrayEquals(new int[][] { { 0, 0 } }, CamelUtil.getCamelCaseIndices("some string", 0, 1));
        assertArrayEquals(new int[][] { { 0, 0 }, { 5, 5 } }, CamelUtil.getCamelCaseIndices("some string", 0, 2));
        assertArrayEquals(new int[][] { { 5, 5 } }, CamelUtil.getCamelCaseIndices("some string", 1, 1));
        assertArrayEquals(new int[][] { { 8, 8 }, { 12, 12 }, { 19, 19 }, { 26, 26 }, { 31, 31 } }, CamelUtil.getCamelCaseIndices("Editors ApplAction.java - mail/src", 1, 5));
    }

    /**
* @param is
* @param camelCaseIndices
*/
    private void assertArrayEquals(int[][] is, int[][] camelCaseIndices) {
        assertEquals(is.length, camelCaseIndices.length);
        for (int i = 0; i < is.length; i++) {
            int[] js = is[i];
            assertEquals("i=" + i, js.length, camelCaseIndices[i].length);
            for (int j = 0; j < js.length; j++) {
                assertEquals("i=" + i + ", j=" + j, js[j], camelCaseIndices[i][j]);
            }
        }
    }
}
