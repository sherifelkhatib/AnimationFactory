package sherif.android.textview;

import sherif.android.exception.UnknownVibrationPattern;
import sherif.android.interfaces.VibrateAble;
import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

/**
 * Extension of {@link TextView}.
 * <li>Supports Orientation using {@link #setOrientation(int)}
 * <li>Supports multiple vibration patterns use {@link #setVibrationPattern}
 * @author Sherif elKhatib
 */
public class VibratingTextView extends android.widget.LinearLayout implements VibrateAble {
	private int pattern;
	/**
	 * Used to set the vibration pattern of the view.
	 * @param pattern Pattern to use
	 * @throws UnknownVibrationPattern
	 * @see VibrationPattern
	 */
	public void setVibrationPattern(int pattern) throws UnknownVibrationPattern {
		switch(pattern){
		case VibrationPattern.RANDOM:
			this.pattern = pattern;
			break;
			default:
				throw new UnknownVibrationPattern(UnknownVibrationPattern.UNKNOWN);
		};
	}
	long duration = 100;
	TextView[] array;

	public VibratingTextView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.textViewStyle);
	}

	TextView view;

	public VibratingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		view = new TextView(context, attrs, defStyle);
		update();
	}

	/* (non-Javadoc)
	 * @see android.widget.LinearLayout#setOrientation(int)
	 */
	@Override
	public void setOrientation(int orientation) {
		super.setOrientation(orientation);
	}
	private void update() {
		String mText = view.getText().toString();
		Context mContext = view.getContext();

		setBackgroundDrawable(view.getBackground());
		setPadding(view.getPaddingLeft(), view.getPaddingTop(), view
				.getPaddingRight(), view.getPaddingBottom());

		array = new TextView[mText.length()];
		for (int i = 0; i < mText.length(); i++) {
			array[i] = new TextView(mContext);
			array[i].setText(String.valueOf(mText.charAt(i)));
			array[i].setTextScaleX(view.getTextScaleX());
			array[i]
					.setTextSize(TypedValue.COMPLEX_UNIT_PX, view.getTextSize());
			array[i].setTextColor(view.getTextColors());
			addView(array[i]);
			Log.v("", array[i].getText().toString());
		}
		animating = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			animating[i] = false;
		}
	}

	public void setText(String text) {
		removeAllViews();
		view.setText(text);
		update();
	}

	boolean vibrateOntouch = true;
	boolean doAnimate = false;
	boolean[] animating;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(vibrateOntouch){
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				doAnimate = true;
				startVibrating();
			} else if (event.getAction() == MotionEvent.ACTION_UP)
				doAnimate = false;
			return true;
		}
		return false;
	}

	@Override
	public void startVibrating() {
		doAnimate = true;
		for (int i = 0; i < array.length; i++) {
			animate(i);
		}
	}
	@Override
	public void stopVibrating() {
		doAnimate = false;
	}
	private void animate(int index) {
		Animation animation = null;
		switch(pattern){
		case VibrationPattern.RANDOM:
			animation = randomizeAnimation();
			break;
		default:
			animation = randomizeAnimation();
			break;
		};
		animation.setDuration(duration);
		animation.setInterpolator(new AccelerateInterpolator());
		animation.setAnimationListener(new AnimationsListener(index));
		array[index].startAnimation(animation);
	}

	private Animation randomizeAnimation() {
		return new TranslateAnimation(Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, (float) Math.random()
						* 0.1f * (((float) Math.random() > 0.5) ? -1 : +1),
				Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, (float) Math.random() * 0.1f
						* (((float) Math.random() > 0.5) ? -1 : +1));
	}
	@Override
	public boolean isVibrating() {
		for (int i = animating.length - 1; i >= 0; i--)
			if (animating[i])
				return true;
		return false;
	}

	private class AnimationsListener implements AnimationListener {

		int index;

		public AnimationsListener(int index) {
			this.index = index;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if(doAnimate){
				animate(index);
				animation.start();
			}
			else
				animating[index] = false;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationStart(Animation animation) {
			animating[index] = true;
		}
	}
}
