package com.example.sahaya;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class Guard_QR_Fragment extends Fragment {
    ImageView qr;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_guard__q_r_, container, false);
        qr=view.findViewById(R.id.qr_guard);
        sp=getActivity().getSharedPreferences("qr", Context.MODE_PRIVATE);
        String code=sp.getString("phone","")+"//"+sp.getString("pass","")+"//"+sp.getString("user","");
        try {
            qr.setImageBitmap(getQr(code));
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        return view;
    }


    private Bitmap getQr(String Value) throws WriterException {


        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(
                    Value,
                    BarcodeFormat.QR_CODE,
                    900, 900, null
            );
            BarcodeEncoder b=new BarcodeEncoder();

            Bitmap bitmap = b.createBitmap(bitMatrix);

        return bitmap;
        } catch (IllegalArgumentException Illegalargumentexception) {
return null;
        }

    }
}