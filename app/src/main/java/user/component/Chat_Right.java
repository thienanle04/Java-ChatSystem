package user.component;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import user.event.PublicEvent;
import user.model.Model_Chat_Message;
import user.model.Model_Delete_Message;
import user.service.Service;

public class Chat_Right extends javax.swing.JLayeredPane {
    private Model_Chat_Message message;

    public Chat_Right(Model_Chat_Message message) {
        initComponents();
        this.message = message;
        setText(message.getMessage());
        setTime(message.getTime());
        txt.setBackground(new Color(179, 233, 255));
        init();
    }

    private void init() {
        txt.addMouseListenerToTxt(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.isPopupTrigger()) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem deleteItem = new JMenuItem("Delete message for me");

                    if (message.getTime().isBefore(LocalDateTime.now().minusDays(1))) {

                    } else {
                        JMenuItem deleteForEveryoneItem = new JMenuItem("Delete message for everyone");
                        deleteForEveryoneItem.addActionListener(_ -> {
                            PublicEvent.getInstance().getEventChat().deleteMessageForEveryone(new Model_Delete_Message(message.getMessageID(), message.getGroupID(), Service.getInstance().getUser().getUserID()));
                        });
                        popupMenu.add(deleteForEveryoneItem);
                        popupMenu.addSeparator();
                    }
        
                    deleteItem.addActionListener(_ -> {
                        PublicEvent.getInstance().getEventChat().deleteMessageForMe(new Model_Delete_Message(message.getMessageID(), message.getGroupID(), Service.getInstance().getUser().getUserID()));
                    });

                    popupMenu.add(deleteItem);
                    popupMenu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });
    }

    public Model_Chat_Message getMessage() {
        return message;
    }

    public String getText() {
        return txt.getText();
    }

    public void setHighLight() {
        txt.setHighLight();
    }

    public void setNormal() {
        txt.setNormal();
    }


    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
        txt.seen();
    }

    public void setTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        txt.setTime(time.format(formatter));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new user.component.Chat_Item();

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.component.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
