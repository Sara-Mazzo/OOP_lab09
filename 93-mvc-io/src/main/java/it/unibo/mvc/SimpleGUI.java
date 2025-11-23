package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    public SimpleGUI(final SimpleController controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JTextField textField = new JTextField();
        JTextArea textArea = new JTextArea();
        JButton print = new JButton("Print");
        JButton showHistory = new JButton("Show History");
        canvas.add(textField);
        canvas.add(textArea);
        canvas.add(print, BorderLayout.SOUTH);
        canvas.add(showHistory, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
   
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    public static void main(final String args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
