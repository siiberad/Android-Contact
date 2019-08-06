package com.siiberad.sproutdigital;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.siiberad.sproutdigital.adapter.CustomAdapter;
import com.siiberad.sproutdigital.model.ContactModel;
import com.siiberad.sproutdigital.model.DataModel;
import com.siiberad.sproutdigital.network.GetDataService;
import com.siiberad.sproutdigital.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private List<DataModel> dataModelList;

    DataModel dataModel;

    ProgressDialog progressDialog;

    private TextView txt_firstname, txt_email;
    private ImageView img_avatar, img_dot, img_phone, img_message;
    private LinearLayout linear_name, linear_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        dataModelList = new ArrayList<>();
        getItem();
    }



    public void getItem(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ContactModel> call = service.getAll();
        call.enqueue(new Callback<ContactModel>() {
            @Override
            public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                generateDataList(response.body().getData());
                dataModelList.addAll(response.body().getData());
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ContactModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(ArrayList<DataModel> serverList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        customAdapter = new CustomAdapter(this, serverList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onClick(View v) {
        dataModel = (DataModel) v.getTag();
        dataModel.setSelected(!dataModel.isSelected());

        txt_firstname = v.findViewById(R.id.txt_firstname);
        txt_email = v.findViewById(R.id.txt_email);
        img_avatar = v.findViewById(R.id.img_avatar);
        img_dot = v.findViewById(R.id.img_dot);
        img_phone = v.findViewById(R.id.img_phone);
        img_message = v.findViewById(R.id.img_message);
        linear_name = v.findViewById(R.id.linear_name);
        linear_full = v.findViewById(R.id.linear_full);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        if(dataModel.isSelected()){
            img_avatar.setColorFilter(filter);
            linear_name.setBackgroundColor(Color.parseColor("#9d47ff"));
            img_dot.setVisibility(View.GONE);
            img_phone.setVisibility(View.VISIBLE);
            img_message.setVisibility(View.VISIBLE);
            txt_firstname.setTextColor(Color.WHITE);
            txt_email.setTextColor(Color.WHITE);
        }else{
            img_avatar.setColorFilter(Color.parseColor("#9d47ff"), PorterDuff.Mode.LIGHTEN);
            linear_name.setBackgroundColor(Color.WHITE);
            img_dot.setVisibility(View.VISIBLE);
            img_phone.setVisibility(View.GONE);
            img_message.setVisibility(View.GONE);
            txt_firstname.setTextColor(Color.BLACK);
            txt_email.setTextColor(Color.BLACK);
        }
    }
}
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
