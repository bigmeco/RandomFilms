
package bigi.randomfilms.POJO2;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Callback;

public class ExampleJanr {

    @SerializedName("genres")
    @Expose
    private List<GenreJanr> genres = new ArrayList<GenreJanr>();

    public List<GenreJanr> getListbuku() {
        return genres;
    }

    public List<GenreJanr> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreJanr> genres) {
        this.genres = genres;
    }

}
