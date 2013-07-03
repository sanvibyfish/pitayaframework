package com.pitaya.framework.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;

public class ImageUtils {
	
	public static byte[] bitmap2Bytes(Bitmap bm){  
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);    
	    return baos.toByteArray();  
	   }  
}
