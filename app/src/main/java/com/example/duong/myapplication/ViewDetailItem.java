package com.example.duong.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duong.myapplication.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewDetailItem  extends AppCompatActivity {
    CustomAdapterRecyclerViewReview customAdaper;
    private LayoutInflater layoutInflater;
    List<ReviewList> data =new ArrayList<>();
    private ViewPager viewPager;
    private ImageSlider myImageSlider;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private Context context;
    private String locationId;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        locationId = id;
        this.getDetailLocation(id);
    }

    private void displayData(LocationList location){
        final TextView txtName= (TextView) findViewById(R.id.txt_name);
        final TextView txtAddress = (TextView) findViewById(R.id.txt_address);
        final RatingBar rbRating = (RatingBar) findViewById(R.id.rb_rating);
        final TextView txtHour= (TextView) findViewById(R.id.txt_hour);
        txtName.setText(location.getName());
        txtAddress.setText(location.getAddress());
        rbRating.setRating(location.getRating());
        txtHour.setText(location.getOpeningTime());
        if(location.getImages().length > 0){
            viewPager = (ViewPager) findViewById(R.id.view_pager);
            myImageSlider = new ImageSlider(location.getImages());
            viewPager.setAdapter(myImageSlider);
        }
        else{
            viewPager = (ViewPager) findViewById(R.id.view_pager);
//            Link Url
            String[] url ={"https://i0.wp.com/www.ghiencaphe.com/wp-content/uploads/2016/11/14907180_1793403247600360_1149741883844579018_n.jpg?resize=625%2C417&ssl=1","https://i0.wp.com/www.ghiencaphe.com/wp-content/uploads/2016/11/11026026_1634385786835441_1646900694864188830_n.jpg?resize=625%2C625&ssl=1"};
            myImageSlider = new ImageSlider(url);
            viewPager.setAdapter(myImageSlider);
        }
        ArrayList<ReviewList> reviews = location.getReviews();
        customAdaper.addAll(reviews);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_location);
//          USING RecyclerView
            // Lookup the recyclerview in activity layout
            RecyclerView rvContacts = (RecyclerView) findViewById(R.id.dt_location);
            // Create adapter passing in the sample user data
            List<ReviewList> data = new ArrayList<>();
            customAdaper = new CustomAdapterRecyclerViewReview(data);
            // Attach the adapter to the recyclerview to populate items
            rvContacts.setAdapter(customAdaper);
//            // Set layout manager to position the items
            rvContacts.setLayoutManager(new LinearLayoutManager(this));

//            declacre btn txt and catch event click
            final TextView tvView= (TextView) findViewById(R.id.btn_addreview);

            tvView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent Intent = new Intent(ViewDetailItem.this, AddReview.class);
                    Intent.putExtra("locationId",locationId);
                    startActivity(Intent);
                }
            });

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new MyTimeTask(),2000,4000);
    }


//    function support load review
    private void getDetailLocation(String id)
    {
        FecthDetailLocationTask task = new FecthDetailLocationTask();
        task.execute(id);
    }


    public class FecthDetailLocationTask extends AsyncTask<String, Void, LocationList>
    {

        @Override
        protected LocationList doInBackground(String... params) {
            String id = params[0];
            return QueryUtils.fetchDetailLocationData(id);
        }

        @Override
        protected void onPostExecute(LocationList data) {
            if(data == null)
            {
                return;
            }
            else
            {
                Log.d("Detail", data.toString());
                displayData(data);

            }

        }
    }
// Class Support Image Slider Loop
    public class MyTimeTask extends TimerTask {
        @Override
        public void run(){
            ViewDetailItem.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(0);
                    }else{
                        viewPager.setCurrentItem(1);
                    }
                }
            });
        }
    }


        private int getItem(int i) {
            return viewPager.getCurrentItem() + i;
        }


        //  viewpager change listener
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };

        /**
         * Making notification bar transparent
         */
        private void changeStatusBarColor() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }

}
