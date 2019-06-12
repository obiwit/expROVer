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

public class EditBookmarkDialogFragment extends DialogFragment {

    private EditText name;
    private String editName;

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public interface EditBookmarkDialogListener {
        public void onEditDialogPositiveClick(DialogFragment dialog, String s);

        public void onEditDialogNeutralClick(DialogFragment dialog);

        public void onEditDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    EditBookmarkDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (EditBookmarkDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout._dialog_add_bookmark, null));


        builder.setMessage("Edit bookmark")
//                .setTitle("Title")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        listener.onEditDialogPositiveClick(EditBookmarkDialogFragment.this, name.getText().toString());


                    }
                })
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        listener.onEditDialogNeutralClick(EditBookmarkDialogFragment.this);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onEditDialogNegativeClick(EditBookmarkDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        name = (EditText) getDialog().findViewById(R.id.dialog_add_bookmark_bookmark_name);
        name.setText(editName);

        // So the "titlebar" doesn't appear with the fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}

