package com.example.mobilesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilesafe.Constant;
import com.example.mobilesafe.R;
import com.example.mobilesafe.Utils.SPUtils;
import com.example.mobilesafe.Utils.SystemInfoUtils;

public class SplashActivity extends Activity {

    private RelativeLayout rootLayout;
    private TextView versionNameTextView;
    private TextView versionNumTextView;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
        initAnimation();
        initEvent();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.rl_splash_root);
        versionNameTextView = findViewById(R.id.tv_splash_version_name);
        versionNumTextView = findViewById(R.id.tv_splash_version_code);
    }

    private void initAnimation() {
        /** 0 开始旋转角度
         * 360 结束角度
         * Animation.RELATIVE_TO_SELF 锚点,相对于自己的某个位置固定
         * 0.5f 在X轴方向说明锚点相对于自己的宽度的一半去旋转
         */
        // 旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画作用时间
        rotateAnimation.setDuration(2000);
        //补间动画,又称影子动画
        //固定动画做完的位置
        rotateAnimation.setFillAfter(true);
        /**
         * 0.0f 在X轴方向从0放大到1
         */
        //比例动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        /**0.0f 完全透明
         * 1.0f 全不透
         */
        //透明度渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        /**
         * false 不共用动画插入器
         */
        //动画容器,用来转载所有的动画,然后统一运行
        animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        rootLayout.startAnimation(animationSet);
    }

    private void initData() {
        try {
            String appCode = SystemInfoUtils.getVersionCode(getApplicationContext()) + "";
            String versionName = SystemInfoUtils.getVersionName(getApplicationContext());
            versionNumTextView.setText(appCode);
            versionNameTextView.setText(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            //动画一启动就被回调的方法
            @Override
            public void onAnimationStart(Animation animation) {
                if (SPUtils.getBoolean(getApplicationContext(), Constant.UPDATE)) {



                } else {
                    startHome();
                }

            }
            //动画做完之后的回调
            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //检查版本更新的方法
    /**
     * @called 当需要版本更新的时候在调用
     */
    private void checkVersion() {
        //在子线程中检查是否需要更新
        new Thread(){
            public void run(){
//                readURLData();
            }
        }.start();
    }

    private void startHome() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
