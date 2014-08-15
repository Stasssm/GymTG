package bodyGrid;

import java.util.ArrayList;

import com.example.gym.R;

import bodyhelper.BodyPart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomGridViewAdapter extends ArrayAdapter<BodyPart> {
	Context context;
	int layoutResourceId;
	ArrayList<BodyPart> data = new ArrayList<BodyPart>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<BodyPart> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = ((Activity) context).getLayoutInflater().inflate(
					R.layout.list_bodyp, null);
		}
		BodyPart c = getItem(position);

		ImageView image =(ImageView) convertView.findViewById(R.id.bodyp);
		image.setImageResource(c.getBp_drawable());
		return convertView;

	}

}