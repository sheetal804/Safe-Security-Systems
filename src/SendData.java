//package com.oksbwn.server_activity;

import java.io.*;

//import logging.LogMessage;

//import com.oksbwn.serial_communication.getData;
//import com.oksbwn.temp_trip_data.AccessTripData;

public class SendData {

getData	gd= new getData();
String command;
String response;
//static String api="http://safesystem.esy.es/dbconfig.php/?l1=10&l2=106";
private boolean DEBUG_ENABLED=true;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//PiCam ob1=new PiCam();
		SendData ob=new SendData();
		
		new SetupGPRS().SetUpGPRS();
		new SetupGPRS().sendSAP();
		System.out.println(ob.initHTTP());
		// SendImage ob2=new SendImage();
		 //ob.sendImg();
		System.out.println(ob.sendHTTP());
		
		//PiCam ob1=new PiCam();

	}
	public void getDetail() throws Exception
	{
		command="AT+CGNSPWR=1";
		response= gd.getFromMeter(command.toCharArray(),true);
		command="AT+CGNSSEQ=RMC";
		response= gd.getFromMeter(command.toCharArray(),true);
		command="AT+CGNSINF";
		response= gd.getFromMeter(command.toCharArray(),true);
		String words[]=response.split(",");
		for(String rr:words)
		System.out.println(rr);
	}
public boolean initHTTP() throws Exception{
	command="AT+HTTPTERM";
	response= gd.getFromMeter(command.toCharArray(),true);
	System.out.println(response);
	 command="AT+HTTPINIT";
	 int i=0;
	 do{
		 response= gd.getFromMeter(command.toCharArray(),true);
		 i++;

		 if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);
	 }while(!response.toLowerCase().contains("ok")&& i<10);
	 
	 if(i==10)
		 return false;
	 
	 command="AT+HTTPPARA=\"CID\",1";
	 i=0;
	 do{
		 response= gd.getFromMeter(command.toCharArray(),true);
		 
		 if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);
		 
		 i++;
	 }while(!response.toLowerCase().contains("ok") && i<10);
	 if(i==10)
		 return false;
	return true;
	 
}


public void sendImg() throws InterruptedException{
	getData	gd= new getData();
	Try ob3=new Try();
	String s=ob3.Try1();
	//System.out.println(s);
	String api1="safesystem.esy.es/abcd.php/";
	//String command,response;
	command="AT+HTTPPARA=\"URL\",\"http://"+api1+"?s="+s+"\"";
	 response= gd.getFromMeter(command.toCharArray(),true);
	 System.out.println(api1);
			System.out.println("Reply HTTP : "+response);
			System.out.println(api1);
	 	command="AT+HTTPACTION=0";
	 response= gd.getFromMeter(command.toCharArray(),true);
			System.out.println("Reply HTTP : "+response);
 if(response.toLowerCase().contains("+httpaction:0,10")){
	 Thread.sleep(1000);
	 command="AT+HTTPREAD";
	 response= gd.getFromMeter(command.toCharArray(),true);
		System.out.println("Reply HTTP Server: "+response);
	 System.out.println(response);
	// return response;
	 
 }
 //else
	// return "0";
 
}


 public String sendHTTP() throws InterruptedException{
	 int i=0;
	 command="AT+CGNSPWR=1";
		response= gd.getFromMeter(command.toCharArray(),true);
		command="AT+CGNSSEQ=RMC";
		response= gd.getFromMeter(command.toCharArray(),true);
		do{
			if(i!=2){
			command="AT+CGNSINF";
			response= gd.getFromMeter(command.toCharArray(),true);
			String words[]=response.split(",");
		
			System.out.println("values are");
			for(String rr:words)
				System.out.println(rr);
			String api="safesystemz.esy.es/dbconfig.php/";
		//command="AT+HTTPPARA=\"URL\",\"http://"+api+"?"+parameters.substring(1)+"\"";
			float w1=Float.parseFloat(words[3]);
			float w2=Float.parseFloat(words[4]);
			System.out.println("lat and long "+w1+" "+w2);
			command="AT+HTTPPARA=\"URL\",\"http://"+api+"?l1="+w1+"&l2="+w2+"\"";
			response= gd.getFromMeter(command.toCharArray(),true);

		 if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);

	 command="AT+HTTPACTION=0";
		 response= gd.getFromMeter(command.toCharArray(),true);

		 if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);
			
			
			}
		else{
			System.out.println("df");
			String api1="safesystemz.esy.es/file/index.php";
			command="AT+HTTPPARA=\"URL\",\"http://"+api1+"\"";
			response= gd.getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply HTTP : "+response);
			command="AT+HTTPACTION=0";
			 response= gd.getFromMeter(command.toCharArray(),true);
			 if(DEBUG_ENABLED)
					System.out.println("Reply HTTP : "+response);
		}
		 i++;
	 }while(!response.toLowerCase().contains("+httpaction:0,10") && i<10);
	 if(i==10){
		 	Runtime r= Runtime.getRuntime();
			try {	
				//new AccessTripData().saveTripID(tripID);
				//new LogMessage(1,"Main : Rebooting The PI Due to unavaibility of Internet",null);
				r.exec("sudo reboot");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				//new LogMessage(0, "Main : Rebooting The PI Due to unavaibility of Internet.",e1);
				}
			}
	 if(response.toLowerCase().contains("+httpaction:0,10")){
		 Thread.sleep(1000);
		 command="AT+HTTPREAD";
		 response= gd.getFromMeter(command.toCharArray(),true);
		 if(DEBUG_ENABLED)
			System.out.println("Reply HTTP Server: "+response);
		// System.out.println(response);
		 return response;
		 
	 }
	 else
		 return "0";
	 
	 
 }
}
