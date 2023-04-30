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

public class MainActivity_Project extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecyclerAdapter_Project adapter1;
    private ArrayList<Project> projArrayList;
    private FloatingActionButton fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_project);
        projArrayList = new ArrayList<>();

        recyclerView1 = (RecyclerView) findViewById(R.id.recyle_view_proj);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);

        recyclerView1.setHasFixedSize(true);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);

        setRecyclerViewData1(); //adding data to array list
        adapter1 = new RecyclerAdapter_Project(this, projArrayList);
        recyclerView1.setAdapter(adapter1);

        fab1.setOnClickListener(onAddingListener());
    }

    private void setRecyclerViewData1() { // Example Projects
        projArrayList.add(new Project("CS4084", "30/04", "50%"));
        projArrayList.add(new Project("CS4106", "03/05", "20%"));
        projArrayList.add(new Project("CS4116", "09/05", "40%"));
    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity_Project.this);
                dialog.setContentView(R.layout.dialog_add_project); //layout for dialog
                dialog.setTitle("Add a new project");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText module = (EditText) dialog.findViewById(R.id.module);
                EditText dueDate = (EditText) dialog.findViewById(R.id.dueDate);
                EditText percentage = (EditText) dialog.findViewById(R.id.percentage);

                // Buttons
                View btnAdd = dialog.findViewById(R.id.btn_add);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);

                // Set the handling event for 2 buttons and spinner
                btnAdd.setOnClickListener(onConfirmListener(module, dueDate, percentage, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText module, final EditText dueDate, final EditText percentage, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Project project = new Project(module.getText().toString().trim(), dueDate.getText().toString().trim(), percentage.getText().toString().trim());

                //adding new object to project arraylist
                projArrayList.add(project);

                //notify data set changed in RecyclerView1 adapter1
                adapter1.notifyDataSetChanged();

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