package edu.fje.multimedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bloc> mBlocs;
    private Bloc mLastSelectedBloc;

    // Constructor
    public GridAdapter(Context context, List<Bloc> blocs) {
        mContext = context;
        mBlocs = blocs;
    }

    @Override
    public int getCount() {
        return mBlocs.size();
    }

    @Override
    public Object getItem(int position) {
        return mBlocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // Crear una nueva ImageView si no existe una previamente
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        Bloc bloc = mBlocs.get(position);

        imageView.setImageBitmap(bloc.getImageBlock());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GridAdapter", "Bloque clicado: " + bloc.getId());
                if (mLastSelectedBloc == null) {
                    mLastSelectedBloc = bloc;
                } else {
                    intercambiarBloques(mLastSelectedBloc, bloc);
                    mLastSelectedBloc = null;
                }
            }
        });

        return imageView;
    }

    private void intercambiarBloques(Bloc bloc1, Bloc bloc2) {
        int tempId = bloc1.getId();
        Bitmap tempBitmap = bloc1.getImageBlock();

        bloc1.setId(bloc2.getId());
        bloc1.setImageBlock(bloc2.getImageBlock());
        bloc2.setId(tempId);
        bloc2.setImageBlock(tempBitmap);

        notifyDataSetChanged();
    }
}


