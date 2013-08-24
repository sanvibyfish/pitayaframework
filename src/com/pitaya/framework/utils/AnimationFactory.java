package com.pitaya.framework.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
 *  Copyright 2011 Sherif
 */

public class AnimationFactory {
	/**
	 * @return Animation that moves from left to position of View
	 */
	public static Animation inFromLeft() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	/**
	 * @return Animation that moves from Right to position of View
	 */
	public static Animation inFromRight() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * @return Animation that moves from Top to position of View
	 */
	public static Animation inFromTop() {
		Animation inFromTop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromTop.setInterpolator(new AccelerateInterpolator());
		return inFromTop;
	}

	/**
	 * @return Animation that moves from Bottom to position of View
	 */
	public static Animation inFromBottom() {
		Animation inFromBottom = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromBottom.setInterpolator(new AccelerateInterpolator());
		return inFromBottom;
	}

	/**
	 * @return Animation that fades in
	 */
	public static Animation inFade() {
		Animation inFade = new AlphaAnimation(0.0f, 1.0f);
		inFade.setInterpolator(new AccelerateInterpolator());
		return inFade;
	}

	/**
	 * @return Animation that moves from position of View to left
	 */
	public static Animation outToLeft() {
		Animation outToLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToLeft.setInterpolator(new AccelerateInterpolator());
		return outToLeft;
	}

	/**
	 * @return Animation that moves from position of View to right
	 */
	public static Animation outToRight() {
		Animation outToRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outToRight.setInterpolator(new AccelerateInterpolator());
		return outToRight;
	}

	/**
	 * @return Animation that moves from position of View to top
	 */
	public static Animation outToTop() {
		Animation outToTop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f);
		outToTop.setInterpolator(new AccelerateInterpolator());
		return outToTop;
	}

	/**
	 * @return Animation that moves from position of View to bottom
	 */
	public static Animation outToBottom() {
		Animation outToBottom = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f);
		outToBottom.setInterpolator(new AccelerateInterpolator());
		return outToBottom;
	}

	/**
	 * @return Animation that fades out
	 */
	public static Animation outFade() {
		Animation outFade = new AlphaAnimation(1.0f, 0.0f);
		outFade.setInterpolator(new AccelerateInterpolator());
		return outFade;
	}

	/**
	 * Animates the textview by translating its characters to the left and right
	 * letter by letter
	 * 
	 * @param view
	 *            TextView that will be animated
	 */
	public static void outHorizontal(TextView view) {
		outHorizontal(view, 1000, 300, null);
	}

	/**
	 * Animates the textview by translating its characters to the left and right
	 * letter by letter
	 * 
	 * @param view
	 *            TextView that will be animated
	 * @param listener
	 *            callback when animation is over
	 */
	public static void outHorizontal(TextView view, final SherifAnimationListener listener) {
		outHorizontal(view, 1000, 300, listener);
	}

	/**
	 * Animates the textview by translating its characters to the left and right
	 * letter by letter
	 * 
	 * @param view
	 *            TextView that will be animated
	 * @param duration
	 *            duration of translation of each letter
	 * @param offset
	 *            duration between each animation
	 */
	public static void outHorizontal(TextView view, long duration, long offset) {
		outHorizontal(view, duration, offset, null);
	}
	/**
	 * Animates the textview by translating its characters to the left and right
	 * letter by letter
	 * 
	 * @param view
	 *            TextView that will be animated
	 * @param duration
	 *            duration of translation of each letter
	 * @param offset
	 *            duration between each animation
	 * @param listener
	 *            callback when animation is over
	 */
	public static void outHorizontal(TextView view, long duration, long offset,
			final SherifAnimationListener listener) {
		String mText = view.getText().toString();
		Context mContext = view.getContext();

		final LinearLayout replace = new LinearLayout(mContext);
		replace.setBackgroundDrawable(view.getBackground());
		replace.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view
				.getPaddingRight(), view.getPaddingBottom());
		replace.setOrientation(LinearLayout.HORIZONTAL);

		TextView[] array = new TextView[mText.length()];
		for (int i = 0; i < mText.length(); i++) {
			array[i] = new TextView(mContext);
			array[i].setText(String.valueOf(mText.charAt(i)));
			array[i].setTextScaleX(view.getTextScaleX());
			array[i]
					.setTextSize(TypedValue.COMPLEX_UNIT_PX, view.getTextSize());
			array[i].setTextColor(view.getTextColors());
			replace.addView(array[i]);
		}

		final ViewParent par = view.getParent();
		int i = 0;
		if (par instanceof LinearLayout) {
			LinearLayout parent = (LinearLayout) par;
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
					.getLayoutParams();
			for (i = 0; i < parent.getChildCount(); i++) {
				if (parent.getChildAt(i) == view)
					break;
			}
			view.setVisibility(View.GONE);
			parent.addView(replace, i, params);
		}
		else if(par instanceof RelativeLayout) {
			RelativeLayout parent = (RelativeLayout) par;
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			for (i = 0; i < parent.getChildCount(); i++) {
				if (parent.getChildAt(i) == view)
					break;
			}
			view.setVisibility(View.GONE);
			parent.addView(replace, i, params);
		}
		else if(par instanceof FrameLayout) {
			FrameLayout parent = (FrameLayout) par;
			FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
					.getLayoutParams();
			for (i = 0; i < parent.getChildCount(); i++) {
				if (parent.getChildAt(i) == view)
					break;
			}
			view.setVisibility(View.GONE);
			parent.addView(replace, i, params);
		}
		else if(par instanceof TableLayout) {
			TableLayout parent = (TableLayout) par;
			TableLayout.LayoutParams params = (TableLayout.LayoutParams) view
					.getLayoutParams();
			for (i = 0; i < parent.getChildCount(); i++) {
				if (parent.getChildAt(i) == view)
					break;
			}
			view.setVisibility(View.GONE);
			parent.addView(replace, i, params);
		}
		else if(par instanceof TableRow) {
			TableRow parent = (TableRow) par;
			TableRow.LayoutParams params = (TableRow.LayoutParams) view
					.getLayoutParams();
			for (i = 0; i < parent.getChildCount(); i++) {
				if (parent.getChildAt(i) == view)
					break;
			}
			view.setVisibility(View.GONE);
			parent.addView(replace, i, params);
		}

		// final int location = i;
		int left = 0;
		int right = mText.length() - 1;
		Animation outToLeft;
		Animation outToRight;
		for (i = 0; i < mText.length() / 2; i++) {
			final View leftView = array[left];
			final View rightView = array[right];
			outToLeft = AnimationFactory.outToLeft();
			outToLeft.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					leftView.setVisibility(View.INVISIBLE);
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			outToLeft.setDuration(duration);
			outToRight = AnimationFactory.outToRight();
			if (left == right - 1) {
				outToRight.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						rightView.setVisibility(View.INVISIBLE);
						replace.setVisibility(View.GONE);
						// if(par instanceof LinearLayout){
						// LinearLayout parent = (LinearLayout)par;
						// if(parent!=null)
						// Log.v("shush","parent");
						// parent.removeViewAt(location);
						// Log.v("shush","removed the ll");
						// }
						if (listener != null)
							listener.onAnimationEnd(animation);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
			} else {
				outToRight.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						rightView.setVisibility(View.INVISIBLE);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});
			}
			outToRight.setDuration(duration);
			outToLeft.setStartOffset(offset);
			outToRight.setStartOffset(offset);
			leftView.setAnimation(outToLeft);
			rightView.setAnimation(outToRight);
			offset += 500;
			left++;
			right--;
		}
		if (left == right) {
			final View rightView = array[right];
			outToRight = AnimationFactory.outToRight();
			outToRight.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					rightView.setVisibility(View.INVISIBLE);
					replace.setVisibility(View.GONE);
					// if(par instanceof LinearLayout){
					// LinearLayout parent = (LinearLayout)par;
					// if(parent!=null)
					// Log.v("shush","parent");
					// parent.removeViewAt(location);
					// Log.v("shush","removed the ll");
					// }
					if (listener != null)
						listener.onAnimationEnd(animation);
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			outToRight.setDuration(duration);
			outToRight.setStartOffset(offset);
			rightView.setAnimation(outToRight);
		}
	}

	public interface SherifAnimationListener {
		public void onAnimationEnd(Animation animation);
	}
}
