package mobi.sherif.animation.listview;

import mobi.sherif.animation.AnimationFactory;
import mobi.sherif.animation.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

public class OutScaleListView extends RelativeLayout implements OnItemClickListener {
	private ListView mListView; 
	private RelativeLayout mParent;
	public OutScaleListView(Context context) {
		super(context);
		mListView = new ListView(context);
		init();
	}

	public OutScaleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mListView = new ListView(context, attrs);
		init();
	}

	public OutScaleListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mListView = new ListView(context, attrs, defStyle);
		init();
	}

	private void init() {
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mListView.setId(android.R.id.list);
		mListView.setOnItemClickListener(this);
		addView(mListView, params);
		mParent = new RelativeLayout(getContext());
		mParent.setBackgroundColor(Color.argb(0, 0, 0, 0));
		mParent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {}
		});
		mParent.setVisibility(GONE);
		params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.addRule(ALIGN_LEFT, android.R.id.list);
		params.addRule(ALIGN_RIGHT, android.R.id.list);
		params.addRule(ALIGN_TOP, android.R.id.list);
		params.addRule(ALIGN_BOTTOM, android.R.id.list);
		addView(mParent, params);
	}
	
	public ListView getListView() {
		return mListView;
	}
	OnItemClickListener mListener;
	public void setOnItemClickListener(OnItemClickListener listener) {
		mListener = listener;
	}
	Animation mAnimationOut;
	public void setOutAnimation(Animation anim) {
		mAnimationOut = anim;
	}
	Animation mAnimationIn;
	public void setInAnimation(Animation anim) {
		mAnimationIn = anim;
	}
	public void setAdapter(ListAdapter adapter) {
		mListView.setAdapter(adapter);
	}
	private boolean animating = false;
	private View mLastViewAnimated;
	@Override
	public synchronized void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long id) {
		if(!animating)
			animating = true;
		else return;
		if(mAnimationOut == null) {
			mAnimationOut = AnimationFactory.outToLeft();
			mAnimationOut.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
			mAnimationOut.setFillAfter(true);
		}
		mAnimationOut.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				int top = view.getTop();
				int left = view.getLeft();
				int right = view.getRight();
				int bottom = view.getBottom();
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
				params.topMargin = top;
				params.leftMargin = left;
				params.rightMargin = mParent.getWidth() - right;
				params.bottomMargin = mParent.getHeight() - bottom;
				mParent.setVisibility(View.VISIBLE);
				View fakeView = adapterView.getAdapter().getView(position, null, null);
				fakeView.setId(R.id.fakeview);
				mParent.addView(fakeView, params);
				ScaleAnimation anim = new ScaleAnimation(1.0f, 1.0f, 1.0f, 2.0f);
				anim.setInterpolator(new LinearInterpolator());
				anim.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
				anim.setFillAfter(true);
				anim.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {}
					
					@Override
					public void onAnimationRepeat(Animation animation) {}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						animating = false;
					}
				});
				fakeView.startAnimation(anim);
			}
		});
//		mAnimationOut.reset();
//		mAnimationOut.startNow();
		mLastViewAnimated = view;
		for(int i=0;i<adapterView.getChildCount();i++) {
			if(adapterView.getChildAt(i)!=mLastViewAnimated) {
				adapterView.getChildAt(i).startAnimation(mAnimationOut);
			}
		}
		if(mListener != null) {
			mListener.onItemClick(adapterView, view, position, id);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(mParent.getVisibility() == View.VISIBLE) {
				mParent.removeAllViews();
				mParent.setVisibility(View.GONE);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	public synchronized boolean onBackPressed() {
		if(mParent.getVisibility() == View.VISIBLE) {
			backToList();
			return true;
		}
		else
			return false;
	}
	private void backToList() {
		if(!animating)
			animating = true;
		else return;
		mParent.removeAllViews();
		mParent.setVisibility(View.GONE);
		if(mAnimationIn == null) {
			mAnimationIn = AnimationFactory.inFromLeft();
			mAnimationIn.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
			mAnimationIn.setFillAfter(true);
		}
		mAnimationIn.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				animating = false;
			}
		});
//		mAnimationIn.reset();
//		mAnimationIn.startNow();
		AdapterView<?> adapterView = mListView;
		for(int i=0;i<adapterView.getChildCount();i++) {
			if(adapterView.getChildAt(i)!=mLastViewAnimated) {
				adapterView.getChildAt(i).startAnimation(mAnimationIn);
			}
		}
		
	}
}
