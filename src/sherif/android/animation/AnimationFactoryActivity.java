package sherif.android.animation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;

public class AnimationFactoryActivity extends Activity implements AnimationFactory.SherifAnimationListener {
	static final int DIALOG_SELECT_INANIMATION = 11;
	static final int DIALOG_SELECT_OUTANIMATION = 22;
	String[] inAnimations = { "inFromLeft", "inFromRight", "inFromTop",
			"inFromBottom", "inFade" };
	String[] outAnimations = { "outToLeft", "outToRight", "outToTop",
			"outToBottom", "outFade", "outHorizontal" };

	TextView sample;
	TextView selected;

	boolean dismiss = true;
	boolean showing = false;
	Animation animation = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sample = (TextView) findViewById(R.id.textView1);
		selected = (TextView) findViewById(R.id.textView2);
		Button select = (Button) findViewById(R.id.button3);
		select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!showing) {
					Log.v("", (dismiss ? "dismiss" : "bringIn"));
					dismiss = sample.getVisibility() == View.VISIBLE;
					if (dismiss)
						showDialog(DIALOG_SELECT_OUTANIMATION);
					else
						showDialog(DIALOG_SELECT_INANIMATION);
				}
			}
		});
	}

	protected void toggle() {
		animation.setDuration(1000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				if (dismiss)
					sample.setVisibility(View.INVISIBLE);
				dismiss = !dismiss;
				showing = false;
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				if (sample.getVisibility() == View.INVISIBLE)
					sample.setVisibility(View.VISIBLE);
			}
		});
		sample.setAnimation(animation);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		CharSequence[] shush;
		Dialog dialog = null;
		switch (id) {
		case DIALOG_SELECT_OUTANIMATION:
			shush = new CharSequence[outAnimations.length];
			for (int i = 0; i < outAnimations.length; i++) {
				shush[i] = outAnimations[i];
			}
			dialog = new AlertDialog.Builder(this).setTitle("Select Animation")
					.setSingleChoiceItems(shush, 5,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									switch (which) {
									case 0:
										animation = AnimationFactory
												.outToLeft();
										break;
									case 1:
										animation = AnimationFactory
												.outToRight();
										break;
									case 2:
										animation = AnimationFactory.outToTop();
										break;
									case 3:
										animation = AnimationFactory
												.outToBottom();
										break;
									case 4:
										animation = AnimationFactory.outFade();
										break;
									case 5:
										animation = null;
										AnimationFactory.outHorizontal(sample, AnimationFactoryActivity.this);
										break;
									}
									;
									selected.setText(outAnimations[which]);
									showing = true;
									dialog.dismiss();
								}
							}).create();
			break;
		case DIALOG_SELECT_INANIMATION:
			shush = new CharSequence[inAnimations.length];
			for (int i = 0; i < inAnimations.length; i++) {
				shush[i] = inAnimations[i];
			}
			dialog = new AlertDialog.Builder(this).setTitle("Select Animation")
					.setSingleChoiceItems(shush, 5,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									switch (which) {
									case 0:
										animation = AnimationFactory
												.inFromLeft();
										break;
									case 1:
										animation = AnimationFactory
												.inFromRight();
										break;
									case 2:
										animation = AnimationFactory
												.inFromTop();
										break;
									case 3:
										animation = AnimationFactory
												.inFromBottom();
										break;
									case 4:
										animation = AnimationFactory.inFade();
										break;
									}
									;
									selected.setText(inAnimations[which]);
									showing = true;
									dialog.dismiss();
								}
							}).create();
			break;
		}
		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface arg0) {
				if(animation!=null)
					toggle();
			}
		});
		return dialog;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		showing = false;
		sample.setVisibility(View.INVISIBLE);
	}
}