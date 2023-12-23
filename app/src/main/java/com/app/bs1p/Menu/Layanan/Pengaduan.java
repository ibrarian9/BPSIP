package com.app.bs1p.Menu.Layanan;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.bs1p.CallApi.ApiCall;
import com.app.bs1p.CallApi.ApiEndpoint;

import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.Model.ResponsePengaduan;
import com.app.bs1p.R;
import com.app.bs1p.Util.HttpFileUpload;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengaduan extends AppCompatActivity {

    public static final String JPG_DIR = "bpsip/pengaduan";
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    private Boolean upflag = false;
    EditText edNama, edNik, edAlamat ,edNohp ,edPengaduan, edTempat, edTanggal;
    ApiEndpoint api;
    Button btnKirim, btnFoto;
    public String jpgName, fName;
    private File dFile, sFile, file;
    ImageView camera, gallery, poto;
    ActivityResultLauncher<Intent> resultLauncherCamera, resultLauncherGallery;
    String nama, nik , alamat, nohp, pengaduan, tempat, tanggal, dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_pengaduan);

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        file = new File(path + "/" + JPG_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);

        Date currentTime = Calendar.getInstance().getTime();
        jpgName = "pengaduan_" + dateFormat.format(currentTime) + ".jpg";

        edNama = findViewById(R.id.nama);
        edNik = findViewById(R.id.edNik);
        edAlamat = findViewById(R.id.edAlamat);
        edTempat = findViewById(R.id.edTempat);
        edTanggal = findViewById(R.id.edTanggal);
        edNohp = findViewById(R.id.edNohp);
        edPengaduan = findViewById(R.id.edPengaduan);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        btnKirim = findViewById(R.id.send);
        btnKirim.setOnClickListener(view -> {
            final String foto = jpgName;
            nama = edNama.getText().toString();
            nik = edNik.getText().toString();
            alamat = edAlamat.getText().toString();
            tempat = edTempat.getText().toString();
            tanggal = edTanggal.getText().toString();
            nohp = edNohp.getText().toString();
            pengaduan = edPengaduan.getText().toString();

            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
            try {
                Date date = dateFormat1.parse(tanggal);
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
                dateTime = dateFormat2.format(date);
            } catch (ParseException e){
                e.printStackTrace();
            }

            if (foto == null ) {
                Toast.makeText(this, "Foto Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (nama.equals("")) {
                Toast.makeText(this, "Nama Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (nik.equals("")) {
                Toast.makeText(this, "Nik Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (alamat.equals("")) {
                Toast.makeText(this, "Alamat Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (tempat.equals("")) {
                Toast.makeText(this, "Tempat Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (tanggal.equals("")) {
                Toast.makeText(this, "Tanggal Belum Terisi", Toast.LENGTH_SHORT).show();
            } else if (pengaduan.equals("")) {
                Toast.makeText(this, "Uraian Belum Terisi", Toast.LENGTH_SHORT).show();
            } else {

                Call<ResponsePengaduan> postPengaduan = api.postPengaduan(nama, nik, alamat, tempat, dateTime, nohp, foto, pengaduan);
                postPengaduan.enqueue(new Callback<ResponsePengaduan>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponsePengaduan> call, @NonNull Response<ResponsePengaduan> response) {
                        if (response.isSuccessful()){
                            new UploadFile().execute();
                            sendToWa();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<ResponsePengaduan> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                        Log.e(TAG, "onFailure: "+t.getCause());
                        Toast.makeText(getApplicationContext(), "Form Gagal Dikirim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnFoto = findViewById(R.id.uploadFoto);
        btnFoto.setOnClickListener(view -> {
           Dialog choose = new Dialog(this);
           choose.requestWindowFeature(Window.FEATURE_NO_TITLE);
           choose.setContentView(R.layout.add_dialog);
           choose.setCancelable(false);
           camera = choose.findViewById(R.id.camera);
           gallery = choose.findViewById(R.id.gallery);

           camera.setOnClickListener(v -> {
               openCamera();
               choose.dismiss();
           });
           gallery.setOnClickListener(v1 -> {
               openGallery();
               choose.dismiss();
           });
           choose.show();
        });

        poto = findViewById(R.id.ivPoto);
        resultLauncherGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        upflag = true;
                        assert data != null;
                        Uri selectImg = data.getData();
                        sFile = new File(getPathFromUri(selectImg));
                        fName = jpgName;
                        dFile = new File(file, fName);

                        Picasso.get().load(selectImg)
                                .resize(120,120)
                                .centerCrop().into(poto);

                        try {
                            copyFile(sFile, dFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        resultLauncherCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        upflag = true;
                        assert data != null;
                        Bundle extras = data.getExtras();
                        assert extras != null;
                        Bitmap photo = (Bitmap) extras.get("data");
                        assert photo != null;
                        Uri selectImg = getImageUri(getApplicationContext(), photo);
                        poto.setImageURI(selectImg);
                        sFile = new File(getPathFromUri(selectImg));
                        fName = jpgName;
                        dFile = new File(file, fName);

                        Picasso.get().load(selectImg)
                                .resize(120, 120)
                                .centerCrop().into(poto);

                        try {
                            copyFile(sFile, dFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar);

        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(this, Kontak.class));
                return true;
            }
            return false;
        });
    }

    private void sendToWa() {

        String contact = "+6282172652675";
        String message =
                "Form Magang " +
                        "%0aNama : " + nama +
                        "%0aNik : " + nik +
                        "%0aAlamat : " + alamat +
                        "%0aTempat : " + tempat +
                        "%0aTanggal : " + tanggal +
                        "%0aNoHp : " + nohp +
                        "%0aPengaduan : " + pengaduan;

        String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=" + message;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private Uri getImageUri(Context context, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), photo, "bitmap", null);
        return Uri.parse(path);
    }

    class UploadFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                FileInputStream input = new FileInputStream(dFile);
                HttpFileUpload hfu = new HttpFileUpload(ApiCall.pengaduan_poto, "ftitle", "fdescription", jpgName);
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

    private String getPathFromUri(Uri img) {
        if (img == null)
            return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(img, "r");
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
        File outputFile = File.createTempFile("img", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }

    private void openGallery() {
        Intent pickPoto = new Intent(Intent.ACTION_PICK);
        pickPoto.setType("image/*");
        resultLauncherGallery.launch(pickPoto);
    }

    private void openCamera() {
        Intent takePoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultLauncherCamera.launch(takePoto);
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