package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

public class ProcessingTimerHorizontal extends ProcessingTimerAbstract {

    protected Bitmap mImageEdited;
    ImageView mImageView;
    protected int pixels[];
    protected int yOffset;
    private final int length;
    protected final int mImHeight;
    protected final int mImWidth;

    public ProcessingTimerHorizontal(Activity activity, Bitmap mImageEdited,
	    ImageView mImageView) {
	super(activity);
	this.mImageEdited = mImageEdited;
	this.mImageView = mImageView;
	this.mImHeight = mImageEdited.getHeight();
	this.mImWidth = mImageEdited.getWidth();
	length = mImHeight / 6;
	pixels = new int[length];
    }

    public void setNewBitmap(Bitmap newBitmap) {
	this.mImageEdited = newBitmap;
    }

    @Override
    protected void initMethod() {
	mMethod = new Runnable() {

	    @Override
	    public void run() {
		tickMethod(mCurrentTick);
		mCurrentTick += 1;
	    }

	};

    }

    private void tickMethod(int tick) {
	try {
	    mImageEdited.getPixels(pixels, 0, 1, tick, yOffset, 1, length);
	    for (int i = 0; i < length; i++) {

		int pixel = processPixel(tick, i, pixels[i]);
		mImageEdited.setPixel(tick, yOffset + i, pixel);
	    }
	} catch (IllegalArgumentException x) {
	    yOffset += length;
	    mCurrentTick = 0;
	    if (yOffset > mImHeight)
		yOffset = 0;
	}

	mImageView.invalidate();
    }

    /**
     * Method to process each pixel
     * 
     * @param x
     *            Value of timer's tick
     * @param y
     *            Index of pixel in the row
     * @param pixel
     *            Value of pixel
     */
    protected int processPixel(int x, int y, int pixel) {
	int red = Color.red(pixel);
	int green = Color.green(pixel);
	int blue = Color.blue(pixel);
	pixel = Color.rgb(blue - 8, green - 7, red - 6);
	return pixel;

    }

}
