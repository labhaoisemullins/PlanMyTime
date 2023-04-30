package ie.ul.planmytime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//import android.support.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import ie.ul.planmytime.Utils.LetterImageView;

public class WeekActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;  // to be accessed across all activities
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        setupUIViews();
//        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
//        toolbar = (Toolbar)findViewById(R.id.WeekToolbar);
        listView = (ListView)findViewById(R.id.WeekLV);
        sharedPreferences = getSharedPreferences("DAY", MODE_PRIVATE);
    }
//    private void initToolbar(){
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Week Timetable");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // brings you to the previous activity
//    }

    // OnItemClickListener - used so that each click is unique to activity (ex. Click Monday, opens Mondays' timetable not all days)
    private void setupListView(){
        String[] week = getResources().getStringArray(R.array.Week);

        WeekAdapter adapter = new WeekAdapter(this, R.layout.activity_week_single_item, week);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, MapsActivity.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    case 6: {
                        startActivity(new Intent(WeekActivity.this, Homepage.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Sunday").apply();
                        break;
                    }
                    default:break;  // default case - in the outcome that something isn't defined
                }
            }
        });
    }

    public class WeekAdapter extends ArrayAdapter {

        private int resource;  // List View
        private LayoutInflater layoutInflater;
        private String[] week = new String[]{};  // Week

        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.week = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.LogoIV = (LetterImageView)convertView.findViewById(R.id.LetterIV);
                holder.WeekTV = (TextView)convertView.findViewById(R.id.MainTV);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.LogoIV.setOval(true);  // sets the shape to round oval
            holder.LogoIV.setLetter(week[position].charAt(0));  // sets the letter in logo to first letter of the day
            holder.WeekTV.setText(week[position]);  // displays the day to right of logo

            return convertView;
        }

        class ViewHolder{  // elements in Week activity view
            private LetterImageView LogoIV;  // Letter of day
            private TextView WeekTV;  // Day name
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {   // brings you to the previous activity, back arrow
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
 }