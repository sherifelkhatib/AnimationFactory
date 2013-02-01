package mobi.sherif.testanimationfactory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch(v.getId()) {
		case R.id.buttonbasicanimations:
			intent = new Intent(this, BasicAnimations.class);
			break;
		case R.id.buttonlistview:
			intent = new Intent(this, ListViewAnimations.class);
			break;
		}
		startActivity(intent);
	}

}
