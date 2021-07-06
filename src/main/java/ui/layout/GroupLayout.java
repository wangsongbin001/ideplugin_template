package ui.layout;

import java.awt.*;
import java.io.Serializable;

public class GroupLayout implements LayoutManager, Serializable {
    int vgap = 0;
    int hgap = 0;

    public GroupLayout() {
    }

    public GroupLayout(int hg, int vg) {
        this.hgap = hg;
        this.vgap = vg;
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int w = 0;
            int h = 0;

            for (int i = 0; i < ncomponents; ++i) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                if (w < d.width) {
                    w = d.width;
                }

                h += d.height + this.vgap;
            }

            return new Dimension(insets.left + insets.right + w + 2 * this.hgap,
                    insets.top + insets.bottom + h + 2 * this.vgap);
        }
    }

    public Dimension minimumLayoutSize(Container parent) {
        return this.preferredLayoutSize(parent);
    }

    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            if (ncomponents != 0) {
                int y = insets.top + this.vgap;

                for (int c = 0; c < ncomponents; ++c) {
                    int h = parent.getComponent(c).getPreferredSize().height;
                    parent.getComponent(c)
                            .setBounds(insets.left + this.hgap, y,
                                    parent.getWidth() - insets.left - insets.right - 2 * this.hgap, h);
                    y += h + this.vgap;
                }
            }
        }
    }

    public String toString() {
        return this.getClass().getName();
    }
}