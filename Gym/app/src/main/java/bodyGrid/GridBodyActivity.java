package bodyGrid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import bodyhelper.BodyFragment;
import bodyhelper.BodyLab;
import bodyhelper.BodyPart;
import bodyhelper.FragmentActivity;

import com.example.gym.R;

public class GridBodyActivity extends Activity {
	GridView gridView;
	ArrayList<BodyPart> gridArray = new ArrayList<BodyPart>();
	 CustomGridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_body_layout);

		//set grid view item
		gridArray = BodyLab.get(getApplicationContext()).getBp();


		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.list_bodyp, gridArray);
		gridView.setAdapter(customGridAdapter);

	gridView.setOnItemClickListener(new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			BodyPart c = gridArray.get(position);
			Intent i = new Intent(getApplicationContext(), FragmentActivity.class);
			i.putExtra(BodyFragment.EXTRA_ID, c.getId());
			startActivity(i);
			
		}
		
	});
	}
	
}

