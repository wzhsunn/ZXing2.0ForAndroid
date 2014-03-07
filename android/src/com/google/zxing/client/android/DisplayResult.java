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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_display_result);
		
		Intent intent = getIntent();
		ProductNode node = (ProductNode)intent.getSerializableExtra(CaptureActivity.PRODUCT_NODE_SERIALE);

		LinearLayout layout = new LinearLayout(this);
	    
	    layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    
		if(node == null){
			layout.setOrientation(LinearLayout.VERTICAL);
			TextView qrDataView = new TextView(this);
	        LinearLayout.LayoutParams lparams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        qrDataView.setLayoutParams(lparams);
	        qrDataView.setTextSize(20);
	        qrDataView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
	        String qrData = intent.getStringExtra(CaptureActivity.QR_CODE_DATA);
	        qrDataView.setText(qrData);
	        layout.addView(qrDataView);
	        
			TextView titleView = new TextView(this);
	        titleView.setLayoutParams(lparams);
	        titleView.setTextSize(20);
	        titleView.setTextAppearance(this, android.R.attr.textAppearanceLarge);
	        titleView.setText("无效识别码!");
	        layout.addView(titleView);
		}
		else{
			layout.setOrientation(LinearLayout.HORIZONTAL);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_result, menu);
		return true;
	}

}
