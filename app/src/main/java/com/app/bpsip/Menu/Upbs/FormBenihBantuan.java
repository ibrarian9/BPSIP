package com.app.bpsip.Menu.Upbs;

import static android.content.ContentValues.TAG;

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
import com.app.bpsip.Model.ResponseUpbs;
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

public class FormBenihBantuan extends AppCompatActivity {

    public static final String PDF_DIR = "bpsip/bibit";
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    private Boolean upflag = false;
    EditText edNama, edNik, edAlamat, edKelTani, edJabatan, edLuasLahan, edKomoditas, edBenih, edNoHp;
    ApiEndpoint apiEndpoint;
    String pdfName, fName;
    Button btnSend, btnUpload;
    private File dFile, sFile, file;
    String nama, nik, alamat, klmp_tani, jabatan, lahan, komoditas, benih, noHp;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_benih_bantuan);

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
        pdfName = "benih_" + dateFormat.format(currentTime) + ".pdf";

        edNama = findViewById(R.id.nama);
        edNik = findViewById(R.id.nik);
        edAlamat = findViewById(R.id.alamat);
        edKelTani = findViewById(R.id.tani);
        edJabatan = findViewById(R.id.jabatan);
        edLuasLahan = findViewById(R.id.lahan);
        edKomoditas = findViewById(R.id.komoditas);
        edBenih = findViewById(R.id.benih);
        edNoHp = findViewById(R.id.nohp);

        apiEndpoint = ApiCall.getApi().create(ApiEndpoint.class);

        btnSend = findViewById(R.id.kirim);
        btnSend.setOnClickListener(view -> {

            final String mFile = pdfName;
            nama = edNama.getText().toString();
            nik = edNik.getText().toString();
            alamat = edAlamat.getText().toString();
            klmp_tani = edKelTani.getText().toString();
            jabatan = edJabatan.getText().toString();
            lahan = edLuasLahan.getText().toString();
            komoditas = edKomoditas.getText().toString();
            benih = edBenih.getText().toString();
            noHp = edNoHp.getText().toString();

            if (mFile == null) {
                Toast.makeText(FormBenihBantuan.this, "File Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (nama.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (nik.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nik Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (alamat.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Alamat Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (klmp_tani.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nama Kelompok Tani Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (jabatan.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Jabatan Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (lahan.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Luas Kepemilikan Lahan Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (komoditas.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Komoditas Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (benih.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Kebutuhan Benih Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (noHp.equals("")) {
                Toast.makeText(FormBenihBantuan.this, "Nomor Hape Masih Kosong", Toast.LENGTH_SHORT).show();
            } else {

                Call<ResponseUpbs> postUpbsCall = apiEndpoint.postUpbs(nama, nik, alamat, klmp_tani, jabatan, lahan, komoditas, benih, noHp, mFile);

                postUpbsCall.enqueue(new Callback<ResponseUpbs>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseUpbs> call, @NonNull Response<ResponseUpbs> response) {
                        if (response.isSuccessful()) {
                            new UploadFile().execute();
                            sendToWa();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponseUpbs> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        Log.e(TAG, "onFailure: " + t.getCause());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnUpload = findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(view -> {
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

                            Uri uri = data.getData();
                            sFile = new File(getPathFromUri(uri));
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

        BottomNavigationView botNavbar = findViewById(R.id.navbar_benih);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(FormBenihBantuan.this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(FormBenihBantuan.this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(FormBenihBantuan.this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(FormBenihBantuan.this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(FormBenihBantuan.this, Kontak.class));
                return true;
            }
            return false;
        });
    }

    private void sendToWa() {
        String contact = "+6282172652675";
        String message =
                "Form Benih Bantuan " +
                        "%0aNama : " + nama +
                        "%0aNik : " + nik +
                        "%0aAlamat : " + alamat +
                        "%0aKelompok Tani : " + klmp_tani +
                        "%0aJabatan : " + jabatan +
                        "%0aLuas Lahan : " + lahan +
                        "%0aKomoditas : " + komoditas +
                        "%0aBenih : " + benih +
                        "%0aNo Hape : " + noHp;

        String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=" + message;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    class UploadFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                FileInputStream input = new FileInputStream(dFile);
                HttpFileUpload hfu = new HttpFileUpload(ApiCall.benih_file, "ftitle", "fdescription", pdfName);
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

    private String getPathFromUri(Uri uri) {
        if (uri == null)
            return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "r");
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

    private String getTempFilename(Context context) throws IOException {
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