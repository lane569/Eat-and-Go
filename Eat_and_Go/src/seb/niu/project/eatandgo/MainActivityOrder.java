package seb.niu.project.eatandgo;
/*--------------------老蔡訂餐--------------------*/
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

public class MainActivityOrder extends ActionBarActivity {

	// 宣告
	Button btn_map, btn_main_cancel, btn_drink_cancel, btn_last, btn_next,
			btn_add, btn_map_cancel;
	Spinner sp_main, sp_main_num, sp_drink, sp_drink_temp, sp_drink_num;
	EditText ed_main, ed_main_num, ed_main_demand, ed_main_price, ed_drink,
			ed_drink_temp, ed_drink_num, ed_drink_demand, ed_drink_price;
	TextView tv_address, tv_title;
	SQLitemenudb_one db = new SQLitemenudb_one(MainActivityOrder.this);

	String[] main_menu = new String[] { "起士蛋三明治", "火腿蛋三明治", "肉鬆蛋三明治", "培根蛋三明治",
			"玉米蛋三明治", "鮪魚蛋三明治", "香雞蛋三明治", "薯餅蛋三明治", "豬排蛋三明治", "卡拉雞腿蛋三明治",
			"薯餅火腿蛋三明治", "薯餅培根蛋三明治", "烤豬肉總匯蛋三明治", "豬排火腿蛋三明治", "豬排培根蛋三明治",
			"黃金豬排蛋三明治", "香酥菲力雞排蛋三明治" };
	Integer[] main_price = new Integer[] { 30, 30, 30, 30, 35, 35, 35, 35, 40,
			45, 45, 45, 45, 50, 50, 50, 50 };
	Integer[] main_cont = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	String[] drink_menu = new String[] { "紅茶(中)", "紅茶(大)", "奶茶(中)", "奶茶(大)",
			"豆漿(中)", "豆漿(大)", "咖啡(中)", "咖啡(大)", "鮮奶茶(中)", "鮮奶茶(大)", "無糖綠茶(中)",
			"無糖綠茶(大)", "柳橙汁(中)", "柳橙汁(大)", "百香果汁(中)", "百香果汁(大)", "水蜜桃汁(中)",
			"水蜜桃汁(大)" };
	Integer[] drink_price = new Integer[] { 10, 15, 15, 20, 10, 15, 15, 20, 25,
			30, 10, 15, 15, 20, 15, 20, 15, 20 };
	String[] drink_temp = new String[] { "hot", "warm", "cold" };
	Integer[] drink_cont = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	WebView web_map;
	int freq = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* hide the app title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* end of hid the app title */
		setContentView(R.layout.activity_main_activity_order_one);
        //將此Activity加進ActivityManager裡管理
        ActivityManager.getInstance().addActivity(this);
		
		/* spinner */
		sp_main = (Spinner) findViewById(R.id.spinner1);
		sp_main_num = (Spinner) findViewById(R.id.spinner2);
		sp_drink = (Spinner) findViewById(R.id.spinner3);
		sp_drink_temp = (Spinner) findViewById(R.id.spinner4);
		sp_drink_num = (Spinner) findViewById(R.id.spinner5);
		/* end of spinner */
		
		/* button */
		btn_map = (Button) findViewById(R.id.button1);
		btn_next = (Button) findViewById(R.id.button2);
		btn_main_cancel = (Button) findViewById(R.id.button3);
		btn_map_cancel = (Button) findViewById(R.id.button4);
		btn_drink_cancel = (Button) findViewById(R.id.button5);
		btn_last = (Button) findViewById(R.id.button6);
		btn_add = (Button) findViewById(R.id.button7);
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
		setEditTextReadOnly(ed_main_num);
		setEditTextReadOnly(ed_main_price);
		setEditTextReadOnly(ed_drink);
		setEditTextReadOnly(ed_drink_price);
		setEditTextReadOnly(ed_drink_temp);
		setEditTextReadOnly(ed_drink_num);
		/* end of edit text */
		
		/* webview */
		web_map = (WebView) findViewById(R.id.webView1);
		web_map.setVisibility(View.INVISIBLE);
		btn_map_cancel.setVisibility(View.INVISIBLE);
		
		/* 資料讀取 */
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
				
				String url = "https://www.google.com.tw/maps/place/260%E5%AE%9C%E8%98%AD%E7%"
				 + "B8%A3%E5%AE%9C%E8%98%AD%E5%B8%82%E5%A5%B3%E4%B8%AD%E8%B" + "7%AF%E4%B8%89%E6%AE%B5225%E8%99%9F/@24.745356,121.7405093,17z/data=!3m1!4b1!4m5!3m4!1s0x3467e4" + 
						"ecac281b5d:0x9bb86864e89f66f6!8m2!3d24.745356!4d121.742698";
				
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
					Toast toast = Toast.makeText(MainActivityOrder.this
							, "未點餐，請重新輸入", Toast.LENGTH_LONG); 
					toast.show();
				}
				else
				{
					Intent intent = new Intent(MainActivityOrder.this
						, MainActivityOrder_Check.class);
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
				ed_main.setText("無");
				ed_main_num.setText("0");
				ed_main_demand.setText("");
				ed_main_price.setText("0");
			}
		});
		btn_drink_cancel.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed_drink.setText("無");
				ed_drink_temp.setText("無");
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
				intent.setClass(MainActivityOrder.this, MainActivityRestaurant.class);
				startActivity(intent);
				MainActivityOrder.this.finish();
			}
		});
		btn_add.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//判斷點餐合理性
				if(ed_main.getText().toString().equals("無") == false  
						|| ed_drink.getText().toString().equals("無") == false)
				{
					if( (ed_main.getText().toString().equals("無") == false 
							&& ed_main_num.getText().toString().equals("0") == false
							&& ed_drink.getText().toString().equals("無") == true 
							&& ed_drink_num.getText().toString().equals("0") == true 
							&& ed_drink_temp.getText().toString().equals("無") == true) 
							|| (ed_drink.getText().toString().equals("無") == false 
							&& ed_drink_num.getText().toString().equals("0") == false 
							&& ed_drink_temp.getText().toString().equals("無") == false
							&& ed_main.getText().toString().equals("無") == true 
							&& ed_main_num.getText().toString().equals("0") == true)
							||(ed_drink.getText().toString().equals("無") == false 
									&& ed_drink_num.getText().toString().equals("0") == false 
									&& ed_drink_temp.getText().toString().equals("無") == false
									&& ed_main.getText().toString().equals("無") == false 
									&& ed_main_num.getText().toString().equals("0") == false))
					{
						db.insert( ed_main.getText().toString()
								, Integer.parseInt(ed_main_price.getText().toString())
								, Integer.parseInt(ed_main_num.getText().toString())
								, ed_main_demand.getText().toString(), ed_drink.getText().toString()
								, Integer.parseInt(ed_drink_price.getText().toString())
								, ed_drink_temp.getText().toString()
								, Integer.parseInt(ed_drink_num.getText().toString())
								, ed_drink_demand.getText().toString());
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
					}
					else
					{  
						Toast toast = Toast.makeText(MainActivityOrder.this
							, "錯誤，請重新輸入", Toast.LENGTH_LONG); 
						toast.show();
					}
				}
				else if(ed_main.getText().toString().equals("無") == true 
						&& ed_drink.getText().toString().equals("無") == true)
				{
					Toast toast = Toast.makeText(MainActivityOrder.this
							, "錯誤，請重新輸入", Toast.LENGTH_LONG); 
					toast.show();
				}
			}
		});
		btn_map_cancel.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				web_map.setVisibility(View.INVISIBLE);
				btn_map_cancel.setVisibility(View.INVISIBLE);
			}
		});
		/* end of button on click */
		
		/* adapter */
		ArrayAdapter<String> adapter_main = 
        		new ArrayAdapter<String>(MainActivityOrder.this
        				, R.layout.support_simple_spinner_dropdown_item
        				, main_menu);
		sp_main.setAdapter(adapter_main);
		
		ArrayAdapter<Integer> adapter_main_num = 
        		new ArrayAdapter<Integer>(MainActivityOrder.this
        				, R.layout.support_simple_spinner_dropdown_item
        				, main_cont);
		sp_main_num.setAdapter(adapter_main_num);
		
		ArrayAdapter<String> adapter_drink = 
        		new ArrayAdapter<String>(MainActivityOrder.this
        				, R.layout.support_simple_spinner_dropdown_item
        				, drink_menu);
		sp_drink.setAdapter(adapter_drink);
		
		ArrayAdapter<String> adapter_drink_temp = 
        		new ArrayAdapter<String>(MainActivityOrder.this
        				, R.layout.support_simple_spinner_dropdown_item
        				, drink_temp);
		sp_drink_temp.setAdapter(adapter_drink_temp);
		
		ArrayAdapter<Integer> adapter_drink_num = 
        		new ArrayAdapter<Integer>(MainActivityOrder.this
        				, R.layout.support_simple_spinner_dropdown_item
        				, drink_cont);
		sp_drink_num.setAdapter(adapter_drink_num);
		/* end of adapter */
		
		/* setOnItemSelectedListener */
		
		sp_main.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ed_main.setText( main_menu[arg2] );
				ed_main_price.setText( "" + main_price[arg2] );		
				
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
				//settext因為overload,必須把它變成字串
				ed_main_num.setText( "" + main_cont[arg2] );
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
				ed_drink.setText( drink_menu[arg2] );
				ed_drink_price.setText( "" + drink_price[arg2] );
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
				ed_drink_temp.setText( drink_temp[arg2] );
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
				ed_drink_num.setText( "" + drink_cont[arg2] );
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
