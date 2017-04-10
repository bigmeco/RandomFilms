package bigi.randomfilms;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Main2Activity extends AppCompatActivity implements ListView.OnItemClickListener {
    private static final String URL = "https://api.themoviedb.org/3/";

    private static final String ID = "id";
    private static final String NAMA = "nama";
    //listview untuk menampilkan data
    private ListView listView;
    //varibel books bertipe List dan List tersebut berdasarkan objek Listbuku
    private List<GenreJanr> books ;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listViewBuku);

        getBuku();
        listView.setOnItemClickListener(this);

//        InterfFilm API = RetrZap();
//        Call<ExampleJanr> call = API.EJanr();
//        call.enqueue(new Callback<ExampleJanr>() {
//                         @Override
//                         public void onResponse(Call<ExampleJanr> call, Response<ExampleJanr> response) {
//
//                             contactList = response.body().getGenres();
//
//                             exampleJanr = new ListV(Main2Activity.this,contactList);
//                             listView.setAdapter(exampleJanr);
//                         }
//
//                         @Override
//                         public void onFailure(Call<ExampleJanr> call, Throwable t) {
//
//                         }
//                     });
    }

    private void getBuku() {
        //Ketika Aplikasi mengambil data kita akan melihat progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please Wait..",false,false);

        InterfFilm API = RetrZap();
        Call<ExampleJanr> call = API.EJanr();
        call.enqueue(new Callback<ExampleJanr>() {
            @Override
            public void onResponse(Call<ExampleJanr> call, Response<ExampleJanr> response) {
                loading.dismiss();
                List<GenreJanr> buku = response.body().getListbuku();
                books = buku;
                showList();
            }

            @Override
            public void onFailure(Call<ExampleJanr> call, Throwable t) {

            }
        });
    }

    private void showList() {
        //String array untuk menyimpan nama semua nama buku
        String[] items = new String[books.size()];

        for (int i = 0; i < books.size(); i++) {

            items[i] = books.get(i).getName();
        }
        //Membuat Array Adapter for listview
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_aktiv, items);


        //setting adapter untuk listview
        listView.setAdapter(adapter);

    }

    public InterfFilm RetrZap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfFilm service = retrofit.create(InterfFilm.class);
        return service;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    // ListV listV = new ListV(this,initData());
    //  listView.setAdapter(listV);

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

}
