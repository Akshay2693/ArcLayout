package xyz.sahildave.arclayout.demo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import xyz.sahildave.arclayout.ArcLayout;
import xyz.sahildave.arclayout.ArcLayoutSettings;

public class TabbedActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_ARC_POSITION = "arg_position";
        private static final String ARG_ARC_TYPE = "arg_type";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(final int position, String arcType) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_ARC_TYPE, arcType);
            args.putInt(ARG_ARC_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_ARC_TYPE));

            ArcLayout arcLayout = (ArcLayout) rootView.findViewById(R.id.arc_layout);

            ArcLayoutSettings setting = arcLayout.getSettings();
            int position = getArguments().getInt(ARG_ARC_POSITION);
            switch (position) {
                case 0:
                    setting.setIsCropConvex(true);
                    setting.setIsDirectionBottom(false);
                    break;
                case 1:
                    setting.setIsCropConvex(false);
                    setting.setIsDirectionBottom(false);
                    break;
                case 2:
                    setting.setIsCropConvex(true);
                    setting.setIsDirectionBottom(true);
                    break;
                case 3:
                    setting.setIsCropConvex(false);
                    setting.setIsDirectionBottom(true);
                    break;
            }
            arcLayout.setSettings(setting);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        static final String TOP_CONVEX = "Top Convex";
        static final String TOP_CONCAVE = "Top Concave";
        static final String BOTTOM_CONVEX = "Bottom Convex";
        static final String BOTTOM_CONCAVE = "Bottom Concave";

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String arcType = "";
            switch (position) {
                case 0:
                    arcType = TOP_CONVEX;
                    break;
                case 1:
                    arcType = TOP_CONCAVE;
                    break;
                case 2:
                    arcType = BOTTOM_CONVEX;
                    break;
                case 3:
                    arcType = BOTTOM_CONCAVE;
                    break;

            }
            return PlaceholderFragment.newInstance(position, arcType);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TOP_CONVEX;
                case 1:
                    return TOP_CONCAVE;
                case 2:
                    return BOTTOM_CONVEX;
                case 3:
                    return BOTTOM_CONCAVE;
            }
            return null;
        }
    }
}
