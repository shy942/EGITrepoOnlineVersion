/***/
package org.eclipse.e4.ui.internal.css.swt;

import org.eclipse.swt.graphics.Color;

public interface ICTabRendering {

    void setSelectedTabFill(Color color);

    void setSelectedTabFill(Color[] colors, int[] percents);

    void setUnselectedTabsColor(Color color);

    void setUnselectedTabsColor(Color[] colors, int[] percents);

    void setUnselectedHotTabsColorBackground(Color color);

    void setTabOutline(Color color);

    void setInnerKeyline(Color color);

    void setOuterKeyline(Color color);

    void setShadowColor(Color color);

    void setCornerRadius(int radius);

    void setShadowVisible(boolean visible);
}
