package ie.ul.planmytime;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity_Slot extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter_Slot adapter;
    private ArrayList<Slot> slotArrayList;
    private FloatingActionButton fab;
    private boolean moduleCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slot);
        slotArrayList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setRecyclerViewData(); //adding data to array list
        adapter = new RecyclerAdapter_Slot(this, slotArrayList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(onAddingListener());
    }

    private void setRecyclerViewData() {
        slotArrayList.add(new Slot("CS4084", "CS1044", "Lecture", "11 - 12"));
        slotArrayList.add(new Slot("CS4106", "SG15", "Laboratory", "12 - 2"));
        slotArrayList.add(new Slot("CS4116", "SG15", "Laboratory", "12 - 2"));
    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity_Slot.this);
                dialog.setContentView(R.layout.dialog_add_slot); //layout for dialog
                dialog.setTitle("Add a new slot");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText module = (EditText) dialog.findViewById(R.id.module);
                EditText location = (EditText) dialog.findViewById(R.id.location);
                EditText slotType = (EditText) dialog.findViewById(R.id.slotType);
                EditText slotTime = (EditText) dialog.findViewById(R.id.slotTime);

                // Buttons
                View btnAdd = dialog.findViewById(R.id.btn_add);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);

                // Set the handling event for 2 buttons and spinner
                btnAdd.setOnClickListener(onConfirmListener(module, location, slotType, slotTime, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText module, final EditText location, final EditText slotType, final EditText slotTime, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slot slot = new Slot(module.getText().toString().trim(), location.getText().toString().trim(), slotType.getText().toString().trim(), slotTime.getText().toString().trim());

                //adding new object to arraylist
                slotArrayList.add(slot);

                //notify data set changed in RecyclerView adapter
                adapter.notifyDataSetChanged();

                //close dialog after all
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }
}