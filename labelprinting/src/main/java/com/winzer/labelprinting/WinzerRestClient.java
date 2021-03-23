package com.winzer.labelprinting;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.ZebraPrintException;
import fr.w3blog.zpl.model.element.ZebraBarCode39;
import fr.w3blog.zpl.model.element.ZebraText;
import fr.w3blog.zpl.utils.ZebraUtils;
//controller
@RestController
@RequestMapping("/winzer/label")
public class WinzerRestClient
{
	private static Logger logger = LoggerFactory.getLogger(WinzerRestClient.class);
	private static final String BASE64CONST = "data:image/png;base64,";
	@Value("${endpoint}")
	String endpoint;

	@Value("${wmsuser}")
	String wmsuser;
	
	@Value("${wmspassword}")
	String wmspassword;

	@Value("${printer.ip}")
	String printerIP;

	@Value("${printer.port}")
	int printerPort;

	@Value("${image.thickness}")
	int imageThickness;

	@Value("${label.width}")
	int labelWidth;

	@Value("${label.height}")
	int labelHeight;

	@Autowired
	SFTPConfigProperties sftpConfig;
	
	@GetMapping("/ping")
	public String sayHello()
	{
		return "Hello Winzer! I'm up and running";
	}
	/*
    public void printItemLabel(@RequestParam("itemNumber") String )
    {
    	prinLabel();
    }
    */
	@GetMapping("/print")
	public void printLabel(@RequestParam("code") String code)
	{

		String itemDesc = "";
		String itemBarcode = "";
		String itemImage = "";
		
		if (code == null || "".equals(code))
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Code is required.");
		}

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(wmsuser, wmspassword));
			ResponseBody responseBody = restTemplate.getForObject(
					endpoint + "entity/item?code__in="+code+"&fields=id,description,barcode,image_data", ResponseBody.class);
			LinkedHashMap<String, Object> itemData = (LinkedHashMap<String, Object>)responseBody.getResults().get(0);
			
			//int itemId = (Integer)itemData.get("id");
			itemDesc = (String)itemData.get("description");
			itemBarcode = (String)itemData.get("barcode");
			itemImage = (String)itemData.get("image_data");
			
			if (itemImage == null || "".equals(itemImage)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Code doent have image.");
			}
			
			itemImage = itemImage.substring(22,itemImage.length());
			
		} catch (Exception e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Item Code "+code);
		}

		ZebraLabel zebraLabel = new ZebraLabel(labelWidth, labelHeight);
		zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);
		
		zebraLabel.addElement(new ZebraText(50, 300, code, 12));
		zebraLabel.addElement(new ZebraText(50, 450, itemDesc, 12));
		zebraLabel.addElement(new ZebraBarCode39(50, 150, itemBarcode, 75, 5, 2));
		BufferedImage buffImage = decodeToImage(itemImage);
		zebraLabel.addElement(new ZebraImage(buffImage, 800, 50, imageThickness));
		System.out.println(zebraLabel.getZplCode());
		/*
		try 
		{ 
			ZebraUtils.printZpl(zebraLabel.getZplCode(), printerIP, printerPort); 
		}catch (ZebraPrintException e) 
		{ 
				e.printStackTrace();
		}
		*/
	}

	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			imageByte = Base64.decodeBase64(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
