package mobi.sherif.testanimationfactory;

import mobi.sherif.animation.listview.OutScaleListView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

public class ListViewAnimations extends FragmentActivity {
	private static String[] items;
	static {
		int num = 10000;
		items = new String[num];
		for(int i=0;i<num;i++)
			items[i] = new StringBuilder(14).append("Item ").append(i).toString();
	}
	private ArrayAdapter<String> mAdapter;
	private OutScaleListView mList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		mList = (OutScaleListView) findViewById(R.id.list);
		mAdapter = new ArrayAdapter<String>(this, R.layout.layout_item, items);
		mList.setAdapter(mAdapter);
	}
	
	@Override
	public void onBackPressed() {
		if(!mList.onBackPressed())
			super.onBackPressed();
	}
}