package com.example.sahaya;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.nio.charset.CharsetEncoder;

public class Stud_QR_Fragment extends Fragment {
ImageView qr;
SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_stud__q_r_, container, false);
        sp=getActivity().getSharedPreferences("qr", Context.MODE_PRIVATE);
        String code=encode(sp.getString("phone",""))+"//"+encode(sp.getString("pass",""))+"//"+encode(sp.getString("user",""));
        qr=view.findViewById(R.id.qr_student);
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

    public String encode(String str){
        String res="";
        str=str.trim();
        for(int i=0;i<str.length();i++){
            int k=str.charAt(i);
            k=k+3;
            char c=(char)k;
            res+=c;
        }
        return res;
    }

}