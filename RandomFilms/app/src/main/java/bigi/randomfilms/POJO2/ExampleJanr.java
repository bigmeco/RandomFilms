
package bigi.randomfilms.POJO2;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Callback;

public class ExampleJanr {

    @SerializedName("genres")
    @Expose
    private ArrayList<GenreJanr> genres = new ArrayList<GenreJanr>();



    public ArrayList<GenreJanr> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreJanr> genres) {
        this.genres = genres;
    }

}
