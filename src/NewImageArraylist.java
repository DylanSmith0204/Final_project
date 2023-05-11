import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;

public class NewImageArraylist {

    public Arraylist<> ArrayListMaker(String[] args) {

        // create an ArrayList to hol images
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

        // loop through and upload files
        for (int i = 1; i <= 10; i++) {
            try {
                // load the image file into a BufferedImage object
                BufferedImage image = ImageIO.read(new File("image" + i + ".jpg"));

                // add the BufferedImage object to the ArrayList
                images.add(image);

                //not sure what this does but it said I needed a try and cacth online
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}

