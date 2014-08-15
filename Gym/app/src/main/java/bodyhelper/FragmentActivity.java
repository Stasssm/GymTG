package bodyhelper;

import android.support.v4.app.Fragment;

public class FragmentActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new BodyFragment();
	}

}
