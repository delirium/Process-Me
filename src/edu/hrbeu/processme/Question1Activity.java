/**
 * 
 */
package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author delirium
 * 
 */
public class Question1Activity extends Activity {
    private Bitmap firstImage, secondImage;
    static private int IMAGE_SIZE = 200;
    static private int INNER_SQUARE_SIZE = 45;
    static private int IM1_BACKGROUND = Color.rgb(63, 63, 63);
    static private int IM2_BACKGROUND = Color.rgb(203, 203, 203);
    static private int SQ_COLOR = Color.rgb(127, 127, 127);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	setContentView(R.layout.q1);
	LinearLayout ll = (LinearLayout) findViewById(R.id.q1);
	firstImage = Bitmap.createBitmap(IMAGE_SIZE, IMAGE_SIZE,
		Bitmap.Config.ARGB_4444);
	secondImage = Bitmap.createBitmap(IMAGE_SIZE, IMAGE_SIZE,
		Bitmap.Config.ARGB_4444);

	for (int x = 0; x < IMAGE_SIZE; x++) {
	    for (int y = 0; y < IMAGE_SIZE; y++) {
		if ((x > IMAGE_SIZE / 2 - INNER_SQUARE_SIZE)
			&& (x < IMAGE_SIZE / 2 + INNER_SQUARE_SIZE)
			&& (y > IMAGE_SIZE / 2 - INNER_SQUARE_SIZE)
			&& (y < IMAGE_SIZE / 2 + INNER_SQUARE_SIZE)) {
		    firstImage.setPixel(x, y, SQ_COLOR);
		    secondImage.setPixel(x, y, SQ_COLOR);
		} else {
		    firstImage.setPixel(x, y, IM1_BACKGROUND);
		    secondImage.setPixel(x, y, IM2_BACKGROUND);
		}
	    }
	}
	ImageView imageView1 = (ImageView) findViewById(R.id.ImageView01);
	ImageView imageView2 = (ImageView) findViewById(R.id.ImageView02);

	imageView1.setImageBitmap(firstImage);
	imageView2.setImageBitmap(secondImage);
    }

}
