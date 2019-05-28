package seb.niu.project.eatandgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.os.Build;

public class MainActivityOrder_B_Check extends ActionBarActivity {

	Button btn_next;
	TextView tv_check;
	SQLitemenudb_three db = new SQLitemenudb_three(MainActivityOrder_B_Check.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* hide the app title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* end of hid the app title */
		setContentView(R.layout.activity_main_activity_order_three_check);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);

		/* button */
		btn_next = (Button) findViewById(R.id.button2);
		/* end of button */

		/* textview */
		tv_check = (TextView) findViewById(R.id.textView1);
		/* end of listview */

		/* 將點餐次數傳到check裡  */
		Bundle bundle = this.getIntent().getExtras();
		int freq = bundle.getInt("freq");

		/* 讀取資料  */
		db.curosr();
		
		/* 顯示訂單 */
		listviewupdate(freq);
		
		/* 下拉式  */
		tv_check.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		btn_next.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivityOrder_B_Check.this,
						MainActivityOrderFinish.class);
				startActivity(intent);
				MainActivityOrder_B_Check.this.finish();
			}
		});

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void listviewupdate(int freq) {
		tv_check.append("訂單:\n");
		int total = 0;
		
		for (int i = db.select(0).size() - freq; i < db.select(0).size(); i++) {
			
			tv_check.append("主餐:" + db.select(1).get(i) + "\t\t數量:"
					+ db.select(3).get(i) + "\t\t特殊需求:" + db.select(4).get(i)
					+ "\n");
			tv_check.append("飲料:" + db.select(5).get(i) + "\t\t溫度:"
					+ db.select(7).get(i) + "\t\t數量:" + db.select(8).get(i)
					+ "\n");
			tv_check.append("特殊需求:" + db.select(9).get(i) + "\n\n");
			
			tv_check.setTextSize(20.0f);
			
			total += Integer.parseInt(db.select(2).get(i))
					*Integer.parseInt(db.select(3).get(i)) 
					+ Integer.parseInt(db.select(6).get(i))*Integer.parseInt(db.select(8).get(i));
		}
		
		tv_check.append("總價錢:" + total);
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
