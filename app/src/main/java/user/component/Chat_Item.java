package user.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;

import javax.swing.JButton;

public class Chat_Item extends javax.swing.JLayeredPane {

    private JLabel label;

    public Chat_Item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        
//        txt.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent me) {
//                System.out.println("Mouse entered");
//                mouseOverText = true;
//            }
//
//            @Override
//            public void mouseExited(MouseEvent me) {
//                System.out.println("Mouse exited");
//                mouseOverText = false;
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent me) {
//                if (mouseOverText) {
//                    System.out.println("Mouse released");
//                    if (me.getButton() == MouseEvent.BUTTON3) {
//                        // Show popup menu
//                        // Create a popup menu
//                        JPopupMenu popupMenu = new JPopupMenu();
//
//                        // Add menu items to the popup menu
//                        JMenuItem menuItem1 = new JMenuItem("Delete message for me");
//                        
//                        popupMenu.add(menuItem1);
//
//                        // Add action listeners for menu items
//                        menuItem1.addActionListener(e -> {
//                            // PublicEvent.getInstance().getEventChat().reportSpam();
//                        });
//
//                        popupMenu.show(me.getComponent(), me.getX(), me.getY());
//
//                    }
//
//                }
//            }
        // });
    }

    public void addMouseListenerToTxt(MouseAdapter adapter) {
        txt.addMouseListener(adapter);
        label.addMouseListener(adapter);
        addMouseListener(adapter);
    }

    public void setUserProfile(String user) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton cmd = new JButton(user);
        cmd.setHorizontalAlignment(SwingConstants.LEFT);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30, 121, 213));
        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        layer.add(cmd);
        add(layer, 0);
    }

    public void setHighLight() {
        txt.highlightAllText(new Color(255, 255, 0, 100));
    }

    public void setNormal() {
        txt.undoHighlights();
    }

    public void setText(String text) {
        txt.setText(text);
    }

    public String getText() {
        return txt.getText();
    }

    public void setTime(String time) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(time);
        label.setFont(new java.awt.Font(label.getFont().getName(), 0, 10));
        label.setForeground(new Color(110, 110, 110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }

    public void sendSuccess() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/user/tick.png")));
        }
    }

    public void seen() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/user/double_tick.png")));
        }
    }

    public void hideText() {
        txt.setVisible(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new user.swing.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txt.setSelectionColor(new java.awt.Color(92, 188, 255));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        if (getBackground() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
