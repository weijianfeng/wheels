package com.stevenhu.lit;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener
{
	//创建异步加载图片类对象
	private AsyncImageLoader mImageLoader = new AsyncImageLoader();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button get = (Button)findViewById(R.id.get_image);
        get.setOnClickListener(this);
    }
    
    private void loadImage(final String url, final int id)
    {
    	ImageView imageView = (ImageView)findViewById(id);
    	ImageCallbackImpl callbackImpl = new ImageCallbackImpl(imageView);
    	Drawable cacheImage = mImageLoader.loadDrawable(url, callbackImpl);
    	//若缓存中存在，直接取出来显示
    	if (cacheImage != null)
    	{
    		imageView.setImageDrawable(cacheImage);
    	}
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.get_image)
		{
			//从网络上获取海贼王的三张图片显示
			loadImage("http://wenwen.soso.com/p/20111003/20111003194816-1615366606.jpg", R.id.one);
	        loadImage("http://t10.baidu.com/it/u=2492256852,4267838923&fm=23&gp=0.jpg", R.id.two);
	        loadImage("http://wenwen.soso.com/p/20100410/20100410102416-1948049438.jpg", R.id.three);
		}
		
	}
	
	
}