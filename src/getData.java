//package com.oksbwn.serial_communication;

//import logging.LogMessage;

import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Serial;

public class getData{
	final static GpioController gpio = GpioFactory.getInstance();
    int fd = Serial.serialOpen(Serial.DEFAULT_COM_PORT,9600);

    static GpioPinDigitalOutput resetPin= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
    
    
    public static void main (String[] args) throws InterruptedException{
    	
    	String dd=new getData().getFromMeter(new char[]{0x041,0x54,0x0D},false);
    	System.out.println(dd);
    }
public String getFromMeter(char[] commandBuffer, boolean isEneterRequired) throws InterruptedException{
	//System.out.println("fh");
	ArrayList<Character> response = new ArrayList<Character>();
	//new LogMessage(1, "COMMAND: "+String.valueOf(commandBuffer),null);
	 if (fd == -1)
	   	{
		   System.out.println("Sorry");  
		   return "0";
	   	}
	 
	 Thread.sleep(1000);
	 for(int commCounter=0;commCounter<commandBuffer.length;commCounter++)
		{
		 Serial.serialPutchar(fd,commandBuffer[commCounter]);   
		 //System.out.println((int)commandBuffer[commCounter]);
		}
	 if(isEneterRequired)
		 Serial.serialPutchar(fd,(char) 0x0D);
	 
	 Thread.sleep(1000);
	 int dataavail = Serial.serialDataAvail(fd);
	// System.out.println(dataavail);
	 while(dataavail!=0)
		{
	       int data = Serial.serialGetchar(fd);
	       response.add((char) data);
	      
	       dataavail = Serial.serialDataAvail(fd);
	      // System.out.println(data);
		} 

	 //new LogMessage(1, "Response: "+ getStringRepresentation(response),null);
	 return getStringRepresentation(response);
	}
public void close(){
	 Serial.serialClose(fd);
	 gpio.shutdown();
}
String getStringRepresentation(ArrayList<Character> list)
{    
    //StringBuffer builder1 = new StringBuffer(list.size());
	String ff="";
    for(Character ch: list)
    {
    	ff=ff+ch;
     //   builder1.append(ch);
    }
    return ff;
}
public void powerOnModule(){
	try {
		resetPin.high();
		resetPin.low();
		Thread.sleep(2000);
		resetPin.high();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
