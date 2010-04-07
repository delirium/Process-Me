package edu.hrbeu.processme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

public class ProcessingTimerVertical extends ProcessingTimerAbstract {

    private Bitmap mImageEdited;
    ImageView mImageView;
    protected int pixels[];
    private int xOffset;
    private final int length;
    private final int mImWidth;

    public ProcessingTimerVertical(Activity activity, Bitmap mImageEdited,
	    ImageView mImageView) {
	super(activity);
	this.mImageEdited = mImageEdited;
	this.mImageView = mImageView;
	this.mImWidth = mImageEdited.getWidth();
	length = mImWidth / 5;
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
	    mImageEdited.getPixels(pixels, 0, length, xOffset, tick, length, 1);
	    for (int i = 0; i < length; i++) {
		int red = Color.red(pixels[i]);
		int green = Color.green(pixels[i]);
		int blue = Color.blue(pixels[i]);
		pixels[i] = Color.rgb(green + 7, blue + 6, red + 8);
		mImageEdited.setPixel(xOffset + i, tick, pixels[i]);
	    }
	} catch (IllegalArgumentException x) {
	    xOffset += length;
	    mCurrentTick = 0;
	    if (xOffset >= mImWidth)
		xOffset = 0;
	}

	// mImageView.invalidate();
    }

}
