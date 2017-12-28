//package com.oksbwn.server_activity;

//import com.oksbwn.serial_communication.getData;

public class SetupGPRS {
	public static void main(String[] args) {
		SetupGPRS i=new SetupGPRS();
		i.SetUpGPRS();

	}
	private boolean GPRS_STATUS=false;
	private boolean DEBUG_ENABLED=true;
	private boolean GPRS_SAP_STATUS;
	String command = "AT+CREG?";
	public void SetUpGPRS(){
		String result;
		try {
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS : "+result);
			if(result.toLowerCase().contains("+creg: 0,1"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
			command ="AT+SAPBR=3,1,\"APN\",\"TATA.DOCOMO.INTERNET\"";
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS : "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;

			command ="AT+SAPBR=3,1,\"USER\",\"\"";
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS : "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
			command="AT+SAPBR=3,1,\"PWD\",\"\"";			
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS : "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
			command ="AT+SAPBR=3,1,\"Contype\",\"GPRS\"";
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS: "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
			//command ="AT+SAPBR=2,1";
			//result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS: "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendSAP(){
		command="AT+SAPBR=1,1";			
		String result;
		try {
			result = new getData().getFromMeter(command.toCharArray(),true);
			
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS : "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_SAP_STATUS= true;
			else 
				GPRS_SAP_STATUS= false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			command ="AT+SAPBR=2,1";
			result = new getData().getFromMeter(command.toCharArray(),true);
			if(DEBUG_ENABLED)
				System.out.println("Reply GPRS: "+result);
			if(result.toLowerCase().contains("ok"))
				GPRS_STATUS= true;
			else 
				GPRS_STATUS= false;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public boolean getStatus(){
		return GPRS_STATUS;
		
	}
	public boolean getSAPStatus(){
		return GPRS_SAP_STATUS;
		
	}
}
