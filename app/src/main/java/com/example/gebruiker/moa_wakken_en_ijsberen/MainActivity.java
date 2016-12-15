package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentSettings.setSettingsListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    CustomPager customPager;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        customPager = new CustomPager(getSupportFragmentManager(), MainActivity.this);
        //viewPager.setAdapter(new CustomPager(getSupportFragmentManager(),MainActivity.this));
        viewPager.setAdapter(customPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Controle of naam in sharedpreff staat, zoniet, dialog tonen voor naam invoer
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name", "");
        if(TextUtils.isEmpty(name))
            NameDialog();
    }

    //Methode voor fragments om van tab te wisselen
    public void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }

    @Override
    public void setSettings(int seconds, int dices, boolean pinguins) {
        selectPage(0); // Zet actieve fragment in viewpager naar FragmentGame
        int id = viewPager.getCurrentItem();
        //Haalt actieve fragment uit de viewpager (FragmentGame)
        FragmentGame frag = (FragmentGame) getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.viewpager+":"+id);
        frag.UpdateGame(seconds, dices, pinguins);
    }

    //Dialog methode om naam in te voeren, slaat naam op in sharedpreffs
    public void NameDialog(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.AppTheme);
        final EditText edittext = new EditText(this);
        alert.setMessage(getString(R.string.Naam));
        alert.setTitle(getString(R.string.Welkom));
        alert.setView(edittext);
        alert.setCancelable(false);
        alert.setPositiveButton(getString(R.string.Accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(!TextUtils.isEmpty(edittext.getText())){
                    //Slaat naam op in sharedpreff
                    preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name",edittext.getText().toString());
                    editor.apply();
                }
                else{
                    Toast.makeText(getApplicationContext(), getString(R.string.naamError), Toast.LENGTH_SHORT).show();
                    NameDialog();
                }
            }
        });
        alert.show();
    }

}
