package angelhack.jobber;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec homespec = tabHost.newTabSpec("Home");
        homespec.setIndicator("Home", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent homeIntent = new Intent(this, Home.class);
        homespec.setContent(homeIntent);
        
        // Tab for Songs
        TabSpec profilespec = tabHost.newTabSpec("Profile");
        // setting Title and Icon for the Tab
        profilespec.setIndicator("Profile", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent profileIntent = new Intent(this, Profile.class);
        profilespec.setContent(profileIntent);
        
        // Tab for Videos
        TabSpec searchspec = tabHost.newTabSpec("Search");
        searchspec.setIndicator("Search", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent searchIntent = new Intent(this, Search.class);
        searchspec.setContent(searchIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(homespec); // Adding photos tab
        tabHost.addTab(profilespec); // Adding songs tab
        tabHost.addTab(searchspec); // Adding videos tab
    }
}