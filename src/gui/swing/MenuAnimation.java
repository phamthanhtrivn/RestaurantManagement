/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author THANHTRI
 */
public class MenuAnimation {

    private final MigLayout layout;
    private final MenuItem menuItem;
    private Animator animator;
    private boolean open;

    public MenuAnimation(MigLayout layout, Component component) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, 200);
    }

    public MenuAnimation(MigLayout layout, Component component, int duration) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, duration);
    }

    private void initAnimator(Component component, int duration) {
        int height = component.getPreferredSize().height;
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float h;
                if (open) {
                    h = 40 + ((height - 40) * fraction);
                    menuItem.setAlpha(fraction);
                } else {
                    h = 40 + ((height - 40) * (1f - fraction));
                    menuItem.setAlpha(1f - fraction);
                }
                layout.setComponentConstraints(menuItem, "h " + h + "!");
                component.revalidate();
                component.repaint();
            }
        };
        animator = new Animator(duration, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }

    public void openMenu() {
        open = true;
        animator.start();
    }

    public void closeMenu() {
        open = false;
        animator.start();
    }
}
