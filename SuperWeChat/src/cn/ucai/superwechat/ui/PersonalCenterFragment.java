package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.chat.EMClient;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
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

    @OnClick(R.id.layout_settings)
    public void settings() {
        MFGT.gotoSettings(getActivity());
    }

    @OnClick(R.id.layout_money)
    public void change() {
        RedPacketUtil.startChangeActivity(getActivity());
    }
    @OnClick(R.id.firstBar)
    public void firstBar(){
        MFGT.gotoUserProfile(getActivity());
    }
    @OnClick(R.id.ivMyAvatar)
    public void avatar() {
        startActivity(new Intent(getActivity(), UserProfileActivity.class).putExtra("setting", true)
                .putExtra("username", EMClient.getInstance().getCurrentUser()));
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(((MainActivity)getActivity()).isConflict){
            outState.putBoolean("isConflict", true);
        }else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }
}
