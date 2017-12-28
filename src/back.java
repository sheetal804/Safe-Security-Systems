 i++;
	 }while(!response.toLowerCase().contains("ok") && i<10);
	 if(i==10)
		 return "0";

	 command="AT+HTTPACTION=0";
	 i=0;
	 do{
		 response= gd.getFromMeter(command.toCharArray(),true);

		 if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);
		 
		 i++;
	 }while(!response.toLowerCase().contains("+httpaction:0,10") && i<10);
public class back {

}
