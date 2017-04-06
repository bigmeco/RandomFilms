package bigi.randomfilms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import bigi.randomfilms.POJO.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://api.themoviedb.org/3/";
    private static final String Imeg = "https://image.tmdb.org/t/p/w500";
    KenBurnsView kbv;
    ImageView imageView;
    TextView title;
    TextView original_title;
    TextView overview;
    TextView vote_count;
    EditText editText;
    ImageButton imageButton;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kbv = (KenBurnsView) findViewById(R.id.backdrop_path);
        imageView = (ImageView) findViewById(R.id.imageView);
        title =(TextView) findViewById(R.id.title);
        original_title =(TextView) findViewById(R.id.original_title);
        overview =(TextView) findViewById(R.id.overview);
        vote_count =(TextView) findViewById(R.id.vote_count);
        editText =(EditText) findViewById(R.id.editText);


        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ID = Integer.parseInt(editText.getText().toString());
            ResstrPr();

        }
        });






    }

    public void ResstrPr() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfFilm service = retrofit.create(InterfFilm.class);
        Call<Example> call = service.listRepos(ID);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                System.out.println(response.body().getOverview());

                Picasso.with(MainActivity.this)
                        .load(Imeg+response.body().getBackdropPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(kbv);

                Picasso.with(MainActivity.this)
                        .load(Imeg+response.body().getPosterPath())
                        .placeholder(R.drawable.fon)
                        .error(R.drawable.fon)
                        .into(imageView);
                title.setText(response.body().getTitle());
                original_title.setText(response.body().getOriginalTitle());
                overview.setText(response.body().getOverview());

                //  vote_count.setText(response.body().getVoteCount());


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}


