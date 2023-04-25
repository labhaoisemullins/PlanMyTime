package ie.ul.planmytime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ie.ul.planmytime.R;

public class Homepage extends AppCompatActivity {

    FirebaseAuth mAuth;

    private Toolbar toolbar;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupUIViews();
        //initToolbar();
        setupListView();
    }


    private void setupUIViews() {
        toolbar = (Toolbar) findViewById(R.id.MainToolbar);
        listView = (ListView) findViewById(R.id.MainLV);
    }

   /*  private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Plan My Time");
    } */

    private void setupListView() {
        String[] title = getResources().getStringArray(R.array.Main);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // My Timetable
                    case 0: {
                        Intent intent = new Intent(Homepage.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    // My Projects
                    case 1: {
                        Intent intent = new Intent(Homepage.this, ProjectDetail.class);
                        startActivity(intent);
                        break;
                    }
                    // UL Campus Guide
                    case 2: {
                        Intent intent = new Intent(Homepage.this, MapsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        break;
                    }
                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {
        // define all variables
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title;
        private String[] titleArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title) {
            mContext = context;
            titleArray = title;
            layoutInflater = LayoutInflater.from(context); // allows us to put diff. layouts into a view
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.home_page_single_item, null);
            }

            title = (TextView) convertView.findViewById(R.id.MainTV);
            imageView = (ImageView) convertView.findViewById(R.id.MainIV);

            title.setText(titleArray[position]);

            // set up drawable images
            if (titleArray[position].equalsIgnoreCase("My Timetable")) {
                imageView.setImageResource(R.drawable.icons_timetable);
            } else if (titleArray[position].equalsIgnoreCase("My Projects")) {
                imageView.setImageResource(R.drawable.icons_projects);
            } else if (titleArray[position].equalsIgnoreCase("UL Campus Guide")) {
                imageView.setImageResource(R.drawable.icons_map);
            }
            return convertView;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();

            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();
        }
    }
}



