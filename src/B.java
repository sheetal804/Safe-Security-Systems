import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
class B
{
	public static void main(String args[]) throws IOException
	{final float FACTOR  = 1f;
	BufferedImage img = ImageIO.read(new File("C:/Users/sheetal/Desktop/python/compress.jpg"));
	int scaleX = (int) (img.getWidth() * FACTOR);
	int scaleY = (int) (img.getHeight() * FACTOR);
	Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
	BufferedImage buffered = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_RGB);
	A ob=new A();
	String s=ob.encodeToString(buffered,"jpg");
	BufferedImage b=decodeToImage(s);
	
		
	}
public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}