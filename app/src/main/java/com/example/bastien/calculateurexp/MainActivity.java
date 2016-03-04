package com.example.bastien.calculateurexp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                rootView = inflater.inflate(R.layout.activity_calcul_degat, container, false);

                 final EditText chance, attaque, bonus, de, carac, technique;
                 Button calcul;
                 final TextView degat;

                degat = (TextView) rootView.findViewById(R.id.degat);
                chance = (EditText) rootView.findViewById(R.id.editTextChance);
                attaque = (EditText) rootView.findViewById(R.id.editTextAttaque);
                bonus = (EditText) rootView.findViewById(R.id.editTextBonus);
                de = (EditText) rootView.findViewById(R.id.editTextDe);
                technique = (EditText) rootView.findViewById(R.id.editTextTechnique);
                carac = (EditText) rootView.findViewById(R.id.editTextCarac);

                calcul = (Button) rootView.findViewById(R.id.button2);
                calcul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int degatTotal = 0, chanceVal = 0, attaqueVal = 0, deVal = 0, bonusVal = 0, caracVal = 0, techniqueVal = 0;
                        if (!chance.getText().toString().equals("")) {
                            chanceVal = Integer.valueOf(chance.getText().toString());
                        }

                        if (!attaque.getText().toString().equals("")) {
                            attaqueVal = Integer.valueOf(attaque.getText().toString());
                        }

                        if (!de.getText().toString().equals("")) {
                            deVal = Integer.valueOf(de.getText().toString());
                        }

                        if (!bonus.getText().toString().equals("")) {
                            bonusVal = Integer.valueOf(bonus.getText().toString());
                        }

                        if (!carac.getText().toString().equals("")) {
                            caracVal = Integer.valueOf(carac.getText().toString());
                        }

                        if (!technique.getText().toString().equals("")) {
                            techniqueVal = Integer.valueOf(technique.getText().toString());
                        }


                        if (deVal == 1 | deVal == 2) {
                            degat.setText("0");
                        } else {
                            if (deVal == 20 || deVal == 19) {
                                degatTotal += deVal * 2;
                            } else if ((deVal == 18 || deVal == 17) && chanceVal == 2) {
                                degatTotal += deVal * 2;
                            } else {
                                degatTotal += deVal;
                            }
                            degatTotal += attaqueVal + bonusVal + (techniqueVal * caracVal /100);
                            degat.setText(String.valueOf(degatTotal));
                        }


                    }
                });
            }
            return rootView;
        }
    }
}
