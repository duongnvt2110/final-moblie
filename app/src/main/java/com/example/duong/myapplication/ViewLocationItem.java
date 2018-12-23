package com.example.duong.myapplication;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duong.myapplication.utils.QueryUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ViewLocationItem  extends AppCompatActivity {
    CustomAdapter customAdaper = null;
    protected String simpleFileName = "token.txt";
    private int type = 1;
    private int page = 1;
    private boolean flag_loading = false;
    private boolean end = false;

    @Override
    protected void onStart() {
        super.onStart();
        this.updateLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout)
        {
            LogoutTask logoutTask = new LogoutTask();
            logoutTask.execute();
        }
        return super.onOptionsItemSelected(item);
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ListView locationList = (ListView) findViewById(R.id.lv_location);
        final ImageView imgLocation = (ImageView) findViewById(R.id.image);
        ArrayList<LocationList> arrContact = new ArrayList<>();

        int id = getResources().getIdentifier("coffee", "drawable", getPackageName());

        customAdaper = new CustomAdapter(ViewLocationItem.this, R.layout.location_view, arrContact);
        locationList.setAdapter(customAdaper);


//        Event Click
        // Set an item click listener for ListView
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                LocationList itemChosen = (LocationList) parent.getItemAtPosition(position);

                Intent Intent = new Intent(ViewLocationItem.this, ViewDetailItem.class);
                view.setSelected(true);
                Intent.putExtra("hour","Monday 7 a.m-10 p.m");
                Intent.putExtra("name","Local Coffee");
                Intent.putExtra("id",itemChosen.getId());
                startActivity(Intent);
            }
        });

        locationList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
                {
                    if(end == false){
                        if(flag_loading == false)
                        {
                            flag_loading = true;
                            Toast.makeText(ViewLocationItem.this, "Load more", Toast.LENGTH_SHORT).show();
                            loadMore();
                        }
                    }

                }

            }
        });

        final Button btn_new = (Button) findViewById(R.id.btn_new);
        btn_new.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateLocation();
            }
        });
        final Button btn_near = (Button) findViewById(R.id.btn_near);
        btn_near.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ViewLocationItem.this, "Button near", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateLocation()
    {
        FecthLocationTask task = new FecthLocationTask();
        task.execute();
    }


    private void loadMore(){
        page ++;
        if(type == 1){
            FecthLocationByPageTask fecthLocationByPageTask = new FecthLocationByPageTask();
            fecthLocationByPageTask.execute(page);
        }


    }

    public class FecthLocationByPageTask extends AsyncTask<Integer, Void, List<LocationList>>
    {


        @Override
        protected List<LocationList> doInBackground(Integer... params) {
            return QueryUtils.fetchLocationDataByPage(params[0]);
        }

        @Override
        protected void onPostExecute(List<LocationList> data) {
            if(data == null || data.isEmpty())
            {
                end = true;
                return;
            }
            else
            {
                customAdaper.addAll(data);

            }

        }
    }


    public class FecthLocationTask extends AsyncTask<String, Void, List<LocationList>>
    {


        @Override
        protected List<LocationList> doInBackground(String... params) {
            return QueryUtils.fetchLocationData();
        }

        @Override
        protected void onPostExecute(List<LocationList> data) {
            if(data == null || data.isEmpty())
            {
                return;
            }
            else
            {
                page = 1;
                customAdaper.clear();
                customAdaper.addAll(data);

            }

        }
    }

    private void updateLocationByDistance()
    {

        FecthLocationByDistanceTask task = new FecthLocationByDistanceTask();
        task.execute();
    }

    public class FecthLocationByDistanceTask extends AsyncTask<String, Void, List<LocationList>>
    {

        @Override
        protected List<LocationList> doInBackground(String... params) {
            return QueryUtils.fetchLocationByDistanceData(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(List<LocationList> data) {
            if(data == null || data.isEmpty())
            {
                return;
            }
            else
            {
                page = 1;
                customAdaper.clear();
                customAdaper.addAll(data);

            }

        }
    }



    public class LogoutTask extends AsyncTask<String, Void, Integer>
    {


        @Override
        protected Integer doInBackground(String... params) {
            return deleteToken(simpleFileName);
        }

        @Override
        protected void onPostExecute(Integer status) {
            if(status !=1){
                Toast.makeText(ViewLocationItem.this, "Đăng xuất thất bại", Toast.LENGTH_SHORT).show();

            }
            else{
                startActivity(new Intent(ViewLocationItem.this, Home.class));
            }
        }
    }

    public int deleteToken(String fileName){

        try{
            File dir = getFilesDir();
            File file = new File(dir, fileName);
            boolean deleted = file.delete();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}
