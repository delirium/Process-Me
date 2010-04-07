package edu.hrbeu.processme;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;

abstract class ProcessingTimerAbstract {
    private Timer mTimer;
    private TimerTask mTask;
    protected int mCurrentTick;
    private final Activity mActivity;
    protected Runnable mMethod;
    protected boolean mIsStoped;

    public ProcessingTimerAbstract(Activity activity) {
	this.mActivity = activity;
	mIsStoped = true;
	initMethod();
    }

    abstract protected void initMethod();

    public void run(int delay, int period) {
	this.mTimer = new Timer();
	this.mTask = new TimerTask() {

	    @Override
	    public void run() {
		mIsStoped = false;
		mActivity.runOnUiThread(mMethod);
	    }
	};

	this.mTimer.schedule(mTask, delay, period);

    }

    public void stop() {
	if (!mIsStoped)
	    mTimer.cancel();
	mCurrentTick = 0;
	mIsStoped = true;
    }
}
