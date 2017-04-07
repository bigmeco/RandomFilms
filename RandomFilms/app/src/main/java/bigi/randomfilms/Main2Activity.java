package bigi.randomfilms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bigi.randomfilms.POJO2.ExampleJanr;
import bigi.randomfilms.POJO2.GenreJanr;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private static final String URL = "https://api.themoviedb.org/3/";
    private ListView listView;
    private View paretView;
    private ArrayList<GenreJanr> contactList;
    private ListV exampleJanr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contactList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);

        InterfFilm API = RetrZap();
        Call<ExampleJanr> call = API.EJanr();
        call.enqueue(new Callback<ExampleJanr>() {
                         @Override
                         public void onResponse(Call<ExampleJanr> call, Response<ExampleJanr> response) {

                             contactList = response.body().getGenres();

                             exampleJanr = new ListV(Main2Activity.this,contactList);
                             listView.setAdapter(exampleJanr);
                         }

                         @Override
                         public void onFailure(Call<ExampleJanr> call, Throwable t) {

                         }
                     });
       // ListV listV = new ListV(this,initData());
      //  listView.setAdapter(listV);
    }
//    public List<GenreJanr> initData(){
//        List<GenreJanr> list = new ArrayList<GenreJanr>();
//        GenreJanr genreJanr  = new GenreJanr();
//        genreJanr.setName("fgd");
//        genreJanr.setId(34);
//        list.add(genreJanr);
//      //  JanrPol();
//    return list;
//    }

//    private void JanrPol() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        InterfFilm service = retrofit.create(InterfFilm.class);
//        Call<ExampleJanr> call = service.EJanr();
//        call.enqueue(new Callback<ExampleJanr>() {
//            @Override
//            public void onResponse(Call<ExampleJanr> call, Response<ExampleJanr> response) {
//                GenreJanr genreJanr  = new GenreJanr();
//                genreJanr.getName();
//                genreJanr.getId();
//               // list.add(genreJanr);
//
//            }
//
//            @Override
//            public void onFailure(Call<ExampleJanr> call, Throwable t) {
//
//            }
//        });
//
//    }
    public InterfFilm RetrZap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfFilm service = retrofit.create(InterfFilm.class);
        return service;
    }
}
