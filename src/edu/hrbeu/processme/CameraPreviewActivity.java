package edu.hrbeu.processme;

import java.io.IOException;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class CameraPreviewActivity extends Activity implements
	SurfaceHolder.Callback {
    Camera mCamera;
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    boolean mPreviewRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	getWindow().setFormat(PixelFormat.TRANSLUCENT);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

	WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.camera_surface);
	mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
	mSurfaceHolder = mSurfaceView.getHolder();
	mSurfaceHolder.addCallback(this);
	mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	final Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

	    public void onPictureTaken(byte[] imageData, Camera c) {

		while (true) {
		    boolean bNoException = true;
		    try {
			ProcessMeBlurActivity.sBitmap = BitmapFactory
				.decodeByteArray(imageData, 0, imageData.length);
		    } catch (OutOfMemoryError x) {
			bNoException = false;
			Log.e("ERRROR!", "Problem to allocate memorry");

		    }
		    if (bNoException)
			break;
		}
		finish();
	    }

	};
	mSurfaceView.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		mCamera.takePicture(null, null, mPictureCallback);
	    }
	});
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
	    int height) {
	if (mPreviewRunning) {
	    mCamera.stopPreview();
	}

	Camera.Parameters p = mCamera.getParameters();

	p.setPictureSize(480, 640);
	p.setPreviewSize(height, width);
	mCamera.setParameters(p);

	try {
	    mCamera.setPreviewDisplay(holder);

	} catch (IOException e) {
	    e.printStackTrace();
	}

	mCamera.startPreview();
	mPreviewRunning = true;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
	mCamera = Camera.open();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
	mCamera.stopPreview();

	mPreviewRunning = false;

	mCamera.release();

    }
}
