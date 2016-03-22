package com.example.videoplay.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	private float lastX;
	private float lastY;

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = ev.getX();
			lastY = ev.getY();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			if (lastX == ev.getX() && lastY == ev.getY()) {
				System.out.println("处理成点击事件");
				return false;
			}
			break;

		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
}
