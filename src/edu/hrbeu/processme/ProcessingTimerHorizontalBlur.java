package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

public class ProcessingTimerHorizontalBlur extends ProcessingTimerHorizontal {

    /**
     * @param activity
     * @param mImageEdited
     * @param mImageView
     */
    public ProcessingTimerHorizontalBlur(Activity activity,
	    Bitmap mImageEdited, ImageView mImageView) {
	super(activity, mImageEdited, mImageView);
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hrbeu.processme.ProcessingTimerHorizontal#processPixel(int, int,
     * int)
     */
    @Override
    protected int processPixel(int x, int y, int pixel) {

	y = y + yOffset;
	int finalRed = 0, finalGreen = 0, finalBlue = 0;
	// if ((x <= 0) || (y <= 0) || (x >= mImWidth - 1) || (y >= mImHeight -
	// 1)) {
	// return pixel;
	// }

	for (int mx = x - 1; mx < x + 2; mx++)
	    for (int my = y - 1; my < y + 2; my++) {
		try {
		    int neighborPixel = mImageEdited.getPixel(mx, my);
		    finalRed += Color.red(neighborPixel);
		    finalGreen += Color.green(neighborPixel);
		    finalBlue += Color.blue(neighborPixel);
		} catch (IllegalArgumentException ex) {

		}
	    }
	finalRed /= 9;
	finalGreen /= 9;
	finalBlue /= 9;
	// mImageEdited.setPixel(x + 1, y + 1, Color.rgb(150, 150, 150));
	// Log.i("PROCESSING PIXELS", "Returning a pixel");
	return Color.rgb(finalRed, finalGreen, finalBlue);
    }
}
