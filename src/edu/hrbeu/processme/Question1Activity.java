/**
 * 
 */
package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author delirium
 * 
 */
public class Question1Activity extends Activity {
    private Bitmap firstImage, secondImage;
    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private TextView mLeftTextView;
    private TextView mRightTextView;
    private TextView mLeftTopTextView;
    private TextView mRightTopTextView;
    private TextView mLeftBottomTextView;
    private TextView mRightBottomTextView;
    static private int IMAGE_SIZE = 200;
    static private int INNER_SQUARE_SIZE = 42;
    static private int IM1_BACKGROUND = Color.rgb(63, 63, 63);
    static private int IM2_BACKGROUND = Color.rgb(203, 203, 203);
    static private int SQ_COLOR = Color.rgb(127, 127, 127);
    private int mChangeableColor = Color.rgb(127, 127, 127);

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	setContentView(R.layout.q1);
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
	mLeftImageView = (ImageView) findViewById(R.id.ImageView01);
	mRightImageView = (ImageView) findViewById(R.id.ImageView02);

	mLeftImageView.setImageBitmap(firstImage);
	mRightImageView.setImageBitmap(secondImage);

	mLeftTopTextView = (TextView) findViewById(R.id.q1leftTopTextView);
	mRightTopTextView = (TextView) findViewById(R.id.q1rightTopTextView);
	mLeftBottomTextView = (TextView) findViewById(R.id.q1leftBottomTextView);
	mRightBottomTextView = (TextView) findViewById(R.id.q1rightBottomTextView);

	mLeftTopTextView.setText("Background: " + Color.red(IM1_BACKGROUND));
	mRightTopTextView.setText("Background: " + Color.red(IM2_BACKGROUND));
	mLeftBottomTextView.setText("Middle: " + Color.red(SQ_COLOR));
	mRightBottomTextView.setText("Middle: " + Color.red(mChangeableColor));
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
	    int color = Color.red(mChangeableColor);
	    if (Math.abs(x) > Math.abs(y)) {

		if (x > 0) {
		    if (color + 4 < 255)
			color += 4;
		} else {
		    if (color - 4 > 0)
			color -= 4;
		}
		mChangeableColor = Color.rgb(color, color, color);
		mRightBottomTextView.setText("Middle: "
			+ Color.red(mChangeableColor));

	    }
	    drawCenterOfRightMiddleSquare(color);
	}
	return super.onTrackballEvent(event);
    }

    private void drawCenterOfRightMiddleSquare(int color) {
	int l = IMAGE_SIZE / 2 - INNER_SQUARE_SIZE;
	int t = IMAGE_SIZE / 2 + INNER_SQUARE_SIZE;
	int r = IMAGE_SIZE / 2 - INNER_SQUARE_SIZE;
	int b = IMAGE_SIZE / 2 + INNER_SQUARE_SIZE;
	for (int x = l; x < t; x++) {
	    for (int y = r; y < b; y++) {

		secondImage.setPixel(x, y, Color.rgb(color, color, color));

	    }
	}
	mRightImageView.setImageBitmap(secondImage);
	mRightImageView.invalidate();
    }
}
