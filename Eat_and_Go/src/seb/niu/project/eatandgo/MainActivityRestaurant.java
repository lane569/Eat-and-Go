package seb.niu.project.eatandgo;
/*--------------------餐廳--------------------*/
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
import android.widget.ImageButton;
import android.os.Build;

public class MainActivityRestaurant extends ActionBarActivity {
	//老蔡、歡喜堂、阿嬤、灶坑
	ImageButton imgbtn_A, imgbtn_B, imgbtn_C, imgbtn_D;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* hide the app title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* end of hid the app title */
		setContentView(R.layout.activity_main_activity_restaurant);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);
		/* Image Button */
		imgbtn_A = (ImageButton) findViewById(R.id.imageButton1);
		imgbtn_B = (ImageButton) findViewById(R.id.imageButton2);
		imgbtn_C = (ImageButton) findViewById(R.id.imageButton3);
		imgbtn_D = (ImageButton) findViewById(R.id.imageButton4);

		imgbtn_A.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivityRestaurant.this,
						MainActivityOrder.class);
				startActivity(intent);
				
			}
		});
		imgbtn_B.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent( MainActivityRestaurant.this
						, MainActivityOrder_A.class);
				startActivity(intent);
				
			}
		});
		imgbtn_C.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent( MainActivityRestaurant.this
						, MainActivityOrder_B.class);
				startActivity(intent);
			
			}
		});
		imgbtn_D.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent( MainActivityRestaurant.this
						, MainActivityOrder_C.class);
				startActivity(intent);
			}
		});
		/* End of Image Button */
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
