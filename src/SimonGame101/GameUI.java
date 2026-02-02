package SimonGame101;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameUI {
    private JFrame frame;
    private JButton red, blue, green, yellow;
    private SimonGame game;
    private JLabel scoreLabel, statusLabel, topScoresLabel;
    private Clip currentClip;

    public GameUI(SimonGame game) {
        this.game = game;
        frame = new JFrame("Simon Game");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel for score and status
        JPanel topPanel = new JPanel(new BorderLayout());
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel = new JLabel("Waiting...");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(scoreLabel, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.CENTER);

        // Center panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        red = createButton(Color.RED, "Red");
        blue = createButton(Color.BLUE, "Blue");
        green = createButton(Color.GREEN, "Green");
        yellow = createButton(Color.YELLOW, "Yellow");
        buttonPanel.add(red);
        buttonPanel.add(blue);
        buttonPanel.add(green);
        buttonPanel.add(yellow);

        // Bottom panel for top scores
        topScoresLabel = new JLabel("<html>Top Scores:<br></html>");
        topScoresLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topScoresLabel.setVerticalAlignment(JLabel.TOP);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(topScoresLabel, BorderLayout.NORTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add panels to frame
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void playSound(String color) {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
            }

            File soundFile = new File("src/sounds/" + color.toLowerCase() + ".wav");
            if (!soundFile.exists()) {
                statusLabel.setText("⚠️ Sound file not found: " + soundFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInput);
            currentClip.start();
        } catch (Exception e) {
            statusLabel.setText("⚠️ Sound error: " + e.getMessage());
        }
    }

    private JButton createButton(Color color, String name) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            playSound(name);
            Color original = button.getBackground();
            button.setBackground(Color.WHITE);
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    button.setBackground(original);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
            game.handlePlayerInput(name);
        });
        return button;
    }

    public void playSequence(ArrayList<String> sequence) {
        statusLabel.setText("Watch the sequence...");
        new Thread(() -> {
            try {
                for (String color : sequence) {
                    JButton btn = getButtonByName(color);
                    Color original = btn.getBackground();
                    btn.setBackground(Color.WHITE);
                    playSound(color);
                    Thread.sleep(500);
                    btn.setBackground(original);
                    Thread.sleep(300);
                }
                statusLabel.setText("Your turn! Repeat the sequence.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private JButton getButtonByName(String name) {
        switch (name) {
            case "Red": return red;
            case "Blue": return blue;
            case "Green": return green;
            case "Yellow": return yellow;
        }
        return null;
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void updateTopScores(String topScores) {
        topScoresLabel.setText("<html>Top Scores:<br>" + topScores.replace("\n", "<br>") + "</html>");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
