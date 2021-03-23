package com.winzer.actualweight;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class WinzerActualWeightApplication
{
	JScrollPane scrollPane;
	JFrame frame;
	JLabel lpnLabel;
	JTextField lpnTxt;
	BufferedReader br;
	private ObjectMapper objectMapper;
	//@Value("${endpoint}")
	String endpoint="https://ta26.wms.ocs.oraclecloud.com/winzerusa_test/wms/lgfapi/v10/";

	//@Value("${wmsuser}")
	String wmsuser="Mani";

	//@Value("${wmspassword}")
	String wmspassword="Welcome!23";


	WinzerActualWeightApplication() 
	{
		frame = new JFrame("Actual Weight Updater");
		scrollPane = new JScrollPane();
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		lpnLabel = new JLabel("Scan OBLPN:");
		lpnLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		lpnLabel.setBounds(200, 100, 300, 50);
		lpnTxt = new JTextField();
		lpnTxt.setFont(new Font("Verdana", Font.PLAIN, 25));
		lpnTxt.setBounds(400, 100, 300, 50);
		lpnTxt.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				JTextField src = (JTextField)e.getSource();
                String lpn = src.getText();
                String weight = null;
                src.setText("");
                try 
                {
					br = new BufferedReader(new FileReader("C:\\Weight\\Weight.txt"));
					String st; 
					
					if((st = br.readLine()) != null) 
					{
						//weight=st;
						weight = st.substring(24, 29); 
						System.out.println(weight);
					} 
				} 
                catch (Exception e1) 
                {
					e1.printStackTrace();
				}
                finally 
                {
                	try {
						br.close();
					} catch (IOException e1) 
                	{
						e1.printStackTrace();
					}
				}
                
                try 
                {
                	if(lpn != null && !"".equals(lpn))
                	{
	        			RestTemplate restTemplate = new RestTemplate();
	        			restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(wmsuser, wmspassword));
	        			ResponseBody responseBody = restTemplate.getForObject(
	        					endpoint + "entity/container?container_nbr="+lpn+"&fields=id&type=O", ResponseBody.class);
	        			LinkedHashMap<String, Object> itemData = (LinkedHashMap<String, Object>)responseBody.getResults().get(0);
	        			
	        			//int itemId = (Integer)itemData.get("id");
	        			int lpnid = (int)itemData.get("id");
	        			System.out.println("lpnid "+lpnid);
	        			
	        			if(lpnid > 0)
	        			{
	        				String body = "{\"fields\":{ \"weight\": \""+weight+"\"}}";
       				         System.out.println(body);
	        				restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(wmsuser, wmspassword));
		        			String response = restTemplate.patchForObject(endpoint + "entity/oblpn/"+lpnid,getPostRequestHeaders(body), String.class);
		        			System.out.println(response);
	        			}
                	}
        		} catch (Exception rest) 
        		{
        			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid LPN "+lpn);
        		}
				
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});

		frame.getContentPane().add(scrollPane);

		frame.getContentPane().add(lpnLabel);
		frame.getContentPane().add(lpnTxt);
		frame.setVisible(true);
	}
	
	public HttpEntity getPostRequestHeaders(String jsonPostBody) 
	{
		List acceptTypes = new ArrayList();
		acceptTypes.add(MediaType.APPLICATION_JSON);

		HttpHeaders reqHeaders = new HttpHeaders();
		reqHeaders.setContentType(MediaType.APPLICATION_JSON);
		reqHeaders.setAccept(acceptTypes);

		return new HttpEntity(jsonPostBody, reqHeaders);
	 }

	public static void main(String[] args) {
		// SpringApplication.run(WinzerActualWeightApplication.class, args);

		new WinzerActualWeightApplication();
	}

}
