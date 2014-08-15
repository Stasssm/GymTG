package bodyhelper;

import java.util.ArrayList;
import java.util.UUID;

import com.example.gym.R;
import android.content.Context;
import android.util.Log;


public class BodyLab {

	private ArrayList<BodyPart> mBp;
	private static BodyLab sBodyLab;
	//private Context mAppContext;
	//private DataBase db;

	private BodyLab(Context appContext) {
		// mAppContext = appContext;
		//db = new DataBase(appContext);
		//SQLiteDatabase sqlbase = db.getWritableDatabase();
		mBp = new ArrayList<BodyPart>();
		String[] bodyp_link = new String[] { "back.html","chest.html","legs.html","shoulders.html","triceps.html","biceps.html","calve.html","vol.html"};
		int[] drawables = { R.drawable.back_xhdpi,R.drawable.chest_xhdpi,R.drawable.legs_xhdpi,R.drawable.shoulder_xhdpi,R.drawable.tric_xhdpi,R.drawable.bic_xhdpi,
			R.drawable.calve_xhdpi,R.drawable.vol_xhdpi	};
		for(int i=0;i<drawables.length;i++){
			BodyPart bp =new BodyPart(drawables[i], bodyp_link[i]);
			mBp.add(bp);
		}
	}

	public static BodyLab get(Context c) {
		if (sBodyLab == null) {
			sBodyLab = new BodyLab(c.getApplicationContext());
		}
		return sBodyLab;
	}

	public ArrayList<BodyPart> getBp() {
		return mBp;
	}

	public BodyPart getBp(UUID id) {
		for (BodyPart c : mBp) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}
	
}
