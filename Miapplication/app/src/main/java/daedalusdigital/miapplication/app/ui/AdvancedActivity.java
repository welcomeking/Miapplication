package daedalusdigital.miapplication.app.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import daedalusdigital.miapplication.app.R;
import daedalusdigital.miapplication.app.ui.drawerItems.CustomPrimaryDrawerItem;
import daedalusdigital.miapplication.app.ui.drawerItems.CustomUrlPrimaryDrawerItem;
import daedalusdigital.miapplication.app.ui.drawerItems.OverflowMenuDrawerItem;

public class AdvancedActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private IProfile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(daedalusdigital.miapplication.app.R.layout.activity_sample);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(daedalusdigital.miapplication.app.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(daedalusdigital.miapplication.app.R.string.drawer_item_home);

        // Create a few sample profile
        profile = new ProfileDrawerItem().withName("Welcome King").withEmail("Welcome@outlook.com").withIcon(getResources().getDrawable(daedalusdigital.miapplication.app.R.drawable.profile));
        // Create the AccountHeader
        buildHeader(false, savedInstanceState);

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        //here we use a customPrimaryDrawerItem we defined in our sample app
                        //this custom DrawerItem extends the PrimaryDrawerItem so it just overwrites some methods
                        new OverflowMenuDrawerItem().withName(R.string.drawer_item_mail).withMenu(daedalusdigital.miapplication.app.R.menu.fragment_menu).withOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Toast.makeText(AdvancedActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }).withIcon(GoogleMaterial.Icon.gmd_email),
                        new CustomPrimaryDrawerItem().withBackgroundRes(R.drawable.background_gradient).withName(daedalusdigital.miapplication.app.R.string.drawer_item_news).withIcon(FontAwesome.Icon.faw_newspaper_o),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_jobs).withIcon(FontAwesome.Icon.faw_briefcase),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_varsity).withIcon(FontAwesome.Icon.faw_building),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_tool).withIcon(FontAwesome.Icon.faw_plug),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_discover).withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_desktop),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_help).withDescription("This is a description").withIcon(FontAwesome.Icon.faw_database).withEnabled(false),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_contact).withSelectedIconColor(Color.RED).withIconTintingEnabled(true).withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(daedalusdigital.miapplication.app.R.color.material_drawer_dark_primary_text)).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false)
                ) // add the items we want to use with our Drawer
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                        //if the back arrow is shown. close the activity
                        AdvancedActivity.this.finish();
                        //return true if we have consumed the event
                        return true;
                    }
                })
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10),
                        new SecondaryDrawerItem().withName(daedalusdigital.miapplication.app.R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github)
                )
                .withSavedInstance(savedInstanceState)
                .build();


    }

    /**
     * small helper method to reuse the logic to build the AccountHeader
     * this will be used to replace the header of the drawer with a compact/normal header
     *
     * @param compact
     * @param savedInstanceState
     */
    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(daedalusdigital.miapplication.app.R.drawable.header)
                .withCompactStyle(compact)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(daedalusdigital.miapplication.app.R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman").withEmail("batman@gmail.com").withIcon(getResources().getDrawable(daedalusdigital.miapplication.app.R.drawable.profile5));
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(daedalusdigital.miapplication.app.R.menu.main, menu);
        menu.findItem(R.id.menu_1).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_sort).color(Color.WHITE).actionBar());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case daedalusdigital.miapplication.app.R.id.menu_2:
                //show the arrow icon
                result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                return true;
            case daedalusdigital.miapplication.app.R.id.menu_3:
                //show the hamburger icon
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
                return true;
            case daedalusdigital.miapplication.app.R.id.menu_4:
                //we want to replace our current header with a compact header
                //build the new compact header
                buildHeader(true, null);
                //set the view to the result
                result.setHeader(headerResult.getView());
                //set the drawer to the header (so it will manage the profile list correctly)
                headerResult.setDrawer(result);
                return true;
            case daedalusdigital.miapplication.app.R.id.menu_5:
                //we want to replace our current header with a normal header
                //build the new compact header
                buildHeader(false, null);
                //set the view to the result
                result.setHeader(headerResult.getView());
                //set the drawer to the header (so it will manage the profile list correctly)
                headerResult.setDrawer(result);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
