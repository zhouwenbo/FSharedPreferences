package com.fheebiy.fsp.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fheebiy.fsp.FspManager;


public class RemoteMainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mStringEdit;
    private Button mStringGetBtn;
    private Button mStringPutBtn;
    private Button mStringRemoveBtn;

    private EditText mIntEdit;
    private Button mIntGetBtn;
    private Button mIntPutBtn;
    private Button mIntRemoveBtn;

    private EditText mBooleanEdit;
    private Button mBooleanGetBtn;
    private Button mBooleanPutBtn;
    private Button mBooleanRemoveBtn;

    private EditText mFloatEdit;
    private Button mFloatGetBtn;
    private Button mFloatPutBtn;
    private Button mFloatRemoveBtn;

    private EditText mLongEdit;
    private Button mLongGetBtn;
    private Button mLongPutBtn;
    private Button mLongRemoveBtn;

    private TextView mResultTv;
    private ISpConfig mISpConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bindListener();
        mISpConfig = FspManager.getAppPref(ISpConfig.class);
    }

    private void initView() {
        mStringEdit = findViewById(R.id.edit_string);
        mStringGetBtn = findViewById(R.id.btn_string_get);
        mStringPutBtn = findViewById(R.id.btn_string_put);
        mStringRemoveBtn = findViewById(R.id.btn_string_remove);

        mIntEdit = findViewById(R.id.edit_int);
        mIntGetBtn = findViewById(R.id.btn_int_get);
        mIntPutBtn = findViewById(R.id.btn_int_put);
        mIntRemoveBtn = findViewById(R.id.btn_int_remove);

        mBooleanEdit = findViewById(R.id.edit_bool);
        mBooleanGetBtn = findViewById(R.id.btn_bool_get);
        mBooleanPutBtn = findViewById(R.id.btn_bool_put);
        mBooleanRemoveBtn = findViewById(R.id.btn_bool_remove);

        mFloatEdit = findViewById(R.id.edit_float);
        mFloatGetBtn = findViewById(R.id.btn_float_get);
        mFloatPutBtn = findViewById(R.id.btn_float_put);
        mFloatRemoveBtn = findViewById(R.id.btn_float_remove);

        mLongEdit = findViewById(R.id.edit_long);
        mLongGetBtn = findViewById(R.id.btn_long_get);
        mLongPutBtn = findViewById(R.id.btn_long_put);
        mLongRemoveBtn = findViewById(R.id.btn_long_remove);

        mResultTv = findViewById(R.id.result_tv);

        findViewById(R.id.remote_btn).setVisibility(View.GONE);
    }


    private void bindListener() {
        mStringGetBtn.setOnClickListener(this);
        mStringPutBtn.setOnClickListener(this);
        mStringRemoveBtn.setOnClickListener(this);

        mIntGetBtn.setOnClickListener(this);
        mIntPutBtn.setOnClickListener(this);
        mIntRemoveBtn.setOnClickListener(this);

        mBooleanGetBtn.setOnClickListener(this);
        mBooleanPutBtn.setOnClickListener(this);
        mBooleanRemoveBtn.setOnClickListener(this);

        mFloatGetBtn.setOnClickListener(this);
        mFloatPutBtn.setOnClickListener(this);
        mFloatRemoveBtn.setOnClickListener(this);

        mLongGetBtn.setOnClickListener(this);
        mLongPutBtn.setOnClickListener(this);
        mLongRemoveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_string_get:
                String userId = mISpConfig.getUserId().get();
                mResultTv.setText(userId);
                break;
            case R.id.btn_string_put:
                String userIdS = mStringEdit.getText().toString();
                mISpConfig.getUserId().put(userIdS);
                break;
            case R.id.btn_string_remove:
                mISpConfig.getUserId().remove();
                break;
            case R.id.btn_int_get:
                int index = mISpConfig.getIndex().get();
                mResultTv.setText(index + "");
                break;
            case R.id.btn_int_put:
                try {
                    int indexS = Integer.parseInt(mIntEdit.getText().toString());
                    mISpConfig.getIndex().put(indexS);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_int_remove:
                mISpConfig.getIndex().remove();
                break;
            case R.id.btn_bool_get:
                boolean b = mISpConfig.isSuccess().get();
                mResultTv.setText(b + "");
                break;
            case R.id.btn_bool_put:
                boolean bs = Boolean.parseBoolean(mBooleanEdit.getText().toString());
                mISpConfig.isSuccess().put(bs);
                break;
            case R.id.btn_bool_remove:
                mISpConfig.isSuccess().remove();
                break;
            case R.id.btn_float_get:
                float fs = mISpConfig.getPrice().get();
                mResultTv.setText(fs + "");
                break;
            case R.id.btn_float_put:
                mISpConfig.getPrice().put(Float.parseFloat(mFloatEdit.getText().toString()));
                break;
            case R.id.btn_float_remove:
                mISpConfig.getPrice().remove();
                break;
            case R.id.btn_long_get:
                mResultTv.setText(mISpConfig.getTime().get() + "");
                break;
            case R.id.btn_long_put:
                mISpConfig.getTime().put(Long.parseLong(mLongEdit.getText().toString()));
                break;
            case R.id.btn_long_remove:
                mISpConfig.getTime().remove();
                break;
            default:

        }


    }
}
