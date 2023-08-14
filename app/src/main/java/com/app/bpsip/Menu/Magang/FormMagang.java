package com.app.bpsip.Menu.Magang;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;

import android.os.ParcelFileDescriptor;

import android.util.Log;
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

    public static final String PDF_DIR = "bpsip/magang";
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    private Boolean upflag = false;
    EditText edNama, edNim, edSekolah, edJurusan, edNoHp, edWaktu;
    ApiEndpoint apiEndpoint;
    Button btnKirim, btnUploadFile;
    public String pdfName, fName;
    private File dFile, sFile, file;
    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_magang);

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        file = new File(path + "/" + PDF_DIR);
        if (!file.exists()) {
            file.mkdirs();
            Log.d("Dir", "Directory Created");
        } else {
            Log.e("Dir", "Dir Not Created");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);

        Date currentTime = Calendar.getInstance().getTime();
        pdfName = "magang_" + dateFormat.format(currentTime) + ".pdf";

        edNama = findViewById(R.id.magangNama);
        edNim = findViewById(R.id.magangNim);
        edSekolah = findViewById(R.id.magangSekolah);
        edJurusan = findViewById(R.id.magangJurusan);
        edNoHp = findViewById(R.id.magangNoHp);
        edWaktu = findViewById(R.id.magangWaktu);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnUploadFile = findViewById(R.id.uploadFile);

        btnKirim = findViewById(R.id.send);
        btnKirim.setOnClickListener(view -> {

            final String mFile = pdfName;
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
                    public void onResponse(@NonNull Call<ResponseMagang> call, @NonNull Response<ResponseMagang> response) {
                        if (response.isSuccessful()) {
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
                    public void onFailure(@NonNull Call<ResponseMagang> call, @NonNull Throwable t) {
                        Log.d("Form Gagal", "onFailure: " + t.getCause() + t.getMessage());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnUploadFile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            resultLauncher.launch(Intent.createChooser(intent, "Pilih File PDF..."));
        });

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            upflag = true;

                            Uri uriFile = data.getData();
                            sFile = new File(getPathFromUri(uriFile));

                            fName = pdfName;

                            dFile = new File(file, fName);

                            try {
                                dFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                copyFile(sFile, dFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

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

    class UploadFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                FileInputStream input = new FileInputStream(dFile);
                HttpFileUpload hfu = new HttpFileUpload(ApiCall.magang_file, "ftitle", "fdescription", pdfName);
                upflag = hfu.Send_Now(input);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) {
            if (upflag) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Sayang File Tidak Bisa Di Upload...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPathFromUri(Uri uriFile) {
        if (uriFile == null)
            return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uriFile, "r");
            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);

            String tempFilename = getTempFilename(this);
            output = new FileOutputStream(tempFilename);

            int read;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            return tempFilename;
        } catch (IOException ignored) {
            // Nothing we can do
        } finally {
            closeSilently(input);
            closeSilently(output);
        }
        return null;
    }

    public static void closeSilently(Closeable c) {
        if (c == null)
            return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }

    private static String getTempFilename(Context context) throws IOException {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("file", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }

    private void copyFile(File sFile, File dFile) throws IOException {
        if (!sFile.exists()) {
            Log.e("cFile", "Sfile not Exist");
        }
        FileChannel source;
        FileChannel destination;
        source = new FileInputStream(sFile).getChannel();
        destination = new FileOutputStream(dFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }
}