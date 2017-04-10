package bigi.randomfilms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import bigi.randomfilms.POJO.Example;
import bigi.randomfilms.POJO2.ExampleJanr;
import bigi.randomfilms.POJO2.GenreJanr;
import bigi.randomfilms.POJOjyrnal.ExampleJyrnal;
import bigi.randomfilms.POJOjyrnal.ResultJyrnal;
import bigi.randomfilms.POJOrandom.ExampleRandom;
import bigi.randomfilms.POJOrandom.ResultRandom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://api.themoviedb.org/3/";
    private static final String Imeg = "https://image.tmdb.org/t/p/w500";
   // private static final String ID = "id";
    private static final String NAMA = "nama";
    //listview untuk menampilkan data
    private ListView listView;
    //varibel books bertipe List dan List tersebut berdasarkan objek Listbuku
    private List<GenreJanr> books ;;
    private static int srtoni, nomer;
    KenBurnsView kbv;
    ImageView imageView;
    TextView title;
    TextView original_title;
    TextView overview;
    TextView release_date;
    EditText editText;
    ImageButton imageButton;
    ImageButton imageButton2;
    Spinner spinner;
    Button bat;
   // ArrayAdapter<String> adapter;
    final Random random = new Random();
    int ID,IDj=0,idJn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kbv = (KenBurnsView) findViewById(R.id.backdrop_path);
        imageView = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.title);
        original_title = (TextView) findViewById(R.id.original_title);
        overview = (TextView) findViewById(R.id.overview);
        release_date = (TextView) findViewById(R.id.release_date);
        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner)findViewById(R.id.spinner);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,);

        getBuku();

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID = Integer.parseInt(editText.getText().toString());
                ResstrPr();
            }
        });

        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IDj==0){
                srtoni = random.nextInt(999) + 1;
                nomer = random.nextInt(19) + 1;
                RandomSbor(srtoni, nomer);
                    System.out.println(IDj);
                } else {
                System.out.println("FFFFFFFFFFFFFFF");
                RandomJan(IDj, idJn);
                    }
            }
        });
    bat = (Button) findViewById(R.id.bat);
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        } );
    }
    private void showList() {
        //String array untuk menyimpan nama semua nama buku
        String[] items = new String[books.size()+1];
        items[0]= "OOOL random";
        for (int i = 1; i < books.size(); i++) {

            items[i] = books.get(i).getName();
        }

        //Membuat Array Adapter for spinner
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, items);

        //setting adapter untuk spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                IDj=i;
                System.out.println(IDj);
                idJn = books.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
    public InterfFilm RetrZap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfFilm service = retrofit.create(InterfFilm.class);
        return service;
    }


    private void ResstrPr() {
        InterfFilm service = RetrZap();
        Call<Example> call = service.listRepos(ID);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Picasso.with(MainActivity.this)
                        .load(Imeg + response.body().getBackdropPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(kbv);

                Picasso.with(MainActivity.this)
                        .load(Imeg + response.body().getPosterPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(imageView);
                title.setText(response.body().getTitle());
                original_title.setText(response.body().getOriginalTitle());
                overview.setText(response.body().getOverview());
                release_date.setText(response.body().getReleaseDate());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void RandomJan(final int srtoni,int  idJn) {
        InterfFilm service = RetrZap();
        Call<ExampleJyrnal> call = service.groupList(idJn);
        call.enqueue(new Callback<ExampleJyrnal>() {
            @Override
            public void onResponse(Call<ExampleJyrnal> call, Response<ExampleJyrnal> response) {
               // System.out.println(idJn+ "fffffffrrrrrre");
                ResultJyrnal F = response.body().getResults().get(srtoni);
                Picasso.with(MainActivity.this)
                        .load(Imeg + F.getBackdropPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(kbv);

                Picasso.with(MainActivity.this)
                        .load(Imeg + F.getPosterPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(imageView);
                title.setText(F.getTitle());
                original_title.setText(F.getOriginalTitle());
                overview.setText(F.getOverview());
                release_date.setText(F.getReleaseDate());
            }

            @Override
            public void onFailure(Call<ExampleJyrnal> call, Throwable t) {

            }
        });
    }

    public void RandomSbor(int srtoni, final int nomer) {
        InterfFilm service = RetrZap();
        Call<ExampleRandom> call = service.RandomZp(srtoni);
        call.enqueue(new Callback<ExampleRandom>() {
            @Override
            public void onResponse(Call<ExampleRandom> call, Response<ExampleRandom> response) {
                ResultRandom F = response.body().getResults().get(nomer);
                Picasso.with(MainActivity.this)
                        .load(Imeg + F.getBackdropPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(kbv);
                Picasso.with(MainActivity.this)
                        .load(Imeg + F.getPosterPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(imageView);
                title.setText(F.getTitle());
                original_title.setText(F.getOriginalTitle());
                overview.setText(F.getOverview());
                release_date.setText(F.getReleaseDate());
                editText.setText(F.getId().toString());
            }

            @Override
            public void onFailure(Call<ExampleRandom> call, Throwable t) {

            }
        });
    }


}



