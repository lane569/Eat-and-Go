package seb.niu.project.eatandgo;
/*--------------------程式首頁--------------------*/
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivityIndex extends ActionBarActivity {
	//訂餐、歷史訂單、離開、刪除資料庫
	Button btn_Order,btn_History,btn_Exit,btn_Delete;
	SQLitemenudb_one db = new SQLitemenudb_one(MainActivityIndex.this);
	SQLitemenudb_two db2 = new SQLitemenudb_two(MainActivityIndex.this);
	SQLitemenudb_three db3 = new SQLitemenudb_three(MainActivityIndex.this);
	SQLitemenudb_four db4 = new SQLitemenudb_four(MainActivityIndex.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*hide the app title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        /*end of hid the app title*/
        setContentView(R.layout.activity_main_activity_index);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);
        
        /*button*/
        btn_Order=(Button) findViewById(R.id.button1);
        btn_History=(Button) findViewById(R.id.button2);
        btn_Exit=(Button) findViewById(R.id.button3);
        btn_Delete = (Button) findViewById(R.id.button4);
        
        btn_Order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivityIndex.this,
						MainActivityRestaurant.class);
				startActivity(intent);
				
			}
		});
        btn_History.setOnClickListener(new OnClickListener() {
			
 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				Intent intent = new Intent(MainActivityIndex.this,
						MainActivityHistory.class);
				startActivity(intent);
 			}
 		});
        btn_Exit.setOnClickListener(new OnClickListener() {
			
 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				//使用單例的ActivityManager關掉所有的Activity再退出App
 		        ActivityManager.getInstance().closeAllActivity();
 			}
 		});
        btn_Delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//清空資料庫
				db.cleanHistoryTable();
				db2.cleanHistoryTable();
				db3.cleanHistoryTable();
				db4.cleanHistoryTable();
			}
		});
        /*End of Button*/
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
            View rootView = inflater.inflate(R.layout.fragment_main_activity_index, container, false);
            return rootView;
        }
    }

}
