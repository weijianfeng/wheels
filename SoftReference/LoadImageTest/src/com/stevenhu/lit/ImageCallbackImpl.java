package com.stevenhu.lit;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.stevenhu.lit.AsyncImageLoader.ImageCallback;

public class ImageCallbackImpl implements ImageCallback
{

	private ImageView mImageView;
	
	public ImageCallbackImpl(ImageView imageView)
	{
		mImageView = imageView;
	}
	
	//在ImageView中显示从网上获取的图片
	@Override
	public void imageLoad(Drawable drawable) 
	{
		// TODO Auto-generated method stub
		mImageView.setImageDrawable(drawable);
	}

}
