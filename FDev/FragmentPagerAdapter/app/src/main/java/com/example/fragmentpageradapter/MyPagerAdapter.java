package com.example.fragmentpageradapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

public static class MyPagerAdapter extends FragmentStatePagerAdapter {
    // Return a different fragment for position based on additional state tracked in a member variable
    @Override
    public Fragment getItem(int position) {
        // For a given position, return two different potential fragments based on a condition
    }

    // Force a refresh of the page when a different fragment is displayed
    @Override
    public int getItemPosition(Object object) {
        // this method will be called for every fragment in the ViewPager
        if (object instanceof SomePermanantCachedFragment) {
            return POSITION_UNCHANGED; // don't force a reload
        } else {
            // POSITION_NONE means something like: this fragment is no longer valid
            // triggering the ViewPager to re-build the instance of this fragment.
            return POSITION_NONE;
        }
    }
}
