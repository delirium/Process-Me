/**
 * 
 */
package edu.hrbeu.processme;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author delirium
 * 
 */
public class MainMenuActivity extends Activity implements OnItemClickListener {
    ArrayList<String> mMenuItems;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	this.setContentView(R.layout.main_menu);
	mMenuItems = new ArrayList<String>();
	ListView menuListView = (ListView) findViewById(R.id.MenuListView);
	ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, mMenuItems);
	menuListView.setAdapter(aAdapter);
	menuListView.setOnItemClickListener(this);
	mMenuItems.add("Blur and chanel changing");
	mMenuItems.add("Question 1");
	mMenuItems.add("Question 2");
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	    long arg3) {
	String item = mMenuItems.get(position);
	if (item.equals("Blur and chanel changing")) {
	    Intent i = new Intent(this, ProcessMeBlurActivity.class);
	    startActivity(i);
	}
	if (item.equals("Question 1")) {
	    Intent i = new Intent(this, Question1Activity.class);
	    startActivity(i);
	}
    }
}
