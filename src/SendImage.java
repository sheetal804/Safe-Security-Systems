import java.io.IOException;

public class SendImage
{
	public static void main(String args[])
	{
		
	}
	public static void sendImg() throws InterruptedException{
		getData	gd= new getData();
		String s=Try.Try1();
			//System.out.println(s);
		String api1="safesystem.esy.es/abcd.php/";
		String command,response;
		command="AT+HTTPPARA=\"URL\",\"http://"+api1+"?l1="+s+"\"";
		 response= gd.getFromMeter(command.toCharArray(),true);
		 
				System.out.println("Reply HTTP : "+response);
		
		 	command="AT+HTTPACTION=0";
		 response= gd.getFromMeter(command.toCharArray(),true);
				System.out.println("Reply HTTP : "+response);
	 if(response.toLowerCase().contains("+httpaction:0,10")){
		 Thread.sleep(1000);
		 command="AT+HTTPREAD";
		 response= gd.getFromMeter(command.toCharArray(),true);
			System.out.println("Reply HTTP Server: "+response);
		// System.out.println(response);
		// return response;
		 
	 }
	 //else
		// return "0";
	 
}
 }