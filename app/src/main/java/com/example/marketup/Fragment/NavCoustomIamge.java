package com.example.marketup.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.marketup.R;

import static androidx.constraintlayout.solver.widgets.ConstraintWidget.INVISIBLE;
import static androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE;


public class NavCoustomIamge extends Fragment {

    RelativeLayout relativeLayoutLogo;
    ImageView imageviewLogo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_coustom_iamge, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        relativeLayoutLogo = (RelativeLayout) view.findViewById(R.id.relativeLayoutLogo);
        imageviewLogo = (ImageView) view.findViewById( R.id.imageviewLogo );

//        if (relativeLayoutLogo.isShown()){
//            relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
//        }
//        else {
//            relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
//        }

        relativeLayoutLogo.setOnClickListener( new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (relativeLayoutLogo.getVisibility() == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    Log.d("aaa","hello");
                }
                else if(relativeLayoutLogo.getVisibility() == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    Log.d("aaa1","hello1");

                }

            }
        } );

    }
}