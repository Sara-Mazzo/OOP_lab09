package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    /**
     * Initialize the GUI.
     * 
     * @param controller the controller that controls the printing and the strings.
     */
    public SimpleGUI(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        canvas.add(textField, BorderLayout.NORTH);
        canvas.add(textArea, BorderLayout.CENTER);
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BoxLayout(canvas2, BoxLayout.X_AXIS));
        final JButton print = new JButton("Print");
        final JButton showHistory = new JButton("Show History");
        canvas2.add(print);
        canvas2.add(showHistory);
        canvas.add(canvas2, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNextStringToPrint(textField.getText());
                controller.printCurrentString();
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder history = new StringBuilder();
                for (final String string : controller.getPrintedStringHistory()) {
                    history.append(string).append('\n');
                }
                if (!history.isEmpty()) {
                    history.deleteCharAt(history.length() - 1);
                }
                textArea.setText(history.toString());
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Launches the graphical interface.
     * 
     * @param args unused
     */
    public static void main(final String... args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
