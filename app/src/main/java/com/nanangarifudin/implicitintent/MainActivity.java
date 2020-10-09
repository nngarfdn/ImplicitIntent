package com.nanangarifudin.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText, mLocationEditText, mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_editext);
        mLocationEditText = findViewById(R.id.location_editext);
        mShareEditText = findViewById(R.id.share_editext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntent", "openWebsite: Cannot Handle");
        }
    }

    public void openLocation(View view) {
        String location = mLocationEditText.getText().toString();
        Uri addresUri = Uri.parse("geo:0,0?q="+location);
        Intent intent = new Intent(Intent.ACTION_VIEW, addresUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntent", "openWebsite: Cannot Handle");
        }
    }

    public void shareText(View view) {
        String share = mShareEditText.getText().toString();
        ShareCompat.IntentBuilder
                .from(this).setChooserTitle("Share text with :")
                .setText(share).setType("text/plain")
                .startChooser();
    }
}