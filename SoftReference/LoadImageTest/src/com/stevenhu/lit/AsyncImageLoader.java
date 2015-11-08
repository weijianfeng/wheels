package com.stevenhu.lit;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

//实现图片异步加载的类
public class AsyncImageLoader 
{
	//以Url为键，SoftReference为值，建立缓存HashMap键值对。
	private Map<String, SoftReference<Drawable>> mImageCache = 
		new HashMap<String, SoftReference<Drawable>>();
	
	//实现图片异步加载
	public Drawable loadDrawable(final String imageUrl, final ImageCallback callback)
	{
		//查询缓存，查看当前需要下载的图片是否在缓存中
		if(mImageCache.containsKey(imageUrl))
		{
			SoftReference<Drawable> softReference = mImageCache.get(imageUrl);
			if (softReference.get() != null)
			{
				return softReference.get();
			}
		}
		
		final Handler handler = new Handler()
		{
			@Override
			public void dispatchMessage(Message msg) 
			{
				//回调ImageCallbackImpl中的imageLoad方法，在主线(UI线程)中执行。
				callback.imageLoad((Drawable)msg.obj);
			}
		};
		
		/*若缓存中没有，新开辟一个线程，用于进行从网络上下载图片，
		 * 然后将获取到的Drawable发送到Handler中处理，通过回调实现在UI线程中显示获取的图片
		 */
		new Thread()
		{		
			public void run() 
			{
				Drawable drawable = loadImageFromUrl(imageUrl);
				//将得到的图片存放到缓存中
				mImageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			};
		}.start();
		
		//若缓存中不存在，将从网上下载显示完成后，此处返回null；
		return null;
		
	}
	
	//定义一个回调接口
	public interface ImageCallback
	{
		void imageLoad(Drawable drawable);
	}
	
	//通过Url从网上获取图片Drawable对象；
	protected Drawable loadImageFromUrl(String imageUrl)
	{
		try {
			return Drawable.createFromStream(new URL(imageUrl).openStream(),"debug");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

