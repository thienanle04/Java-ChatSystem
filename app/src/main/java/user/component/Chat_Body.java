package user.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import user.model.Model_Chat_Message;

public class Chat_Body extends javax.swing.JPanel {
    private List<JComponent> matchedMessages;

    public Chat_Body() {
        initComponents();
        init();
        matchedMessages = new ArrayList<>();
    }

    public void cancelSearch() {
        for (JComponent comp : matchedMessages) {
            if (comp instanceof Chat_Left_With_Profile) {
                ((Chat_Left_With_Profile) comp).setNormal();
            } else if (comp instanceof Chat_Right) {
                ((Chat_Right) comp).setNormal();
            }
        }
        matchedMessages.clear();
    }

    // Search for messages containing the search text
    public int searchMessages(String searchText) {
        cancelSearch();
        matchedMessages.clear(); // Clear previous matches

        // Iterate over all components in the chat body
        for (int i = 0; i < body.getComponentCount(); i++) {
            JComponent comp = (JComponent) body.getComponent(i);

            // Check if the component is a chat message
            if (comp instanceof Chat_Left_With_Profile || comp instanceof Chat_Right) {
                String messageText = null;
                if (comp instanceof Chat_Left_With_Profile) {
                    messageText = ((Chat_Left_With_Profile) comp).getText();
                } else if (comp instanceof Chat_Right) {
                    messageText = ((Chat_Right) comp).getText();
                }

                // If the message contains the search text, add it to the matched list
                if (messageText != null && messageText.toLowerCase().contains(searchText.toLowerCase())) {
                    matchedMessages.add(comp);
                }
            }
        }

        // Highlight the first match if found
        if (!matchedMessages.isEmpty()) {
            for (int i = 0; i < matchedMessages.size(); i++) {
                JComponent comp = matchedMessages.get(i);
                if (comp instanceof Chat_Left_With_Profile) {
                    ((Chat_Left_With_Profile) comp).setHighLight();
                } else if (comp instanceof Chat_Right) {
                    ((Chat_Right) comp).setHighLight();
                }
            }

            return matchedMessages.size();
        } else {
            return 0;
        }
    }

    // Navigate to the next or previous match
    public void navigateMatches(int matchIndex) {
        if (matchIndex >= 0 && matchIndex < matchedMessages.size()) {
            JComponent comp = matchedMessages.get(matchIndex);
            SwingUtilities.invokeLater(() -> {
                sp.getVerticalScrollBar().setValue(comp.getY() - 50);
            });
        }

    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[bottom]5"));
        sp.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "width:5;"
                + "background:null;"
                + "trackInsets:5,0,5,0;"
                + "thumbInsets:5,0,5,0;");
        sp.getVerticalScrollBar().setUnitIncrement(10);
    }

    public int navigateToMessage(Model_Chat_Message message) {
        // loop through matchedMessages to find the messags to match index
        for (int i = 0; i < matchedMessages.size(); i++) {
            JComponent comp = matchedMessages.get(i);
            if (comp instanceof Chat_Left_With_Profile) {
                if (((Chat_Left_With_Profile) comp).getMessage().getMessageID() == message.getMessageID()) {
                    return i;
                }
            } else if (comp instanceof Chat_Right) {
                if (((Chat_Right) comp).getMessage().getMessageID() == message.getMessageID()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void removeMessage(int ID) {
        for (int i = 0; i < body.getComponentCount(); i++) {
            JComponent comp = (JComponent) body.getComponent(i);
            if (comp instanceof Chat_Left_With_Profile) {
                if (((Chat_Left_With_Profile) comp).getMessage().getMessageID() == ID) {
                    body.remove(i);
                    break;
                }
            } else if (comp instanceof Chat_Right) {
                if (((Chat_Right) comp).getMessage().getMessageID() == ID) {
                    body.remove(i);
                    break;
                }
            }
        }
        repaint();
        revalidate();
    }

    public void deleteMessage(int ID) {
        for (int i = 0; i < body.getComponentCount(); i++) {
            JComponent comp = (JComponent) body.getComponent(i);
            if (comp instanceof Chat_Left_With_Profile) {
                if (((Chat_Left_With_Profile) comp).getMessage().getMessageID() == ID) {
                    ((Chat_Left_With_Profile) comp).setText("This message has been deleted");
                    break;
                }
            } else if (comp instanceof Chat_Right) {
                if (((Chat_Right) comp).getMessage().getMessageID() == ID) {
                    ((Chat_Right) comp).setText("This message has been deleted");
                    break;
                }
            }
        }
        repaint();
        revalidate();
    }

    public void addItemLeft(Model_Chat_Message data) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile(data);
        body.add(item, "wrap, w 100::60%");
        repaint();
        revalidate();
    }

    public void addItemRight(Model_Chat_Message data) {
        Chat_Right item = new Chat_Right(data);
        body.add(item, "wrap, al right, w 100::60%");
        repaint();
        revalidate();
        scrollToBottom();
    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

}
