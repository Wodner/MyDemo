package wu.mydemo.function.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.mydemo.main.MainActivity;
import wu.mydemo.R;
import wu.mydemo.app.Constant;
import wu.mydemo.utils.SPUtils;

/**
 * 登录界面
 * Created by Administrator on 2016/12/20.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.edit_mobile)
    TextInputEditText editMobile;
    @Bind(R.id.account_layout)
    TextInputLayout accountLayout;
    @Bind(R.id.edit_pwd)
    TextInputEditText editPwd;
    @Bind(R.id.pwd_layout)
    TextInputLayout pwdLayout;
    @Bind(R.id.btn_login)
    Button btnLogin;

    private Context mContext;

    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        mContext = LoginActivity.this;
        editMobile.setText(SPUtils.get(mContext,Constant.SP_MOBILE,"").toString());
        editPwd.setText(SPUtils.get(mContext,Constant.SP_PASSWORD,"").toString());
        editMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                accountLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pwdLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        startSignIn();
    }

    private void startSignIn() {
        mobile = editMobile.getText().toString().trim();
        password = editPwd.getText().toString().trim();
        if(TextUtils.isEmpty(mobile) || mobile.length() !=11){
            accountLayout.setError("请输入有效的手机号");
            return;
        }
        if(TextUtils.isEmpty(password)){
            pwdLayout.setError("密码不能为空");
            return;
        }
        if(!Constant.mobile.equals(mobile)){
            accountLayout.setError("手机号码不正确");
            return;
        }
        if(!Constant.password.equals(password)){
            pwdLayout.setError("密码不正确");
            return;
        }
        SPUtils.put(mContext,Constant.SP_MOBILE,mobile);
        SPUtils.put(mContext,Constant.SP_PASSWORD,password);
        MainActivity.startAction(LoginActivity.this);
        finish();
    }
}
