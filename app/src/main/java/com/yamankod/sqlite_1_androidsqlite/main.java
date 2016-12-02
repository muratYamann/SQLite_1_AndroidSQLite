package com.yamankod.sqlite_1_androidsqlite;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class main extends Activity {
	private SQLiteDatabase db;
	final static int PERMISSIONS_REQUEST_CODE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//call Perssion
		getPermissionReadExternalStorage();
		getPermissionWriteExternalStorage();

		try {

		//	db = SQLiteDatabase.openDatabase("/mnt/sdcard/xxx", null, SQLiteDatabase.CREATE_IF_NECESSARY);

			db = this.openOrCreateDatabase("arkadaslarim", MODE_PRIVATE,null);
			Toast.makeText(getApplicationContext(), db.getPath()+ "DB Acildi!", Toast.LENGTH_LONG).show();
			db.close();
		        } catch (Exception e) {
		        	Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		        }
	}




	public void getPermissionWriteExternalStorage() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {


			if (shouldShowRequestPermissionRationale(
					Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
			}
			requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					PERMISSIONS_REQUEST_CODE);
		}
	}
	public void getPermissionReadExternalStorage() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {


			if (shouldShowRequestPermissionRationale(
					Manifest.permission.READ_EXTERNAL_STORAGE)) {
			}
			requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
					PERMISSIONS_REQUEST_CODE);
		}
	}




	@Override
	public void onRequestPermissionsResult(int requestCode,
										   @NonNull String permissions[],
										   @NonNull int[] grantResults) {
		if (requestCode == PERMISSIONS_REQUEST_CODE) {
			if (grantResults.length == 1 &&
					grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
			}
		} else {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}





}