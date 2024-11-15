package user.component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

public class MenuButton extends JButton {
    
    private Icon disabledIcon;
    private Icon selectedIcon;
    
    @Override
    public void setDisabledIcon(Icon disabledIcon) {
        this.disabledIcon = disabledIcon;
    }
    
    @Override
    public Icon getDisabledIcon() {
        return disabledIcon;
    }
    
    @Override
    public void setSelectedIcon(Icon selectedIcon) {
        this.selectedIcon = selectedIcon;
    }
    
    @Override
    public void setSelected(boolean bln) {
        super.setSelected(bln);
        if (bln) {
            setIcon(selectedIcon != null ? selectedIcon : getIcon()); // Show selectedIcon if set
            setBackground(new Color(249, 249, 249));
        } else {
            setIcon(disabledIcon != null ? disabledIcon : getIcon()); // Show disabledIcon if set
            setBackground(new Color(242, 242, 242));
        }
        repaint();
    }

    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (isSelected()) {
            grphcs.setColor(new Color(110, 213, 255));
            grphcs.fillRect(0, getHeight() - 3, getWidth(), getHeight());
        }
    }
    
    public MenuButton() {
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}