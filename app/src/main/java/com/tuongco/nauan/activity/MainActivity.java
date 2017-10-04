package com.tuongco.nauan.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tuongco.nauan.R;
import com.tuongco.nauan.adapter.LoaimonanAdapter;
import com.tuongco.nauan.model.Loaimonan;
import com.tuongco.nauan.ultil.CheckConnect;
import com.tuongco.nauan.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<Loaimonan> mangloaimonan1;
    LoaimonanAdapter loaimonanAdapter1;
    ArrayList<Loaimonan> mangloaimonan;
    LoaimonanAdapter loaimonanAdapter;
    String id;
    String tenloai="";
    String hinhanh="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnect.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiMonAn();
        } else
        {
            CheckConnect.ShowToast(getApplicationContext(),"Check Your Internet");
            finish();
        }

    }

    private void GetDuLieuLoaiMonAn() {
        RequestQueue requestqueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest json = new JsonArrayRequest(Server.DuongDanLoaiMonAn, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response !=null)
                {
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonobject = response.getJSONObject(i);
                            id= jsonobject.getString("maloai");
                            tenloai=jsonobject.getString("tenloai");
                            hinhanh=jsonobject.getString("hinhanh");
                            mangloaimonan1.add(new Loaimonan(id,tenloai,hinhanh));
                            loaimonanAdapter1.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast(getApplicationContext(),error.toString());
            }
        });
        requestqueue.add(json);
    }

    private void ActionViewFlipper() {
        ArrayList<Integer> mangquangcao= new ArrayList<>();
        mangquangcao.add(R.drawable.hinh1);
        mangquangcao.add(R.drawable.hinh2);
        mangquangcao.add(R.drawable.hinh3);
        for(int i=0;i<mangquangcao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerView =  (RecyclerView) findViewById(R.id.recyclerView);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listView = (ListView) findViewById(R.id.listviewhome);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaimonan1 = new ArrayList<>();
        loaimonanAdapter1 = new LoaimonanAdapter(mangloaimonan1,getApplicationContext());
        listView.setAdapter(loaimonanAdapter1);
        mangloaimonan1 = new ArrayList<>();
        mangloaimonan1.add(0,new Loaimonan("0","HOME","https://image.flaticon.com/icons/png/512/25/25694.png"));
        loaimonanAdapter1 = new LoaimonanAdapter(mangloaimonan1,getApplicationContext());
        listView.setAdapter(loaimonanAdapter1);
        ;
    }
}
