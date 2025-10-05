package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class CalculatorApp extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField display;
    private StringBuilder currentInput = new StringBuilder();
    private Stack<String> history = new Stack<>();

    public CalculatorApp() {
        // Use JPanel instead of JFrame
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(true);
        display.setBackground(new Color(230, 230, 230));
        //display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        display.setFocusable(true);
        add(display, BorderLayout.NORTH);

        // Handle keyboard events directly
        display.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();

                // Only process if the key is a valid digit or operation
                if (Character.isDigit(key)) {
                    currentInput.append(key);
                } else if (key == KeyEvent.VK_BACK_SPACE) {
                    // Handle backspace
                    if (currentInput.length() > 0) {
                        currentInput.deleteCharAt(currentInput.length() - 1);
                    }
                } else if (key == KeyEvent.VK_C) {
                    // Handle clear ('C')
                    currentInput.setLength(0);
                } else if (key == KeyEvent.VK_ENTER) {
                    // When Enter is pressed, evaluate the expression
                    buttonPressed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "="));
                } else if (key == '+') {
                    currentInput.append('+');
                } else if (key == '-') {
                    currentInput.append('-');
                } else if (key == '*') {
                    currentInput.append('*');
                } else if (key == '(') {
                    currentInput.append('(');
                }else if (key == ')') {
                    currentInput.append(')');
                }else if (key == '%') {
                    currentInput.append('%');
                }
            }
        });
        
        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(72, 61, 139));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "=", "+", "%",
            "H", "C", "(", ")",
        };
        
        Color[] buttonColors = {
        	    Color.RED,                // 1. Red
        	    Color.GREEN,              // 2. Green
        	    Color.BLUE,               // 3. Blue
        	    Color.ORANGE,             // 4. Orange
        	    Color.RED,                // 1. Red
        	    Color.PINK,               // 6. Pink
        	    Color.CYAN,               // 7. Cyan
        	    Color.MAGENTA,            // 8. Magenta
        	    Color.GRAY,               // 9. Gray
        	    Color.LIGHT_GRAY,         // 10. Light Gray
        	    Color.DARK_GRAY,          // 11. Dark Gray
        	    new Color(255, 105, 180), // 12. Hot Pink
        	    new Color(255, 140, 0),   // 13. Dark Orange
        	    new Color(0, 255, 255),   // 14. Aqua
        	    new Color(123, 104, 238), // 15. Medium Slate Blue
        	    new Color(255, 99, 71),   // 16. Tomato
        	    Color.RED,                // 1. Red
        	    new Color(102, 205, 170), // 18. Medium Aquamarine
        	    new Color(255, 69, 0),    // 19. Red-Orange
        	    new Color(186, 85, 211)   // 20. Medium Orchid
        	};
        int i=0;
        // Adding buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setFocusable(false);
            button.setBackground(new Color(255, 255, 255));
            button.setForeground(buttonColors[i]);
            button.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            button.addActionListener(this::buttonPressed);
            buttonPanel.add(button);
            i++;
        }

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        setFocusable(true);
    }

    private void buttonPressed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("C")) {
            currentInput.setLength(0);
            display.setText("");
        } else if (command.equals("<-")) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                display.setText(currentInput.toString());
            }
        } else if (command.equals("=")) {
            try {
                String result = evaluateExpression(currentInput.toString());
                display.setText(result);
                history.push(currentInput.toString() + " = " + result);
                currentInput.setLength(0);
                currentInput.append(result);
            } catch (Exception ex) {
                display.setText("Error");
                currentInput.setLength(0);
            }
        } else if (command.equals("%")) {
            try {
                // Handle percentage calculation (as a decimal multiplier)
                String expression = currentInput.toString();
                if (!expression.isEmpty() && !expression.contains("%")) {
                    currentInput.append("%");
                    display.setText(currentInput.toString());
                } else if (expression.contains("%")) {
                    double number = Double.parseDouble(expression.substring(0, expression.length() - 1));
                    double result = number / 100.0;
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                    currentInput.append(String.valueOf(result));
                }
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (command.equals("H")) {
            StringBuilder historyText = new StringBuilder();
            for (String entry : history) {
                historyText.append(entry).append("\n");
            }
            JOptionPane.showMessageDialog(this, historyText.toString(), "History", JOptionPane.INFORMATION_MESSAGE);
        } else {
            currentInput.append(command);
            display.setText(currentInput.toString());
        }
    }

    private String evaluateExpression(String expression) {
        try {
        	if (expression.contains("%")) {
                int percentIndex = expression.indexOf("%");
                String numberBeforePercent = expression.substring(percentIndex-1, percentIndex).trim();
                String orgNumber = expression.substring(0, percentIndex-2).trim();
                if (!numberBeforePercent.isEmpty()) {
                    double number = Double.parseDouble(numberBeforePercent);
                    double percentageValue = (number / 100);  
                    double  percentageRes = Double.parseDouble(orgNumber) * percentageValue;
                    expression = expression.replace(numberBeforePercent + "%", String.valueOf(percentageRes));
                }
            }

            double result = evaluateBasicExpression(expression);
            return String.format("%s", result);
        } catch (Exception e) {
            return "Error";
        }
    }

    private double evaluateBasicExpression(String expression) {
        return new Object() {
            int pos = -1, c;

            void nextChar() {
                c = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (c == ' ') nextChar();
                if (c == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) c);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else {
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char) c);
                        nextChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char) c);
                    x = Double.parseDouble(sb.toString());
                }

                return x;
            }
        }.parse();
    }

    // Main method to run the calculator application
   /* public static void main(String[] args) {
        // Create the JFrame to hold the calculator panel
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 250);

        // Create the calculator app and add it to the frame
        CalculatorApp calculator = new CalculatorApp();
        frame.add(calculator);

        // Make the frame visible
        frame.setVisible(true);
    }*/
}
