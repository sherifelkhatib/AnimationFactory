package sherif.android.animation;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

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
}
