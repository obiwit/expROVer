package ua.deti.exprover;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeleteDialogFragment extends DialogFragment {

    public interface ConfirmDeleteBookmarkDialogListener {
        public void onConfirmDeletePositiveClick(DialogFragment dialog);

        public void onConfirmDeleteNegativeClick(DialogFragment dialog);
    }

    ConfirmDeleteBookmarkDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ConfirmDeleteBookmarkDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement ConfirmDeleteBookmarkDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete bookmark?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        listener.onConfirmDeletePositiveClick(ConfirmDeleteDialogFragment.this);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onConfirmDeleteNegativeClick(ConfirmDeleteDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();

        // So the "titlebar" doesn't appear with the fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}


