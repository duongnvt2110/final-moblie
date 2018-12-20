package com.example.duong.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import  android.support.design.widget.NavigationView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.AppCompatImageView;
import com.example.duong.myapplication.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.bumptech.glide.Glide;
import com.example.duong.myapplication.ReviewList;

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

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
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
//            Demo Data
//            ReviewList review1 = new ReviewList("ToBi","My favorite drink is coffe and this place is very good for mixed coffee",5);
//            ReviewList review2 = new ReviewList("Hoàng","The same message in above",5);
//            ReviewList review3 = new ReviewList("Hoàng","The same message in above",5);
//            data.add(review1);
//            data.add(review2);
//            data.add(review3);
            customAdaper = new CustomAdapterRecyclerViewReview(data);
            // Attach the adapter to the recyclerview to populate items
            rvContacts.setAdapter(customAdaper);
//            // Set layout manager to position the items
            rvContacts.setLayoutManager(new LinearLayoutManager(this));

////            create Listview and add into ReviewList
//            ListView lvReviewList= (ListView) findViewById(R.id.dt_location);
//            ArrayList<ReviewList> arrContact = new ArrayList<>();
//
////            set content custom adapter
//            customAdaper = new CustomAdapterReview(this,R.layout.detail_location,arrContact);
//            lvReviewList.setAdapter(customAdaper);

//            declacre btn txt and catch event click
            final TextView tvView= (TextView) findViewById(R.id.btn_addreview);

            tvView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent Intent = new Intent(ViewDetailItem.this, AddReview.class);
                    startActivity(Intent);
                }
            });

//          Load Image online with Gilde Line 240 Class MyViewPageAdapter.
//            Glide.with(getApplicationContext())
//                .load("https://www.upsieutoc.com/images/2018/12/20/coffee_home.jpg")
//                .into(imageSider1);

//          Image Slider
//            Declare ViewPager
            viewPager = (ViewPager) findViewById(R.id.view_pager);
//            Link Url
            String[] url ={"https://www.upsieutoc.com/images/2018/12/20/coffee_home.jpg","https://www.upsieutoc.com/images/2018/12/20/coffee_home.jpg"};
            myImageSlider = new ImageSlider(url);
            viewPager.setAdapter(myImageSlider);
//            Set time loop
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
