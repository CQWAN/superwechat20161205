package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by LPP on 2017/3/31.
 */

public class PersonalCenterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.em_fragment_personal_center, container, false);
        initView();
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    @OnClick(R.id.ivMySettings)
    public void settings() {
        MFGT.gotoSettings(getActivity());
    }

    @OnClick(R.id.ivMyMoney)
    public void money() {
        MFGT.gotoMoney(getActivity());
    }
}
