package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {

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
    private Bitmap mImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);
        mImageBitmap = (Bitmap) getIntent().getExtras().get("image");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        getMenuInflater().inflate(R.menu.menu_preview, menu);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreateView(inflater,container,savedInstanceState);
            final int sectionValue = getArguments().getInt(ARG_SECTION_NUMBER);


            View rootView = inflater.inflate(R.layout.fragment_preview, container, false);
            Button postButton = (Button) rootView.findViewById(R.id.post_button);
            EditText editView = (EditText) rootView.findViewById(R.id.edit_preview);
            ImageView image = (ImageView) rootView.findViewById(R.id.photo),
                    mediaIcon = (ImageView) rootView.findViewById(R.id.social_media);
            TextView name = (TextView) rootView.findViewById(R.id.text_preview),
                    errorText = (TextView) rootView.findViewById(R.id.error_text);
            Bundle bundle = getActivity().getIntent().getExtras();
            Bitmap b = (Bitmap) bundle.get("image");

            if (bundle != null && b != null){
                image.setImageBitmap(b);
                image.setVisibility(View.VISIBLE);
            }

            CharSequence fbText = (CharSequence) getActivity().getIntent().getExtras().get("text"),
                    twText = fbText,
                    igText = fbText;

            switch (sectionValue) {
                case 1:
                    setAndSaveText(editView,fbText);
                    mediaIcon.setImageResource(R.drawable.facebook);
                    name.setText("Christopher Bugtong");


                    break;
                case 2:
                    setAndSaveText(editView,twText);
                    mediaIcon.setImageResource(R.drawable.twitter);
                    name.setText("ItsBugsLife");

                    if (editView.getText().length() > 140) {
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText("140 character limit exceeded WILL NOT POST");
                    }
                    break;
                default:
                    setAndSaveText(editView,igText);
                    mediaIcon.setImageResource((R.drawable.instagram));
                    postButton.setVisibility(View.VISIBLE);
                    postButton.setText("Publish!");

                    if (image.getVisibility() != View.VISIBLE) {
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText("Image required - WILL NOT POST");
                    }
            }

            postButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sectionValue == 3) {
                        AppCompatActivity a = (AppCompatActivity) getActivity();
                        a.setResult(RESULT_OK, new Intent());
                        a.finish();

                    } else {
                        ;
                    }
                }
            });

            return rootView;
        }

        private void setAndSaveText(EditText edit, CharSequence text) {
            edit.setText(text);
            text = edit.getText();
        }
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
                    return "FACEBOOK";
                case 1:
                    return "TWITTER";
                case 2:
                    return "INSTAGRAM";
            }
            return null;
        }
    }
}
