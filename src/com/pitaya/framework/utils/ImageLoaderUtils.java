package com.pitaya.framework.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pitaya.framework.R;

public class ImageLoaderUtils {

	private static DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showStubImage(R.drawable.loading)
	.showImageForEmptyUri(R.drawable.ic_empty)
	.showImageOnFail(R.drawable.ic_error)
	.cacheInMemory()
	.cacheOnDisc()
	.build();
	
	public static void displayImage(String uri, ImageView imageView){
		ImageLoader.getInstance().displayImage(uri, imageView,options);
	}
	
	public static void displayImage(String uri, ImageView imageView,DisplayImageOptions options){
		ImageLoader.getInstance().displayImage(uri, imageView,options);
	}
	
	
}
