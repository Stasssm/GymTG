package bodyhelper;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gym.R;

public class BodyListFragment extends ListFragment{

	private ArrayList<BodyPart> mBodyP;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBodyP = BodyLab.get(getActivity()).getBp();

		BodyAdapter adapter = new BodyAdapter(mBodyP);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		BodyPart c = ((BodyAdapter) getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(), FragmentActivity.class);
		i.putExtra(BodyFragment.EXTRA_ID, c.getId());
		startActivity(i);
	}

	private class BodyAdapter extends ArrayAdapter<BodyPart> {
		public BodyAdapter(ArrayList<BodyPart> bp) {
			super(getActivity(), 0, bp);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_bodyp, null);
			}
			BodyPart c = getItem(position);

			ImageView image =(ImageView) convertView.findViewById(R.id.bodyp);
			image.setImageResource(c.getBp_drawable());
			return convertView;
		}
	}
}
