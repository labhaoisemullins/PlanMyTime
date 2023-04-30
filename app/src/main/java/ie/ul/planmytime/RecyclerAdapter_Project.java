package ie.ul.planmytime;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerAdapter_Project extends RecyclerView.Adapter<RecyclerAdapter_Project.ViewHolder> {

    private List<Project> projects;
    private Activity activity1;

    public RecyclerAdapter_Project(Activity activity1, List<Project> projects) {
        this.projects = projects;
        this.activity1 = activity1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup1, int viewType) {

        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity1.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_project, viewGroup1, false);
        ViewHolder viewHolder1 = new ViewHolder(view);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_Project.ViewHolder viewHolder1, int position) {

        // Setting the data to view holder elements
        viewHolder1.imageView.setImageResource(R.drawable.icons_timetable);
        viewHolder1.module.setText(projects.get(position).getProjModule());
        viewHolder1.dueDate.setText(projects.get(position).getDueDate());
        viewHolder1.percentage.setText(projects.get(position).getPercentage());

        viewHolder1.container.setOnClickListener(onClickListener(position));

    }

    private void setDataToView(ImageView image, TextView module, TextView dueDate, TextView percentage, int position) {
        image.setImageResource(R.drawable.icons_timetable);

        module.setText(projects.get(position).getProjModule());
        dueDate.setText(projects.get(position).getDueDate());
        percentage.setText(projects.get(position).getPercentage());
    }

    @Override
    public int getItemCount() {
        return (null != projects ? projects.size() : 1); // check
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity1);
                dialog.setContentView(R.layout.item_recycler_project);
                dialog.setTitle("Project Details" );
                dialog.setCancelable(true); // dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                ImageView image1 = (ImageView) dialog.findViewById(R.id.image1);
                TextView module = (TextView) dialog.findViewById(R.id.module);
                TextView dueDate = (TextView) dialog.findViewById(R.id.dueDate);
                TextView percentage = (TextView) dialog.findViewById(R.id.percentage);

                setDataToView(image1, module, dueDate, percentage, position);

                dialog.show();
            }
        };
    }

    // View holder to display each RecyclerView item
    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView module;
        private TextView dueDate;
        private TextView percentage;
        private View container;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image1);
            module = (TextView) view.findViewById(R.id.module);
            dueDate = (TextView) view.findViewById(R.id.dueDate);
            percentage = (TextView) view.findViewById(R.id.percentage);
            container = view.findViewById(R.id.card_view);
        }
    }
}