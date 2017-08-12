package by.test.belarussian.belarussiantests.bizlogic.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import by.test.belarussian.belarussiantests.fragments.QuestionFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final String QUESTION_NUMBER = "questionNumber";
    private final int FRAGMENT_NUMBER = 10;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTION_NUMBER, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_NUMBER;
    }
}
