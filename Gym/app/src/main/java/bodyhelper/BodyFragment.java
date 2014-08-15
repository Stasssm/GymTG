package bodyhelper;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


import com.example.gym.R;


public class BodyFragment extends Fragment {

	public static final String EXTRA_ID = "bodyhelper_id";

	private BodyPart mBp;
	private WebView mWv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID bpId = (UUID) getActivity().getIntent().getSerializableExtra(
				EXTRA_ID);
		mBp = BodyLab.get(getActivity()).getBp(bpId);
		// mCrime = new Crime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.body_part_fragment, parent, false);
		mWv = (WebView)v.findViewById(R.id.bp_web);
		mWv.loadUrl("file:///android_asset/html/bp/"+mBp.getViewPath());
		return v;
	}
}
