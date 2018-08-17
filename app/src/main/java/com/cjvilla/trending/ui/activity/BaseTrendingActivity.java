package com.cjvilla.trending.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.cjvilla.trending.R;


public class BaseTrendingActivity extends AppCompatActivity {
	protected void createToolbar(boolean showUp) {
		Toolbar tool = findViewById(R.id.toolbar);
		if (tool != null) {
			setSupportActionBar(tool);
			ActionBar ab = getSupportActionBar();
			ab.setDisplayHomeAsUpEnabled(showUp);
		}
	}

	protected void showTitle(String title) {
		if (TextUtils.isEmpty(title)) {
			getSupportActionBar().setDisplayShowTitleEnabled(false);
		} else {
			getSupportActionBar().setDisplayShowTitleEnabled(true);
			getSupportActionBar().setTitle(title);
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
