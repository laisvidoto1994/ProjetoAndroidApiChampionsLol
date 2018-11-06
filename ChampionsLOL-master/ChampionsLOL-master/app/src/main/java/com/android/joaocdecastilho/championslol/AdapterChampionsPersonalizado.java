package com.android.joaocdecastilho.championslol;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.android.joaocdecastilho.championslol.models.Champion;
import java.util.List;

public class AdapterChampionsPersonalizado extends BaseAdapter
{
    private static final String TAGS = "LAIS";

    private final List<Champion> champions;
    private final Activity atc;

    public AdapterChampionsPersonalizado(List<Champion> champions, Activity atc) {
        this.champions = champions;
        this.atc = atc;
    }

    /*
    *   Quantidade de Posições da lista de dados
    */
    @Override
    public int getCount() {
        return champions.size();
    }

    /*
    *   Posição atual da lista de dados
    */
    @Override
    public Object getItem(int position) {
        return champions.get(position);
    }

    /*
    *   Id da Posição atual da lista de dados
    */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
    *   Tratamento de dados da lista da posição atual
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = atc.getLayoutInflater().inflate(R.layout.activity_itens_champions, parent,false);

        Champion champion = champions.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView txtName    = (TextView)  view.findViewById(R.id.txtName);
        TextView txtTitles  = (TextView)  view.findViewById(R.id.txtTitles);

        Log.i(TAGS, String.format( "%s: %s", champion.getName(), champion.getIcon() ));

        // Picasso - Responsavel por reenderizar imagens da aplicação
        Picasso.get().load( champion.getIcon() ).resize(300, 300).into(imageView);

        txtName.setText(champion.getName());
        txtTitles.setText(champion.getTitle());

        return view;
    }
}
