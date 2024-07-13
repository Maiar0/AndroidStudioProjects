package com.hire.dennisward;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.hire.dennisward.PermissionUtils;

public class ResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Button contactMe = (findViewById(R.id.contact_button));
        contactMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showContactOptionsDialog();
            }
        });

    }

    private void showContactOptionsDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_contact_options, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Contact Me")
                .setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button buttonCall = dialogView.findViewById(R.id.button_call);
        Button buttonEmail = dialogView.findViewById(R.id.button_email);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (PermissionUtils.isCallPhonePermissionGranted(ResumeActivity.this)) {
                    makeCall();
                } else {
                    PermissionUtils.requestCallPhonePermission(ResumeActivity.this);
                }
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (PermissionUtils.isInternetPermissionGranted(ResumeActivity.this)) {
                    sendEmail();
                } else {
                    PermissionUtils.requestInternetPermission(ResumeActivity.this);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.PERMISSION_REQUEST_CODE_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();
            } else {
                Toast.makeText(this, "CALL_PHONE permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PermissionUtils.PERMISSION_REQUEST_CODE_INTERNET) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendEmail();
            } else {
                Toast.makeText(this, "INTERNET permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:4796522525"));
        startActivity(callIntent);
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:Dennis.ward2nd@gmail.com"));
        startActivity(emailIntent);
    }
}