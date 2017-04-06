package bigi.randomfilms;

import bigi.randomfilms.POJO.Example;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bigi on 06.04.2017.
 */

public interface InterfFilm {
    
    @GET("movie/{movie_id}?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU")
    Call<Example> listRepos(@Path("movie_id") int groupId);
}
