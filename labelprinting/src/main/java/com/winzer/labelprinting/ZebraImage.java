package com.winzer.labelprinting;


import java.awt.image.BufferedImage;
import java.io.IOException;

import fr.w3blog.zpl.model.PrinterOptions;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.utils.ZplUtils;

/**
 * Zebra element to create a box (or line)
 * 
 * Zpl command : ^GB
 * 
 * @author matthiasvets
 * 
 */
public class ZebraImage extends ZebraElement
{


    private Integer tickness;
    BufferedImage orginalImage;

    public ZebraImage(BufferedImage orginalImage,int positionX, int positionY,Integer tickness)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.orginalImage = orginalImage;
        this.tickness = tickness;
    }


    /* (non-Javadoc)
	 * @see fr.w3blog.zpl.model.element.ZebraElement#getZplCode(fr.w3blog.zpl.model.PrinterOptions)
	 */
	@Override
	public String getZplCode(PrinterOptions printerOptions) {
	    StringBuilder zpl = new StringBuilder();
        //zpl.append(getZplCodePosition());
        //zpl.append("\n");
        try {
			ZPLConveter zp = new ZPLConveter(positionX,positionY);
			zp.setCompressHex(true);
			zp.setBlacknessLimitPercentage(tickness);
			zpl.append((zp.convertfromImg(orginalImage)));
		} catch (IOException e) 
        {
			e.printStackTrace();
		}
        //zpl.append("^FS");
        //zpl.append("\n");
        return zpl.toString();
	}
	
	protected String getZplCodePosition() {
        StringBuffer zpl = new StringBuffer("");
        if (positionX != null && positionY != null) {
            zpl.append(ZplUtils.zplCommand("FO", positionX, positionY));
        }
        return zpl.toString();
    }

}

