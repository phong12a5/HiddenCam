package phong.hiddencam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import phong.hiddencam.R;

import phong.hiddencam.ui.ViewPagerAdapter;

import java.io.File;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load and apply the saved language preference before anything else
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        String savedLanguageCode = prefs.getString(Constants.LANGUAGE_KEY, Locale.getDefault().getLanguage());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //force dark theme even on light themed devices

        // Check if current locale is Pashto
        if (getResources().getConfiguration().locale.getLanguage().equals("ps")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(1);

        // This is the path for the osmdroid tile cache
        File osmdroidBasePath = new File(getCacheDir().getAbsolutePath(), "osmdroid");
        File osmdroidTileCache = new File(osmdroidBasePath, "tiles");
        org.osmdroid.config.Configuration.getInstance().setOsmdroidBasePath(osmdroidBasePath);
        org.osmdroid.config.Configuration.getInstance().setOsmdroidTileCache(osmdroidTileCache);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}