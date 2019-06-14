package com.huawen.huawenface.sdk.act;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.huawen.huawenface.R;
import com.huawen.huawenface.sdk.Constants;
import com.huawen.huawenface.sdk.Global;

public class FaceSettingActivity extends BaseActivity {
    private AppCompatEditText mClubeInputView;
    private AppCompatEditText mDeviceInputView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_setting);
        mClubeInputView = (AppCompatEditText) findViewById(R.id.dialog_input_group_id);
        mDeviceInputView = (AppCompatEditText) findViewById(R.id.dialog_input_device_id);
        String clubeId = Global.getSpString(Constants.Sp.SP_GROUP_ID, "");
        String deviceId = Global.getSpString(Constants.Sp.SP_DEVICE_ID, "");
        mClubeInputView.setText(clubeId);
        mDeviceInputView.setText(deviceId);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.setSpString(Constants.Sp.SP_GROUP_ID,mClubeInputView.getText().toString());
                Global.setSpString(Constants.Sp.SP_DEVICE_ID,mDeviceInputView.getText().toString());
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
