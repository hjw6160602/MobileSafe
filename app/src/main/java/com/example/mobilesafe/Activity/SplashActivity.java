package com.example.mobilesafe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilesafe.R;

import org.w3c.dom.Text;

public class SplashActivity extends Activity {

    private RelativeLayout rootLayout;
    private TextView versionNameTextView;
    private TextView versionNumView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initData();
        initView();
        initAnimation();
        initEvent();
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.rl_splash_root);
        versionNameTextView = findViewById(R.id.tv_splash_version_name);
        versionNameTextView = findViewById(R.id.tv_splash_version_code);
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
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        rootLayout.startAnimation(animationSet);
    }

    private void initData() {

    }

    private void initEvent() {

    }

}
