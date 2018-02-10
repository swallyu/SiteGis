package com.mavoic.sitegis.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mavoic.core.activitys.BaseActivity;
import com.mavoic.sitegis.MainActivity;
import com.mavoic.sitegis.R;
import com.mavoic.sitegis.data.LoginData;
import com.mavoic.sitegis.databinding.ActivityLoginBinding;
import com.mavoic.sitegis.databinding.ActivityLoginInputBinding;

import java.util.Random;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnLogin;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    private ActivityLoginBinding binding ;

    private LoginData loginData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,getLayoutId());
        initView();

        loginData = new LoginData();
        loginData.setUserName("test");

        binding.setModel(loginData);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.colorPrimary).fitsSystemWindows(true).init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    private void initView() {
        mBtnLogin = binding.mainBtnLogin;
        progress = binding.layoutProgress;
        mInputLayout = binding.inputLayout.getRoot();


        mName = binding.inputLayout.inputLayoutName;
        mPsw =binding.inputLayout.inputLayoutPsw;

        mBtnLogin.setOnClickListener(this);
        binding.mainBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginData.setUserName("user"+ System.currentTimeMillis());

            }
        });
    }

    /**
     * 输入框的动画效果
     *
     * @param view
     *            控件
     * @param w
     *            宽
     * @param h
     *            高
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    public class JellyInterpolator extends LinearInterpolator {
        private float factor;

        public JellyInterpolator() {
            this.factor = 0.15f;
        }

        @Override
        public float getInterpolation(float input) {
            return (float) (Math.pow(2, -10 * input)
                    * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }
    }

    private int LOGIN_WAIT=0x1001;
    private boolean isLogin=false;

    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(LoginActivity.class.getName(),msg.what+":");
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
        }

    };
    @Override
    public void onClick(View view) {

        Log.d(LoginActivity.class.getName(),"测试日志信息");
        Log.d(LoginActivity.class.getName(),loginData.getUserName());
        Log.d(LoginActivity.class.getName(),loginData.getPassword());
//        if(isLogin){
//            isLogin=false;
//            recovery();
//        }else{
//            isLogin=true;
//            // 计算出控件的高与宽
//            mWidth = mBtnLogin.getMeasuredWidth();
//            mHeight = mBtnLogin.getMeasuredHeight();
//            // 隐藏输入框
//            mName.setVisibility(View.INVISIBLE);
//            mPsw.setVisibility(View.INVISIBLE);
//
//            inputAnimator(mInputLayout, mWidth, mHeight);
//        }
//
//
//        handler.sendEmptyMessageDelayed(LOGIN_WAIT,2000);


    }

    /**
     * 恢复初始状态
     */
    private void recovery() {
        progress.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mName.setVisibility(View.VISIBLE);
        mPsw.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f,1f );
        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
    }
}
