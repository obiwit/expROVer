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

public class AddBookmarkDialogFragment extends DialogFragment {

    private EditText name;


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String s);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
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


        builder.setMessage("Add bookmark")
//                .setTitle("Title")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        System.out.println("name = " + name);
                        System.out.println("name.getText() = " + name.getText());
                        System.out.println("name.getText().toString() = " + name.getText().toString());
                        listener.onDialogPositiveClick(AddBookmarkDialogFragment.this, name.getText().toString());


                    }
                })
//                .setNeutralButton("R.string.neutral", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onDialogNegativeClick(AddBookmarkDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        name = (EditText) getDialog().findViewById(R.id.dialog_add_bookmark_bookmark_name);

        // So the "titlebar" doesn't appear with the fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}

