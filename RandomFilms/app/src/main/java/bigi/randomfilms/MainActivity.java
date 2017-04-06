package bigi.randomfilms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    KenBurnsView kbv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kbv = (KenBurnsView) findViewById(R.id.backdrop_path);

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500/5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg")
                .placeholder(R.drawable.fon)
                .error(R.drawable.fon)
                .into(kbv);
    }
}
