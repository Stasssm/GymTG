package traindate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.gym.R;

/**
 * Created by Stas on 15.08.2014.
 */
public class DeleteDialog extends DialogFragment {

    MyAdapter adapter;

    DeleteDialog (MyAdapter adapter){
        super();
        this.adapter = adapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.confirm_delete_message)
                .setPositiveButton(R.string.delete_all, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.deleteAll();
                        adapter.notifyDataSetChanged();
                        new PreferenceSaver(getActivity().getApplicationContext()).clear();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
