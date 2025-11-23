package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    /**
     * @param controller the controller that does the file operations
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Simple GUI
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeOnFile(textArea.getText());
                } catch (final IOException exception) {
                    JOptionPane.showMessageDialog(
                        null, exception.getMessage(), "An error has occurred", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        //Add File Chooser
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        final JTextField currentFilePath = new JTextField();
        currentFilePath.setEditable(false);
        currentFilePath.setText(controller.getPath());
        final JButton browse = new JButton("Browse");
        canvas2.add(currentFilePath, BorderLayout.CENTER);
        canvas2.add(browse, BorderLayout.LINE_END);
        canvas.add(canvas2, BorderLayout.NORTH);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getFile());
                final int result = chooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(chooser.getSelectedFile());
                    currentFilePath.setText(controller.getPath());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * sets the sets the size, location and visibility of the GUI.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * launches the application.
     * 
     * @param args unused
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
