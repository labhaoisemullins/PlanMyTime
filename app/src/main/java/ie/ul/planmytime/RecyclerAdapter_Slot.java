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

public class RecyclerAdapter_Slot extends RecyclerView.Adapter<RecyclerAdapter_Slot.ViewHolder> {

    private List<Slot> slots;
    private Activity activity;

    public RecyclerAdapter_Slot(Activity activity, List<Slot> slots) {
        this.slots = slots;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_slot, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_Slot.ViewHolder viewHolder, int position) {

        // Setting the data to view holder elements
        viewHolder.imageView.setImageResource(R.drawable.icons_timetable);
        viewHolder.module.setText(slots.get(position).getModule());
        viewHolder.location.setText(slots.get(position).getLocation());
        viewHolder.slotType.setText(slots.get(position).getType());
        viewHolder.slotTime.setText(slots.get(position).getTime());

        viewHolder.container.setOnClickListener(onClickListener(position));

    }

    private void setDataToView(ImageView image, TextView module, TextView location, TextView slotType, TextView slotTime, int position) {
        image.setImageResource(R.drawable.icons_timetable);

        module.setText(slots.get(position).getModule());
        location.setText(slots.get(position).getLocation());
        slotType.setText(slots.get(position).getType());
        slotTime.setText(slots.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return (null != slots ? slots.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.item_recycler_slot);
                dialog.setTitle("Slot Details");
                dialog.setCancelable(true); // dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                TextView module = (TextView) dialog.findViewById(R.id.module);
                TextView location = (TextView) dialog.findViewById(R.id.location);
                TextView slotType = (TextView) dialog.findViewById(R.id.slotType);
                TextView slotTime = (TextView) dialog.findViewById(R.id.slotTime);

                setDataToView(image, module, location, slotType, slotTime, position);

                dialog.show();
            }
        };
    }

    // View holder to display each RecyclerView item
    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView module;
        private TextView location;
        private TextView slotType;
        private TextView slotTime;
        private View container;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            module = (TextView) view.findViewById(R.id.module);
            location = (TextView) view.findViewById(R.id.location);
            slotType = (TextView) view.findViewById(R.id.slotType);
            slotTime = (TextView) view.findViewById(R.id.slotTime);
            container = view.findViewById(R.id.card_view);
        }
    }
}