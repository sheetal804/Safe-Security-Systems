import java.io.*;
//import java.util.Base64;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
public class Try {
public static void main(String args[])throws IOException
{
	Try ob=new Try();
	ob.Try1();
	}
	/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
	imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	byte[] b = baos.toByteArray();
	String encodedString = Base64.encodeToString(b, Base64.URL_SAFE | Base64.NO_WRAP);*/
public String Try1(){
        File f =  new File("C:/Users/sheetal/Desktop/python/image0.jpg");
          String encodstring = encodeFileToBase64Binary(f);
          System.out.println(encodstring);
          return encodstring;
    }

    private static String encodeFileToBase64Binary(File file){
         String encodedfile = null;
         try {
             FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
             fileInputStreamReader.read(bytes);
            encodedfile = Base64.encode(bytes).toString();
             //encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
             //String encodedFile = Base64.getEncoder().encodeToString(bytes);
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         return encodedfile;
     }
}
