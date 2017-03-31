package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ucai.superwechat.R;

/**
 * Created by LPP on 2017/3/31.
 */

public class PersonalCenterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.em_fragment_personal_center, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {

    }
}
