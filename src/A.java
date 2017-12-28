import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;
class A
{
	public static void main(String args[]) throws IOException
	{
		final float FACTOR  = 1f;
		BufferedImage img = ImageIO.read(new File("C:/Users/sheetal/Desktop/python/ii.png"));
		int scaleX = (int) (img.getWidth() * FACTOR);
		int scaleY = (int) (img.getHeight() * FACTOR);
		Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
		BufferedImage buffered = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_RGB);
		A ob=new A();
		String s=ob.encodeToString(buffered,"jpg");
		System.out.println(s);
		}


public String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	}