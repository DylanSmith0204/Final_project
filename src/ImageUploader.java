
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ImageUploader implements ActionListener {

    public JFrame jframe;
    private JLabel imageLabel;
    private JButton[] buttons;
    private JPanel imagePanel;

    private static int initialX;
    private static int initialY;



    public ImageUploader() throws IOException {
        //sets jframe name
        jframe = new JFrame("Image Uploader");


        imagePanel = new JPanel();//holds imageLabel and text box function
        imageLabel = new JLabel();//holds selected meme


        // creates 10 new buttons displayed as "button x" that the user can click to select the
        // corresponding image. Adds each button to a panel at the top of the frame.
        buttons = new JButton[10];
        JPanel buttonPanel = new JPanel();
        JLabel text1 = new JLabel("Choose an image:");
        buttonPanel.add(text1);
        //creates a new arraylist of imageIcons which are image files
        for (int i = 1; i <= 10; i++) {
            //creates button
            buttons[i-1] = new JButton();
            //adds button to panel
            buttonPanel.add(buttons[i-1], BorderLayout.NORTH); //adds buttons to button panel
            ImageIcon img = new ImageIcon("data/image" + i + ".jpg");
            Image image = img.getImage(); //reads image file
            Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scales the image
            img = new ImageIcon(newimg); //creates an image icon of the newly scaled image
            buttons[i-1].setIcon(img); //sets button to the image
            buttons[i-1].setSize(60, 60);  //sets button size
            buttons[i-1].addActionListener(this); //allows user to interact with buttons
        }

        jframe.add(buttonPanel, BorderLayout.NORTH); //adds buttons at the top of frame

        jframe.setSize(930, 500); //sets size of frame
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        JButton TextBoxButton = new JButton("Add Text Box");
        imagePanel.add(TextBoxButton, BorderLayout.WEST); //adds text box button to left side of image

        JButton deleteButton = new JButton("Delete Textbox");
        deleteButton.setBounds(180, 50, 80, 30);
        imagePanel.add(deleteButton);


        //adds action listner to text box button and calls createtextbox() in imagePanel
        TextBoxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               createTextBox(imageLabel, deleteButton);
            }
        });


    }

// creates a unique textbox when button is clicked
    private static void createTextBox(JLabel panel, JButton deleteButton) {

        //creates a text box
        JTextField textField = new JTextField();
        panel.add(textField);

        // Prompt the user to enter text
        String inputText = JOptionPane.showInputDialog(panel, "Enter text:");
        textField.setText(inputText);

        // Prompt the user to choose font size
        String fontSizeString = JOptionPane.showInputDialog(panel, "Enter font size:");
        int fontSizeInt = Integer.parseInt(fontSizeString);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, fontSizeInt);
        textField.setFont(font); //http://www.java2s.com/Tutorials/Java/Java_Swing/1520__Java_Swing_Font.htm

        // Calculate preferred size based on the entered text
        FontMetrics fontMetrics = textField.getFontMetrics(textField.getFont());
        int textWidth = fontMetrics.stringWidth(inputText);
        int textHeight = fontMetrics.getHeight();
        textField.setBounds(textWidth, textHeight, textWidth + 10, textHeight); //sets textbox dimensions
        Dimension textSize = new Dimension(textWidth, textHeight);
        textField.setMaximumSize(textSize); // Sets maximum size for the text box

        // Prompt the user to choose font color
        Color fontColor = JColorChooser.showDialog(panel, "Choose font color", Color.BLACK);
        textField.setForeground(fontColor); //http://www.java2s.com/Tutorial/Java/0240__Swing/SetFontandforegroundcolorforaJLabel.htm

        // Add mouse event listeners to enable dragging
        textField.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
            }
        });

        textField.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = textField.getLocation().x + e.getX() - initialX;
                int newY = textField.getLocation().y + e.getY() - initialY;
                textField.setLocation(newX, newY);
            } //https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html
        });

        //add action listened to enable the deleteTextBox function which is called on any created textFields
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTextBox(panel, textField);
            }
        });

        panel.revalidate();
        panel.repaint();
    }

    private static void deleteTextBox(JLabel panel, JTextField textField) {
        // Removes the text field from the panel
        panel.remove(textField);
        panel.repaint();
    }

    //sets imagepanel to selected meme
    public void actionPerformed(ActionEvent e) {
        //checks which image was clicked
            JButton imageClicked = (JButton) e.getSource();
            for (int i = 1; i <= 10; i++) {
                if ((buttons[i-1]).equals(imageClicked)) {
                    ImageIcon img = new ImageIcon("data/image" + i + ".jpg");
                    Image prememe = img.getImage();
                    Image meme = prememe.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
                    //adds the selected image icon to the image panel
                    imageLabel.setIcon((new ImageIcon(meme)));
                    imageLabel.repaint();
                    imagePanel.add(imageLabel);
                    jframe.add(imagePanel, BorderLayout.CENTER); //adds a panel to hold selected meme
                    imagePanel.repaint();

                }
           }
    }


    public static void main(String[] args) throws IOException {
        new ImageUploader();
    }
}





