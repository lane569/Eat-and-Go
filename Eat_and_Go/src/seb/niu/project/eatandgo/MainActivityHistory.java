package seb.niu.project.eatandgo;

/*--------------------歷史訂單--------------------*/
import java.util.ArrayList;
import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class MainActivityHistory extends ActionBarActivity {
	// 歷史訂單
	ListView mListView, lvr1, lvr2, lvr3, lvr4;
	String[] restaurant = new String[] { "老蔡", "歡喜堂", "阿嬤的古早味", "灶腳飯館" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* hide the app title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* end of hid the app title */
		setContentView(R.layout.activity_main_activity_history);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);

		/* lisview */
		mListView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivityHistory.this, android.R.layout.simple_list_item_1,
				restaurant);
		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg3 == 0) {
					Intent intent = new Intent(MainActivityHistory.this
							, MainActivityHistory_one.class);
					startActivity(intent);
				}
				else if(arg3 == 1)
				{
					Intent intent = new Intent(MainActivityHistory.this
							, MainActivityHistory_two.class);
					startActivity(intent);
				}
				else if(arg3 == 2)
				{
					Intent intent = new Intent(MainActivityHistory.this
							, MainActivityHistory_three.class);
					startActivity(intent);
				}
				else
				{
					Intent intent = new Intent(MainActivityHistory.this
							, MainActivityHistory_four.class);
					startActivity(intent);
				}
			}
		});

		/* the end of list view */

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_index, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_main_activity_index, container, false);
			return rootView;
		}
	}

}
