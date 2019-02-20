package com.dawson.androidfileutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//Created by EminemRen on 2016/1/14.
public class FileUtil {
    private final static String TAG = "FileUtil";
    private static int mZipCount;

    public static String getAppFilePath() {
        if (!TextUtils.isEmpty(getLibratoneStorageDirectory())) {
            File file = new File(getLibratoneStorageDirectory() + File.separator + "otaCache");
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (!mkdirs) {
                    Log.e(TAG, "getAppFilePath->make otaCache dir failed");
                }
            }
            return file.getPath();
        }
        return "";
    }

    public static String getLogDir() {
        Context context = MyApplication.getContext();
        File filesDir = context.getFilesDir();
        if (!filesDir.exists()) {
            boolean makeDir = filesDir.mkdirs();
            if (!makeDir) {
                Log.e(TAG, "getLogDir->make filesDir failed");
            }
        }
        //'/data/user/0/com.libratone/files'
        String logParentDir = filesDir.getPath();
        return logParentDir + File.separator + Constants.LogConstant.LOG_DIRECT;
    }

    public static String getOldLogDir() {
        File externalFilesDir = new File(getLibratoneStorageDirectory());
        if (!externalFilesDir.exists()) {
            boolean mkdirs = externalFilesDir.mkdirs();
            if (!mkdirs) {
                Log.e(TAG, "getLogDir->make externalFilesDir failed");
            }
        }
        //'/storage/emulated/0/Android/data/com.libratone/files'
        String logParentDir = externalFilesDir.getPath();
        return logParentDir + File.separator + Constants.LogConstant.LOG_DIRECT;
    }

    public static String getLibratoneStorageDirectory() {
        //'   /storage/emulated/0/Android/data/com.libratone/files/log'
        File externalFile = MyApplication.getContext().getExternalFilesDir(null);  // external storage
        if (externalFile != null) {
            return externalFile.getPath();
        } else {
            //      /data/user/0/com.libratone/files
            final File internalFile = MyApplication.getContext().getFilesDir(); // internal storage
            if (internalFile != null) {
                return internalFile.getPath();
            }
        }
        return "";
    }

    public static boolean searchFile(String fileName) {
        String filePath = getAppFilePath();
        File file = new File(filePath + "/" + fileName);
        return file.exists();
    }

    public static File getFile(String fileName) {
        String filePath = getAppFilePath();
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) {
            return file;
        } else {
            return null;
        }
    }


    public static void deleteFile(Context context, String fileName) {
     /*   String filePath = getAppFilePath(context);
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }*/
    }

    public static InputStream getBytesFromFileAll(File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            return null;
        }
        return inputStream;
    }

    public static String formatZipFilePath(String prefix) {
        mZipCount++;
        String externalLogDir = FileUtil.getLogDir();
        String format = new SimpleDateFormat("-yyyy.MM.dd_HH.mm.ss.SS", Locale.getDefault()).format(new Date());
        return String.format("%s%s%s%s%s.zip", externalLogDir, File.separator,
                initLogFileNameWithUserData(null, false, prefix), format, mZipCount);
    }

    public static void appendCrashLog(String logString) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d(TAG, "sdcard not exist");
        }
        FileOutputStream fos = null;
        try {
            File logDir = new File(FileUtil.getLogDir());
            if (!logDir.exists()) {
                boolean success = logDir.mkdir();
                if (!success) {
                    Log.e(TAG, "appendCrashLog->make log dir failed");
                    return;
                }
            }
            File logFile = new File(logDir, initLogFileNameWithUserData(".log", true, Constants.LogConstant.CRASH_LOG_PREFIX));
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            Log.d(TAG, logFile.getPath());

            fos = new FileOutputStream(logFile, true);
            fos.write(logString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //this method is only suitable for single stage directory
    //maxFileSize: The maximum size of the file, if the file zipped is larger than maxFileSize, it will create a new zip file
    public static void zipFiles(String filesPath, String zipFilePath, long maxFileSize, String prefix, FilenameFilter filter, boolean deleteSrcFile) {
        try {
            File file = new File(filesPath);
            if (file.exists() && file.list() != null && file.list().length != 0) {
                File[] files = file.listFiles(filter);
                if (files != null && (files.length != 0)) {
                    FileWriter fileWriter = null;
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(zipFilePath);
                        for (File childFile : files) {
                            ZipOutputStream zipOutputStream = null;
                            FileInputStream fileInputStream = null;
                            try {
                                zipOutputStream = new ZipOutputStream(fileOutputStream);
                                fileInputStream = new FileInputStream(childFile);
                                ZipEntry zipEntry = new ZipEntry(childFile.getName());
                                zipOutputStream.putNextEntry(zipEntry);
                                int len;
                                byte[] buffer = new byte[1024];
                                while ((len = fileInputStream.read(buffer)) != -1) {
                                    zipOutputStream.write(buffer, 0, len);
                                }
                                if (deleteSrcFile) {
                                    boolean delete = childFile.delete();
                                    Log.d(TAG, "zipFiles->delete file " + childFile.getAbsolutePath() + " result:" + delete);
                                } else if (childFile.getName().startsWith(Constants.LogConstant
                                        .OTA_LOG_DIRECT_PREFIX)) {
                                    fileWriter = new FileWriter(childFile);
                                    fileWriter.write("");
                                }

                                File zipFile = new File(zipFilePath);
                                long l = zipFile.length() + childFile.length();
                                Log.d(TAG, "to zip size ==" + l + "\nzip file name=" + zipFile.getName());
                                if (l > maxFileSize) {
                                    zipFiles(filesPath, formatZipFilePath(prefix), maxFileSize, prefix, filter, deleteSrcFile);
                                    return;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (zipOutputStream != null) {
                                        zipOutputStream.closeEntry();
                                        zipOutputStream.finish();
                                        zipOutputStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (fileWriter != null) {
                                fileWriter.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //eg:CrashLog-AppVersion_2.5.3-Email_ll@qq.com-HUAWEI-HUAWEIMT7-CL00-Android_6.0-SDK_23-2016.12.27_14:37:08.log
    @SuppressLint("HardwareIds")
    public static String initLogFileNameWithUserData(String nameSuffix, boolean withTimeTag, String prefix) {
        StringBuilder name = new StringBuilder();

        name.append(prefix);
        name.append("-");

        //app version
        name.append("AppVersion_");
        name.append(Common.getVerName(MyApplication.getContext()));
        name.append("-");

        //email or device serial num
        String email = "lll@ll.ll";
        if (!(TextUtils.isEmpty(email))) {
            name.append("Email_");
            String replace = email.replace("@", Constants.LogConstant.LOG_NAME_AT_SYMBOL);
            name.append(replace);
            name.append("-");
        } else {
            name.append("DeviceId_");
            name.append(Build.SERIAL);
            name.append("-");
        }

        //release channel
        name.append("ReleaseChannel_");
        name.append(Manifest.getChannel());
        name.append("-");

        //MANUFACTURER
        name.append(Build.MANUFACTURER);
        name.append("-");

        // phone model
        name.append(android.os.Build.MODEL);
        name.append("-");
        // system version
        name.append("Android_");
        name.append(android.os.Build.VERSION.RELEASE);
        name.append("-");
        // SDK version
        name.append("SDK_");
        name.append(android.os.Build.VERSION.SDK_INT);

        if (withTimeTag) {
            name.append("-");
            // create time
            String time = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss", Locale.getDefault()).format(new Date());
            name.append(time);
        }

        if (!TextUtils.isEmpty(nameSuffix)) {
            name.append(nameSuffix);
        }

        return name.toString().replace(" ", "");
    }

    public static void copyFile(String fileToUpLoadPath, String parentFolderPath) {
        FileInputStream fr = null;
        FileOutputStream bw = null;
        try {
            File srcFile = new File(fileToUpLoadPath);
            String fileName = srcFile.getName();
            File desParentFolder = new File(parentFolderPath);
            if (!desParentFolder.exists()) {
                boolean mkdirs = desParentFolder.mkdirs();
                if (!mkdirs) {
                    Log.e(TAG, "copyFile->make");
                }
            }
            File desFile = new File(parentFolderPath + File.separator + fileName);
            if (!desFile.exists()) {
                desFile.createNewFile();
            }
            fr = new FileInputStream(srcFile);
            bw = new FileOutputStream(desFile);

            //buffer
            byte[] b = new byte[512];
            while (fr.read(b) != -1) {
                bw.write(b);
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getExternalFavoriteDir() {
        File externalFilesDir = new File(getLibratoneStorageDirectory());
        if (!externalFilesDir.exists()) {
            boolean mkdirs = externalFilesDir.mkdirs();
            if (!mkdirs) {
                Log.e(TAG, "getExternalFavoriteDir->mkdirs failed");
            }
        }
        return externalFilesDir.getPath() + File.separator + Constants.LogConstant.FAVORITE_DIRECT;
    }

    public static void saveChannelToSdcard(String json) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d(TAG, "sdcard not exist");
            return;
        }
        FileOutputStream fos = null;
        try {
            File logDir = new File(FileUtil.getExternalFavoriteDir());
            if (!logDir.exists()) {
                boolean result = logDir.mkdir();
                if (!result) {
                    Log.e(TAG, "readChannelFromSdcard->make favoriteDir failed");
                }
            }
            File logFile = new File(logDir, Constants.LogConstant.FAVORITE_LOG_PREFIX + ".txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            Log.d(TAG, logFile.getPath());
            fos = new FileOutputStream(logFile, false);
            fos.write(json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readChannelFromSdcard() {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d(TAG, "sdcard not exist");
            return "";
        }
        String allChannels = "";
        BufferedReader br = null;
        try {
            File favoriteDir = new File(FileUtil.getExternalFavoriteDir());
            if (favoriteDir.exists()) {
                File logFile = new File(favoriteDir, Constants.LogConstant.FAVORITE_LOG_PREFIX + ".txt");
                if (logFile.exists()) {
                    Log.d(TAG, logFile.getPath());
                    StringBuilder result = new StringBuilder();
                    br = new BufferedReader(new FileReader(logFile));
                    String s;
                    while ((s = br.readLine()) != null) {
                        result.append(s);
                    }
                    allChannels = result.toString();
                    return allChannels;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allChannels;
    }
}
