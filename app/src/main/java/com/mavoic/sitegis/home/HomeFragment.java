package com.mavoic.sitegis.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mavoic.sitegis.R;
import com.mavoic.core.activitys.BaseFragment;

/**
 * Created by lzhyu on 2018-2-8.
 */

public class HomeFragment extends BaseFragment {
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    private TextView view;


    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);  //获取参数
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fra, container, false);
        view = root.findViewById(R.id.textView);
        view.setText(mParam);



        return root;
    }
    public static HomeFragment newInstance(String str) {
        HomeFragment frag = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);   //设置参数
        return frag;
    }


    public void setText(int title_home) {
        view.setText(title_home);
    }
}
