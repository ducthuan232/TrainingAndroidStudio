package com.example.trainningandroidstudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DoVatAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    List<DoVat> listDoVat;

    public DoVatAdapter(MainActivity context, int layout, List<DoVat> listDoVat) {
        this.context = context;
        this.layout = layout;
        this.listDoVat = listDoVat;
    }

    @Override
    public int getCount() {
        return listDoVat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtMota;
        ImageView imDoVat,imTrash;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTen = (TextView) convertView.findViewById(R.id.txtTen);
            holder.txtMota = (TextView) convertView.findViewById(R.id.txtMoTa);
            holder.imDoVat = (ImageView) convertView.findViewById(R.id.imDoVat);
            holder.imTrash = (ImageView) convertView.findViewById(R.id.imageTrash);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        DoVat doVat = listDoVat.get(position);
        holder.txtTen.setText(doVat.getTen());
        holder.txtMota.setText(doVat.getMota());
        // chuyen byte[] sang bitmap
        byte[] hinhDoVat = doVat.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhDoVat,0, hinhDoVat.length);
        holder.imDoVat.setImageBitmap(bitmap);
        holder.imTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.xoaCongViec(doVat.getId());
            }
        });



        return convertView;
    }
}
