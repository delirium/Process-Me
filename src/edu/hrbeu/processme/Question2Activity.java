/**
 * 
 */
package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author delirium
 * 
 */
public class Question2Activity extends Activity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    private Bitmap mImageBitmapEdited;
    private TextView mProcessingTextView;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	setContentView(R.layout.q2);

	mImageView = (ImageView) findViewById(R.id.q2ImageView);
	mProcessingTextView = (TextView) findViewById(R.id.ProcessingTextView);
	mProcessingTextView.setText("Processing...");
	mImageBitmap = BitmapFactory.decodeResource(getResources(),
		R.drawable.q2);
	mImageBitmapEdited = Bitmap.createBitmap(mImageBitmap.getWidth(),
		mImageBitmap.getHeight(), Bitmap.Config.ARGB_8888);
	for (int x = 0; x < mImageBitmap.getWidth(); x++) {
	    for (int y = 0; y < mImageBitmap.getHeight(); y++) {
		int pixel = mImageBitmap.getPixel(x, y);
		mImageBitmapEdited.setPixel(x, y, pixel);
	    }
	}
	mImageView.setImageBitmap(mImageBitmapEdited);
	// mProcessingTextView.setText("");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onTrackballEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTrackballEvent(MotionEvent event) {
	if (event.getAction() == event.ACTION_MOVE) {
	    float x = event.getX() * event.getXPrecision();
	    float y = event.getY() * event.getYPrecision();
	    mProcessingTextView.setText("Processing...");

	    if (Math.abs(x) > Math.abs(y)) {

		if (x > 0) {
		    increase();
		} else {
		    decrease();
		}
		// mImageView.setImageBitmap(mImageBitmapEdited);
		mImageView.invalidate();
	    }
	    mProcessingTextView.setText("");
	}
	return super.onTrackballEvent(event);
    }

    private void decrease() {
	for (int x = 0; x < mImageBitmapEdited.getWidth(); x++) {
	    for (int y = 0; y < mImageBitmapEdited.getHeight(); y++) {
		int pixel = mImageBitmapEdited.getPixel(x, y);

		int red = Color.red(pixel);

		if (red >= 0 && red < 50)
		    red -= 15;
		else if (red >= 50 && red < 200)
		    red -= 5;
		else if (red >= 200 && red < 255)
		    red -= 1;

		pixel = Color.rgb(red, red, red);
		mImageBitmapEdited.setPixel(x, y, pixel);
	    }
	}
    }

    private void increase() {
	for (int x = 0; x < mImageBitmapEdited.getWidth(); x++) {
	    for (int y = 0; y < mImageBitmapEdited.getHeight(); y++) {
		int pixel = mImageBitmapEdited.getPixel(x, y);

		int red = Color.red(pixel);

		if (red >= 0 && red < 50)
		    red += 15;
		else if (red >= 50 && red < 200)
		    red += 5;
		else if (red >= 200 && red < 255)
		    red += 1;

		pixel = Color.rgb(red, red, red);
		mImageBitmapEdited.setPixel(x, y, pixel);
	    }
	}
    }

}
