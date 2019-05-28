package seb.niu.project.eatandgo;
/*--------------------首頁背景--------------------*/
import java.util.ArrayList;
import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.os.Build;

public class MainActivityHistory_one extends ActionBarActivity {

	//清單
	ListView mListView;
	//內文
	TextView mTextView;
	//刪除、返回
    Button   btn_exit,btn_delete;
    
    SQLitemenudb_one db = new SQLitemenudb_one(MainActivityHistory_one.this);
    
    //紀錄欲刪除之編號
    int id;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_history_one);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);

        mListView = (ListView) findViewById(R.id.listView1);
        mTextView = (TextView) findViewById(R.id.textView1);
        btn_exit = (Button) findViewById(R.id.button1);
        btn_delete = (Button) findViewById(R.id.button2);
        
        db.curosr();
        
        listviewupdate();
        
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				mListView.setVisibility(View.INVISIBLE);
				mTextView.setVisibility(View.VISIBLE);
				btn_exit.setVisibility(View.VISIBLE);
				btn_delete.setVisibility(View.VISIBLE);
				db.cursor.moveToPosition(arg2);
				id = db.cursor.getInt(0);
				mTextView.append("第" + (arg2+1) + "筆訂單:\n");
				mTextView.append("主餐:" + db.cursor.getString(1) + "\t\t數量:"
						+ db.cursor.getInt(3) + "\n特殊需求:" + db.cursor.getString(4)
						+ "\n");
				mTextView.append("飲料:" + db.cursor.getString(5) + "\t\t溫度:"
						+ db.cursor.getString(7) + "\t\t數量:" + db.cursor.getInt(8)
						+ "\n");
				int total = db.cursor.getInt(2)*db.cursor.getInt(3)
						 + db.cursor.getInt(6)*db.cursor.getInt(8);
				mTextView.append("特殊需求:" + db.cursor.getString(9) + "\n\n");
				mTextView.append("總價錢:" + total + "\n");
				mTextView.setTextSize(20.0f);
			}
		});
        
        btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mListView.setVisibility(View.VISIBLE);
				mTextView.setVisibility(View.INVISIBLE);
				btn_exit.setVisibility(View.INVISIBLE);
				btn_delete.setVisibility(View.INVISIBLE);
				mTextView.setText("");
			}
		});
        
        btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.delete(id);
				listviewupdate();
				mListView.setVisibility(View.VISIBLE);
				mTextView.setVisibility(View.INVISIBLE);
				btn_exit.setVisibility(View.INVISIBLE);
				btn_delete.setVisibility(View.INVISIBLE);
				mTextView.setText("");
			}
		});
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    private void listviewupdate() {
		// TODO Auto-generated method stub
    	
    	ArrayList<HashMap<String, String>> r1 = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < db.select(0).size(); i++) {
			HashMap<String, String> item1 = new HashMap<String, String>();
			item1.put("main", db.select(1).get(i));
			item1.put("main_price", db.select(2).get(i));
			r1.add(item1);
		}
		SimpleAdapter adapter = new SimpleAdapter(
				MainActivityHistory_one.this, r1,
				android.R.layout.simple_list_item_2, new String[] {
						"main", "main_price" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		mListView.setAdapter(adapter);
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
            View rootView = inflater.inflate(R.layout.fragment_main_activity_index, container, false);
            return rootView;
        }
    }

}
