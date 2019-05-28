package seb.niu.project.eatandgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityOrder_A extends ActionBarActivity {

	// 宣告
	Button btn_map, btn_main_cancel, btn_drink_cancel, btn_last, btn_next,
			btn_add, btn_map_cancel;
	Spinner sp_main, sp_main_num, sp_drink, sp_drink_temp, sp_drink_num;
	EditText ed_main, ed_main_num, ed_main_demand, ed_main_price, ed_drink,
			ed_drink_temp, ed_drink_num, ed_drink_demand, ed_drink_price;
	TextView tv_address, tv_title;
	WebView web_map;
	SQLitemenudb_two db = new SQLitemenudb_two(MainActivityOrder_A.this);

	String[] main_menu = new String[] { "香煎鮭魚飯", "雞腿飯", "排骨飯", "招牌飯", "黑胡椒豬排飯",
			"梅干扣肉飯", "香菇燉雞飯", "旗魚飯", "方便菜飯", "沙茶羊肉炒麵(大)", "沙茶羊肉炒麵(小)",
			"沙茶牛肉炒麵(大)", "沙茶牛肉炒麵(小)", "沙茶豬肉炒麵(大)", "沙茶豬肉炒麵(小)", "三鮮炒麵(大)",
			"三鮮炒麵(小)", "什錦炒麵(大)", "什錦炒麵(小)", "青椒牛肉炒飯(大)", "青椒牛肉炒飯(小)",
			"青椒羊肉炒飯(大)", "青椒羊肉炒飯(小)", "蝦仁蛋炒飯(大)", "蝦仁蛋炒飯(小)", "肉絲蛋炒飯(大)",
			"肉絲蛋炒飯(小)", "培根蛋炒飯(大)", "培根蛋炒飯(小)", "火腿蛋炒飯(大)", "火腿蛋炒飯(小)",
			"鳳梨蛋炒飯(大)", "鳳梨蛋炒飯(小)", "高麗菜蛋炒飯(大)", "高麗菜蛋炒飯(小)", "香菇燉雞湯", "蛤蠣湯",
			"青菜豆腐湯", "蛋花湯", "煎鮭魚", "雞腿", "旗魚", "煎豬排 ", "雞排", "扣肉", "白飯", "荷包蛋",
			"滷蛋", "青菜" };
	Integer[] main_price = new Integer[] { 80, 75, 65, 65, 65, 65, 65, 60, 45,
			70, 60, 70, 60, 65, 55, 65, 55, 65, 55, 70, 60, 70, 60, 70, 60, 65,
			55, 65, 55, 60, 50, 60, 50, 60, 50, 35, 25, 20, 20, 50, 40, 35, 25,
			25, 25, 10, 10, 10, 30 };
	Integer[] main_cont = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	String[] drink_menu = new String[] { "無" };
	Integer[] drink_price = new Integer[] { 0 };
	String[] drink_temp = new String[] { "無" };
	Integer[] drink_cont = new Integer[] { 0 };
	int freq = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* hide the app title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* end of hid the app title */
		setContentView(R.layout.activity_main_activity_order_two);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);

		/* spinner */
		sp_main = (Spinner) findViewById(R.id.spinner1);
		sp_main_num = (Spinner) findViewById(R.id.spinner2);
		sp_drink = (Spinner) findViewById(R.id.spinner3);
		sp_drink_temp = (Spinner) findViewById(R.id.spinner4);
		sp_drink_num = (Spinner) findViewById(R.id.spinner5);
		/* end of spinner */
		/* WEBVIEW */
		web_map = (WebView) findViewById(R.id.webView1);
		web_map.setVisibility(View.INVISIBLE);
		/* END OF WEBVIEW */
		/* button */
		btn_map = (Button) findViewById(R.id.button1);
		btn_next = (Button) findViewById(R.id.button2);
		btn_main_cancel = (Button) findViewById(R.id.button3);
		btn_drink_cancel = (Button) findViewById(R.id.button5);
		btn_last = (Button) findViewById(R.id.button6);
		btn_add = (Button) findViewById(R.id.button7);
		btn_map_cancel = (Button) findViewById(R.id.button4);
		/* end of button */

		/* edit text */
		ed_main = (EditText) findViewById(R.id.editText1);
		ed_main_num = (EditText) findViewById(R.id.editText2);
		ed_main_demand = (EditText) findViewById(R.id.editText3);
		ed_main_price = (EditText) findViewById(R.id.editText8);
		ed_drink = (EditText) findViewById(R.id.editText4);
		ed_drink_temp = (EditText) findViewById(R.id.editText5);
		ed_drink_num = (EditText) findViewById(R.id.editText6);
		ed_drink_demand = (EditText) findViewById(R.id.editText7);
		ed_drink_price = (EditText) findViewById(R.id.editText9);
		/* 設定唯讀 */
		setEditTextReadOnly(ed_main);
		setEditTextReadOnly(ed_main_price);
		setEditTextReadOnly(ed_main_num);
		setEditTextReadOnly(ed_drink);
		setEditTextReadOnly(ed_drink_price);
		setEditTextReadOnly(ed_drink_temp);
		setEditTextReadOnly(ed_drink_num);
		/* end of edit text */

		db.curosr();

		/* text text */
		tv_address = (TextView) findViewById(R.id.textView1);
		tv_title = (TextView) findViewById(R.id.textView2);
		/* end of text text */

		/* button on click */
		btn_map.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				web_map.setVisibility(View.VISIBLE);

				String url = "https://www.google.com.tw/maps/place/%E6%AD%A1%E5%96%9C%E"
						+ "5%A0%82/@24.7453156,121.7457313,18z/data=!"
						+ "4m8!1m2!2m1!1z5q2h5Zac5aCC!3m4!1s0x0:"
						+ "0x2f8a97774afc6485!8m2!3d24.7449331!4d121.7446649?hl=zh-TW&authuser=0";

				WebSettings websettings = web_map.getSettings();
				websettings.setSupportZoom(true);
				websettings.setBuiltInZoomControls(true);
				websettings.setJavaScriptEnabled(true);
				web_map.setWebViewClient(new WebViewClient());

				web_map.loadUrl(url);

				btn_map_cancel.setVisibility(View.VISIBLE);

			}
		});
		btn_next.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if( freq == 0 )
				{
					Toast toast = Toast.makeText(MainActivityOrder_A.this
							, "未點餐，請重新輸入", Toast.LENGTH_LONG); 
					toast.show();
				}
				else
				{
					Intent intent = new Intent(MainActivityOrder_A.this
						, MainActivityOrder_A_Check.class);
					//將資料參數放入bundel中，再傳入check
					Bundle bundle = new Bundle();
					bundle.putInt("freq", freq);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		});
		btn_main_cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed_main.setText("");
				ed_main_num.setText("0");
				ed_main_demand.setText("");
				ed_main_price.setText("0");
			}
		});
		btn_drink_cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed_drink.setText("");
				ed_drink_temp.setText("");
				ed_drink_price.setText("0");
				ed_drink_num.setText("0");
				ed_drink_demand.setText("");
			}
		});
		btn_last.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivityOrder_A.this,
						MainActivityRestaurant.class);
				startActivity(intent);
			}
		});
		btn_add.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (ed_main.getText().toString().equals("無") == false
						|| ed_drink.getText().toString().equals("無") == false) {
					if ((ed_main.getText().toString().equals("無") == false
							&& ed_main_num.getText().toString().equals("0") == false
							&& ed_drink.getText().toString().equals("無") == true
							&& ed_drink_num.getText().toString().equals("0") == true && ed_drink_temp
							.getText().toString().equals("無") == true)
							|| (ed_drink.getText().toString().equals("無") == false
									&& ed_drink_num.getText().toString()
											.equals("0") == false
									&& ed_drink_temp.getText().toString()
											.equals("無") == false
									&& ed_main.getText().toString().equals("無") == true && ed_main_num
									.getText().toString().equals("0") == true)
							|| (ed_drink.getText().toString().equals("無") == false
									&& ed_drink_num.getText().toString()
											.equals("0") == false
									&& ed_drink_temp.getText().toString()
											.equals("無") == false
									&& ed_main.getText().toString().equals("無") == false && ed_main_num
									.getText().toString().equals("0") == false)) {
						db.insert(ed_main.getText().toString(), Integer
								.parseInt(ed_main_price.getText().toString()),
								Integer.parseInt(ed_main_num.getText()
										.toString()), ed_main_demand.getText()
										.toString(), ed_drink.getText()
										.toString(), Integer
										.parseInt(ed_drink_price.getText()
												.toString()), ed_drink_temp
										.getText().toString(), Integer
										.parseInt(ed_drink_num.getText()
												.toString()), ed_drink_demand
										.getText().toString());
						ed_main.setText("無");
						ed_main_price.setText("0");
						ed_main_num.setText("0");
						ed_main_demand.setText("");
						ed_drink.setText("無");
						ed_drink_price.setText("0");
						ed_drink_temp.setText("無");
						ed_drink_num.setText("0");
						ed_drink_demand.setText("");
						freq++;
					} else {
						Toast toast = Toast.makeText(MainActivityOrder_A.this,
								"錯誤，請重新輸入", Toast.LENGTH_LONG);
						toast.show();
					}
				} else {
					Toast toast = Toast.makeText(MainActivityOrder_A.this,
							"錯誤，請重新輸入", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});
		btn_map_cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_map_cancel.setVisibility(View.INVISIBLE);
				web_map.setVisibility(View.INVISIBLE);
			}
		});
		/* end of button on click */

		/* adapter */
		ArrayAdapter<String> adapter_main = new ArrayAdapter<String>(
				MainActivityOrder_A.this,
				R.layout.support_simple_spinner_dropdown_item, main_menu);
		sp_main.setAdapter(adapter_main);

		ArrayAdapter<Integer> adapter_main_num = new ArrayAdapter<Integer>(
				MainActivityOrder_A.this,
				R.layout.support_simple_spinner_dropdown_item, main_cont);
		sp_main_num.setAdapter(adapter_main_num);

		ArrayAdapter<String> adapter_drink = new ArrayAdapter<String>(
				MainActivityOrder_A.this,
				R.layout.support_simple_spinner_dropdown_item, drink_menu);
		sp_drink.setAdapter(adapter_drink);

		ArrayAdapter<String> adapter_drink_temp = new ArrayAdapter<String>(
				MainActivityOrder_A.this,
				R.layout.support_simple_spinner_dropdown_item, drink_temp);
		sp_drink_temp.setAdapter(adapter_drink_temp);

		ArrayAdapter<Integer> adapter_drink_num = new ArrayAdapter<Integer>(
				MainActivityOrder_A.this,
				R.layout.support_simple_spinner_dropdown_item, drink_cont);
		sp_drink_num.setAdapter(adapter_drink_num);
		/* end of adapter */

		/* setOnItemSelectedListener */

		sp_main.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ed_main.setText(main_menu[arg2]);
				ed_main_price.setText("" + main_price[arg2]);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		sp_main_num.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				// settext因為overload,必須把它變成字串
				ed_main_num.setText("" + main_cont[arg2]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		sp_drink.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ed_drink.setText(drink_menu[arg2]);
				ed_drink_price.setText("" + drink_price[arg2]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		sp_drink_temp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ed_drink_temp.setText(drink_temp[arg2]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		sp_drink_num.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ed_drink_num.setText("" + drink_cont[arg2]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		/* end of setOnItemSelectedListener */

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public static void setEditTextReadOnly(TextView view) {
		if (view instanceof android.widget.EditText) {
			view.setCursorVisible(false); // 設置光標不可見
			view.setFocusable(false); // 無焦點
			view.setFocusableInTouchMode(false); // 觸摸時也得不到焦點
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
