package com.taipei.happyZoo.page;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.taipei.happyZoo.R;
import com.taipei.happyZoo.custom.anim.DetailsTransition;
import com.taipei.happyZoo.custom.base.BaseFragment;
import com.taipei.happyZoo.page.house.HouseFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Fade;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            addFragment(HouseFragment.newInstance());
        }

    }

    public void replaceFragment(View animationView, Fragment changeFragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (animationView != null) {
            String transitionName = ViewCompat.getTransitionName(animationView);
            if(transitionName != null){
                changeFragment.setSharedElementEnterTransition(new DetailsTransition());
                changeFragment.setReturnTransition(new Fade());
                changeFragment.setSharedElementReturnTransition(new DetailsTransition());
                fragmentTransaction.addSharedElement(animationView, transitionName);
            }
        } else {
            fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_from_right, R.anim.slide_out_left,
                    R.anim.slide_in_from_right, R.anim.slide_out_left);
        }
        fragmentTransaction.replace(R.id.flFragmentContent, changeFragment);
        fragmentTransaction.addToBackStack(changeFragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void addFragment(Fragment changeFragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_left,
                R.anim.slide_in_from_right, R.anim.slide_out_left);
        fragmentTransaction.add(R.id.flFragmentContent, changeFragment);
        fragmentTransaction.addToBackStack(changeFragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fmClose = getSupportFragmentManager();
        BaseFragment currentFragment = (BaseFragment) fmClose.findFragmentById(R.id.flFragmentContent);
        if (currentFragment != null && !currentFragment.canGoBack()) {
            return;
        }

        //再按一次退出程式
        if (fmClose.getBackStackEntryCount() <= 1) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, R.string.common_text_leave_app, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
        } else {
            super.onBackPressed();
        }
    }

}
