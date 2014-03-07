package com.google.zxing.client.android;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayResult extends Activity {

	Product []products = {
		new Product(1, "车厢1", "张三", "2014-03-05", "北京"),
		new Product(2, "车厢2", "李四", "2014-03-04", "上海"),
	};
	
	private int getProductId(String id){
		if(id.length() > 10){
			return -1;
		}
		int idd = Integer.parseInt(id);
		int res = -1;
		for(int i = 0; i < products.length; i ++){
			if(idd == products[i].getId())
			{
				res = i;
			}
		}
		return res;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_display_result);
		
		Intent intent = getIntent();
		ProductNode node = (ProductNode)intent.getSerializableExtra(CaptureActivity.PRODUCT_NODE_SERIALE);

		LinearLayout layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.HORIZONTAL);
	    layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    
		if(node == null){
			TextView titleView = new TextView(this);
	        LinearLayout.LayoutParams lparams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
	        titleView.setLayoutParams(lparams);
	        titleView.setTextSize(20);
	        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
	        titleView.setText("无效识别码!");
	        layout.addView(titleView);
		}
		else{
		    List<Entry> entries = node.getEntries();
		    
		    for(int i = 0; i < entries.size(); ++i){
		    	Entry entry = entries.get(i);

		    	LinearLayout rowLayout = new LinearLayout(this);
			    layout.setOrientation(LinearLayout.VERTICAL);
			    layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			    
			    TextView titleView = new TextView(this);
		        LinearLayout.LayoutParams lparams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
		        titleView.setLayoutParams(lparams);
		        titleView.setTextSize(20);
		        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		        titleView.setText(entry.getName() + ":");
		        
		        TextView valueView = new TextView(this);
		        valueView.setLayoutParams(lparams);
		        valueView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
		        valueView.setTextSize(20);
		        valueView.setText(entry.getValue());
		        
		        rowLayout.addView(titleView);
		        rowLayout.addView(valueView);
		        
		        layout.addView(rowLayout);
		    }
		}
		setContentView(layout);
//		
//		
//	    LinearLayout layout = new LinearLayout(this);
//	    layout.setOrientation(LinearLayout.HORIZONTAL);
//	    layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//	    
//	    LinearLayout rowLayout = new LinearLayout(this);
//	    layout.setOrientation(LinearLayout.VERTICAL);
//	    layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//	    
//	    
//	    
//	    TextView titleView = new TextView(this);
//        LinearLayout.LayoutParams lparams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
//        titleView.setLayoutParams(lparams);
//        titleView.setTextSize(20);
//        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
//        titleView.setText("Hallo Welt!");
//        
//        TextView valueView = new TextView(this);
////        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        valueView.setLayoutParams(lparams);
//        valueView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
//        valueView.setTextSize(20);
//        valueView.setText("Hallo Welt2!");
//        
//        rowLayout.addView(titleView);
//        rowLayout.addView(valueView);
//        
//        layout.addView(rowLayout);
//        
//        
//        setContentView(layout);
	    
//	    String productIdStr = message;
//	    int productId = getProductId(productIdStr);
//	    
	    
//	    
//	    if(productId == -1){
//	    	TextView invalidTextView = (TextView)findViewById(R.id.textView_invalid);
//	    	invalidTextView.setVisibility(View.VISIBLE);
//	    	
//	    	TextView view1 = (TextView)findViewById(R.id.textView1);
//	    	view1.setVisibility(View.GONE);
//	    	TextView view2 = (TextView)findViewById(R.id.textView2);
//	    	view2.setVisibility(View.GONE);
////	    	TextView view3 = (TextView)findViewById(R.id.textView3);
////	    	view3.setVisibility(View.GONE);
//	    	TextView view4 = (TextView)findViewById(R.id.textView4);
//	    	view4.setVisibility(View.GONE);
//	    	TextView view5 = (TextView)findViewById(R.id.textView5);
//	    	view5.setVisibility(View.GONE);
//	    	return;
//	    }
//	    Product product = products[productId];
//	    // Create the text view
//	    //name
//	    TextView nameTextView = (TextView)findViewById(R.id.textview_name);
//	    nameTextView.setText(product.getName());
//	    //duty
//	    TextView dutyTextView = (TextView)findViewById(R.id.textView_duty);
//	    dutyTextView.setText(product.getDuty());
//	    //origin
//	    TextView originTextView = (TextView)findViewById(R.id.textView_origin);
//	    originTextView.setText(product.getPlace());
//	    //date
//	    TextView dateTextView = (TextView)findViewById(R.id.textView_date);
//	    dateTextView.setText(product.getDate());
//	    
//
//	    // Set the text view as the activity layout
////	    setContentView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_result, menu);
		return true;
	}

}
