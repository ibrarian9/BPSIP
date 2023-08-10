package com.app.bpsip.Menu.Magang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;

import android.os.ParcelFileDescriptor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bpsip.Menu.Navbar.Agrostandar;
import com.app.bpsip.CallApi.ApiCall;
import com.app.bpsip.CallApi.ApiEndpoint;
import com.app.bpsip.Menu.Navbar.Dashboard;
import com.app.bpsip.Menu.Navbar.Kontak;
import com.app.bpsip.Menu.Navbar.Layanan;
import com.app.bpsip.Model.ResponseMagang;
import com.app.bpsip.Menu.Navbar.Organisasi;
import com.app.bpsip.R;
import com.app.bpsip.Util.HttpFileUpload;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormMagang extends AppCompatActivity {

    public static final String PDF_DIR = "bpsip";
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    private Boolean upflag = false;
    EditText edNama, edNim, edSekolah, edJurusan, edNoHp, edWaktu;
    ApiEndpoint apiEndpoint;
    Button btnKirim, btnUploadFile;
    String fileName;
    String pdfName;
    File dFile, sfile, file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_magang);

        file = new File(Environment.getExternalStorageDirectory() + "/" + PDF_DIR);
        if (!file.exists()){
            file.mkdirs();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);

        Date currentTime = Calendar.getInstance().getTime();
        pdfName = "Magang" + dateFormat.format(currentTime) + ".pdf" ;

        edNama = findViewById(R.id.magangNama);
        edNim = findViewById(R.id.magangNim);
        edSekolah = findViewById(R.id.magangSekolah);
        edJurusan = findViewById(R.id.magangJurusan);
        edNoHp = findViewById(R.id.magangNoHp);
        edWaktu = findViewById(R.id.magangWaktu);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnUploadFile = findViewById(R.id.uploadFile);
        btnUploadFile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            startActivityForResult(Intent.createChooser(intent,"Pilih File"), 102);
        });

        btnKirim = findViewById(R.id.send);
        btnKirim.setOnClickListener(view -> {

            String mFile = pdfName;
            String nama = edNama.getText().toString();
            String nim = edNim.getText().toString();
            String sekolah = edSekolah.getText().toString();
            String jurusan = edJurusan.getText().toString();
            String noHp = edNoHp.getText().toString();
            String waktu = edWaktu.getText().toString();

            if (nama.equals("")) {
                Toast.makeText(FormMagang.this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (nim.equals("")) {
                Toast.makeText(FormMagang.this, "Nim Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (sekolah.equals("")) {
                Toast.makeText(FormMagang.this, "Sekolah Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (jurusan.equals("")) {
                Toast.makeText(FormMagang.this, "Jurusan Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (noHp.equals("")) {
                Toast.makeText(FormMagang.this, "Nomor Hape Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (waktu.equals("")) {
                Toast.makeText(FormMagang.this, "Waktu Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (mFile == null) {
                Toast.makeText(FormMagang.this, "File Masih Kosong", Toast.LENGTH_SHORT).show();
            } else {
                Call<ResponseMagang> postMagang = apiEndpoint.postMagang(nama, nim, sekolah, jurusan, waktu, noHp, mFile);

                postMagang.enqueue(new Callback<ResponseMagang>() {
                    @Override
                    public void onResponse(Call<ResponseMagang> call, Response<ResponseMagang> response) {
                        if (response.isSuccessful()){
                            new UploadFile().execute();
                            Toast.makeText(getApplicationContext(), "Form Berhasil Di Kirim", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FormMagang.this, Layanan.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            assert response.body() != null;
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseMagang> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_regMagang);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(FormMagang.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(FormMagang.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(FormMagang.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(FormMagang.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(FormMagang.this, Kontak.class));
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK){
                upflag = true;

                Uri fileUri = data.getData();

                sfile = new File(getPathSourceFile(fileUri));
                fileName = pdfName;

                dFile = new File(file, fileName);

                try {
                    copyFile(dFile, sfile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class UploadFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                FileInputStream input = new FileInputStream(dFile);
                HttpFileUpload hfu = new HttpFileUpload(ApiCall.magang_file, "ftitle", "fdescription", fileName);
                upflag = hfu.Send_Now(input);

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s){
            if (upflag){
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Sayang File Tidak Bisa Di Upload...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getPathSourceFile(Uri fileUri) {
        if (fileUri == null)
            return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(fileUri, "file");
            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);

            String tempFIle = getTempFileName(this);
            output = new FileOutputStream(tempFIle);

            return tempFIle;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(input);
            closeSilently(output);
        }
    }

    private void closeSilently(Closeable c) {
        if (c == null)
            return;
        try {
            c.close();
        } catch (Throwable ignored){

        }
    }

    private String getTempFileName(Context context) throws IOException {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("File", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }

    private void copyFile(File sfile, File dFile) throws IOException {
        if (!dFile.exists()){
            return;
        }

        FileChannel sour;
        FileChannel dest;

        sour = new FileOutputStream(dFile).getChannel();
        dest = new FileOutputStream(sfile).getChannel();
        if (dest != null){
            dest.transferFrom(sour, 0, sour.size());
        }
        if (sour != null){
            sour.close();
        }
        if (dest != null){
            dest.close();
        }
    }
}