package bigi.randomfilms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import bigi.randomfilms.POJO2.GenreJanr;
import bigi.randomfilms.POJOrandom.ResultRandom;

/**
 * Created by bigi on 07.04.2017.
 */

public class ListV extends ArrayAdapter<GenreJanr> {
    private List<GenreJanr> list;
    Context context;
    private LayoutInflater layoutInflater;


    public ListV(Context context, List<GenreJanr> list2) {
        super(context,0,list2);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        list = list2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GenreJanr getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final VieHolder VH;

        if(view==null){
            View v = layoutInflater.inflate(R.layout.listview_aktiv, viewGroup,false);
            VH = VieHolder.create((RelativeLayout) v);
            view.setTag(VH);
        } else {
            VH = (VieHolder) view.getTag();
        }
        GenreJanr zapol = getItem(i);
        VH.textView.setText(zapol.getName());
        VH.textView2.setText(zapol.getId());

        return VH.rootView;
   }
   private GenreJanr Zapol(int i){

       return (GenreJanr) getItem(i);
   }
   private static class VieHolder {
       public final RelativeLayout rootView;
       public final TextView textView;
       public final TextView textView2;


       private VieHolder(RelativeLayout rootView, TextView textView, TextView textView2) {
           this.rootView = rootView;
           this.textView = textView;
           this.textView2 = textView2;
       }
        public static VieHolder create(RelativeLayout rootView){
            TextView textView = (TextView) rootView.findViewById(R.id.textView);
            TextView textView2 = (TextView) rootView.findViewById(R.id.textView2);
            return new VieHolder(rootView,textView,textView2);
        }

   }
}
