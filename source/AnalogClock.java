package source;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalTime;

public class AnalogClock extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int RADIUS = 80; // Adjusted for a better fit within JFrame dimensions

    // Random color for each hand
    private Color hourHandColor = getRandomColor();
    private Color minuteHandColor = getRandomColor();
    private Color secondHandColor = getRandomColor();

    // Background image

    public AnalogClock() {
        // Load the background image (make sure the image is in the right resources folder)
        // Start the timer to update the clock every second
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateClock(); // Update clock hands' colors every second
                repaint(); // Repaint the clock every second
            }
        }, 0, 1000); // Update every second
    }

    // Method to get a random color
    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    // Update the random colors of the clock hands
    public void updateClock() {
        hourHandColor = getRandomColor();
        minuteHandColor = getRandomColor();
        secondHandColor = getRandomColor();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get current time
        LocalTime time = LocalTime.now();
        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        // Get the graphics object
        Graphics2D g2d = (Graphics2D) g;

        // Anti-aliasing for smoother lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get center of the panel for the clock
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw background image inside the clock

        // Draw the clock face (circle)
        g2d.setColor(new Color(72, 61, 139));
        g2d.fillOval(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS);
        g2d.setColor(new Color(72, 61, 139));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS);

        // Draw hour markers (1-12)
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        for (int i = 1; i <= 12; i++) {
            double angle = Math.PI / 6 * (i - 3); // Calculate angle for each hour (every 30 degrees)
            int x = (int) (centerX + (RADIUS - 20) * Math.cos(angle));
            int y = (int) (centerY + (RADIUS - 20) * Math.sin(angle));
            g2d.drawString(Integer.toString(i), x - 10, y + 10);
        }

        // Draw hands with random colors
        drawHand(g2d, hours % 12, 12, RADIUS - 40, centerX, centerY, 8, hourHandColor); // Hour hand
        drawHand(g2d, minutes, 60, RADIUS - 20, centerX, centerY, 6, minuteHandColor); // Minute hand
        drawHand(g2d, seconds, 60, RADIUS - 10, centerX, centerY, 2, secondHandColor); // Second hand
    }

    // Method to draw the clock hands with a specified color
    private void drawHand(Graphics2D g2d, int value, int maxValue, int length, int centerX, int centerY, int width, Color handColor) {
        double angle = Math.PI / 2 - 2 * Math.PI * value / maxValue; // Convert value to angle
        int endX = (int) (centerX + length * Math.cos(angle));
        int endY = (int) (centerY - length * Math.sin(angle));

        g2d.setColor(handColor); // Set the hand color
        g2d.setStroke(new BasicStroke(width));
        g2d.drawLine(centerX, centerY, endX, endY);
    }
}
