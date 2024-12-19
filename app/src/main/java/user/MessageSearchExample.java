package user;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MessageSearchExample {
    private DefaultListModel<String> messageListModel; // Stores messages
    private JList<String> messageList;                 // Displays messages
    private JScrollPane scrollPane;                    // Allows scrolling
    private JTextField searchField;                    // User input for search
    private JButton searchButton;                      // Button to perform search
    private JButton nextButton, prevButton;            // Navigation buttons

    private List<Integer> matchedIndices;              // List of matched indices
    private int currentMatchIndex = -1;                // Current match position

    public MessageSearchExample() {
        JFrame frame = new JFrame("Message Finder with Navigation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Initialize message list
        messageListModel = new DefaultListModel<>();
        for (int i = 1; i <= 50; i++) { // Populate sample messages
            messageListModel.addElement("This is Message " + i);
        }
        messageList = new JList<>(messageListModel);
        scrollPane = new JScrollPane(messageList);

        // Search Panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchButton = new JButton("Search");
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        nextButton.setEnabled(false);
        prevButton.setEnabled(false);

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);

        // Add components to the frame
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(navigationPanel, BorderLayout.SOUTH);

        // Event Listeners
        searchButton.addActionListener(e -> searchMessages());
        nextButton.addActionListener(e -> navigateToMatch(1)); // Next match
        prevButton.addActionListener(e -> navigateToMatch(-1)); // Previous match

        frame.setVisible(true);
    }

    private void searchMessages() {
        String searchText = searchField.getText().trim();
        matchedIndices = new ArrayList<>();
        currentMatchIndex = -1;

        // Find all matching indices
        for (int i = 0; i < messageListModel.size(); i++) {
            if (messageListModel.get(i).toLowerCase().contains(searchText.toLowerCase())) {
                matchedIndices.add(i);
            }
        }

        // Handle results
        if (matchedIndices.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages found!");
            nextButton.setEnabled(false);
            prevButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, matchedIndices.size() + " messages found!");
            currentMatchIndex = 0;
            highlightMessage();
            nextButton.setEnabled(matchedIndices.size() > 1);
            prevButton.setEnabled(false);
        }
    }

    private void navigateToMatch(int direction) {
        if (matchedIndices.isEmpty()) return;

        currentMatchIndex += direction;

        // Boundaries for navigation
        if (currentMatchIndex < 0) {
            currentMatchIndex = 0;
        } else if (currentMatchIndex >= matchedIndices.size()) {
            currentMatchIndex = matchedIndices.size() - 1;
        }

        highlightMessage();

        // Enable/disable navigation buttons
        prevButton.setEnabled(currentMatchIndex > 0);
        nextButton.setEnabled(currentMatchIndex < matchedIndices.size() - 1);
    }

    private void highlightMessage() {
        int index = matchedIndices.get(currentMatchIndex);
        messageList.setSelectedIndex(index);
        messageList.ensureIndexIsVisible(index);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MessageSearchExample::new);
    }
}
