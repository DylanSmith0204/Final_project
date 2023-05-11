import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageUploader implements ActionListener {

    public JFrame jframe;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JButton[] buttons;
    private JFileChooser fileChooser;

    public ImageUploader() {
        //sets jframe name
        jframe = new JFrame("Image Uploader");


        imagePanel = new JPanel();
        imageLabel = new JLabel();
        fileChooser = new JFileChooser();

        // creates 10 new buttons displayed as "button x" that the user can click to select the
        // corresponding image. Adds each button to a panel at the top of the frame.
        buttons = new JButton[10];
        JPanel buttonPanel = new JPanel();
        for (int i = 0; i < 10; i++) {
            buttons[i] = new JButton("Image " + (i+1));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }


        jframe.add(buttonPanel, BorderLayout.NORTH);
        jframe.add(imagePanel, BorderLayout.CENTER);

        jframe.setSize(600, 400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        new ImageUploader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks if the user selects the first button
        if (e.getSource() == buttons[0]) {
            //should display the "file chooser dialog" which is an interface that
            // displays and lets user select uploaded files over the center of the jframe.
            int returnVal = fileChooser.showOpenDialog(jframe);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
                imageLabel.setIcon(imageIcon);
                imagePanel.add(imageLabel);

            }
        }
        // add more if statements for other buttons
    }
}

