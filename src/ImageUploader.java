import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImageUploader implements ActionListener {

        public JFrame jframe;
        private JLabel imageLabel;
        private JButton[] buttons;
        private JFileChooser fileChooser;

        ArrayList<ImageIcon> images = new ArrayList<>();


        public ImageUploader() {
            //sets jframe name
            jframe = new JFrame("Image Uploader");


            JPanel imagePanel = new JPanel();
            imageLabel = new JLabel();
            fileChooser = new JFileChooser();

            // creates 10 new buttons displayed as "button x" that the user can click to select the
            // corresponding image. Adds each button to a panel at the top of the frame.
            buttons = new JButton[10];
            JPanel buttonPanel = new JPanel();
            //creates a new arraylist of imageIcons which are image files
            for (int i = 0; i < 10; i++) {
                buttons[i] = new JButton("Image " + (i+1));
                buttonPanel.add(buttons[i]);
                buttons[i].setIcon(images.get(i));
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
            String image = e.getActionCommand();
            for (int i = 0; i < 10; i++) {
                if (images.get(i).equals(image));
                    imageLabel.setIcon(images.get(i));

                }
            }

        }



