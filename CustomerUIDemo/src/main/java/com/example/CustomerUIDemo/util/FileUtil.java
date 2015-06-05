package com.example.CustomerUIDemo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.os.Environment;

public class FileUtil {

	// Get the storage root path.
	public static String getStorageRootPath() {

		String sdCardDir = null;
		String phoneDataDir = null;
		String path;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // SD card is exist or
														// not

		if (sdCardExist) {
			// Get the SD Card root: /mnt/sdcard - Sonny Shih 2014/09/26
			sdCardDir = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			path = sdCardDir;

		} else {
			// Get the phone root: /data - Sonny Shih 2014/09/26
			phoneDataDir = Environment.getDataDirectory().getAbsolutePath();

			path = phoneDataDir;
		}

		return path;
	}

	public static void outputLog(String log){
		String rootPath = getStorageRootPath();
		String tempFolderPath = rootPath + "/temp";
		
		File tempFolder = new File (tempFolderPath);
		
		if (!tempFolder.exists()) {
			tempFolder.mkdir();
		}
		
		String output = "output.log";
		String outputFilePath = tempFolderPath + "/" + output;
		
		File outputFile = new File(outputFilePath);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		
		try {
			FileWriter fileWriter = new FileWriter(outputFilePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter fileOut = new PrintWriter(bufferedWriter);
			fileOut.println(log);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Check the file is the audio or not
	public static boolean isAudio(String fileName) {
		boolean isAudio = false;

		fileName = fileName.toLowerCase();

		if (fileName.endsWith("mp3")) {
			isAudio = true;
		} else if (fileName.endsWith("wav")) {
			isAudio = true;
		} else if (fileName.endsWith("m4a")) {
			isAudio = true;
		} else if (fileName.endsWith("flac")) {
			isAudio = true;
		} else if (fileName.endsWith("mid")) {
			isAudio = true;
		} else if (fileName.endsWith("xmf")) {
			isAudio = true;
		} else if (fileName.endsWith("mxmf")) {
			isAudio = true;
		} else if (fileName.endsWith("rtttl")) {
			isAudio = true;
		} else if (fileName.endsWith("rtx")) {
			isAudio = true;
		} else if (fileName.endsWith("ota")) {
			isAudio = true;
		} else if (fileName.endsWith("imy")) {
			isAudio = true;
		} else if (fileName.endsWith("ogg")) {
			isAudio = true;
		}

		return isAudio;
	}
	
	// Check the file is the image or not.
	public static boolean isImage(String fileName) {
		boolean isImage = false;

		fileName = fileName.toLowerCase();

		if (fileName.endsWith("jpg")) {
			isImage = true;
		} else if (fileName.endsWith("jpeg")) {
			isImage = true;
		} else if (fileName.endsWith("gif")) {
			isImage = true;
		} else if (fileName.endsWith("png")) {
			isImage = true;
		} else if (fileName.endsWith("bmp")) {
			isImage = true;
		} else if (fileName.endsWith("webp")) {
			isImage = true;
		}

		return isImage;

	}

	// Check the file is the video or not
	public static boolean isVideo(String fileName) {
		boolean isMedia = false;

		fileName = fileName.toLowerCase();

		if (fileName.endsWith("3gp")) {
			isMedia = true;
		} else if (fileName.endsWith("mp4")) {
			isMedia = true;
		} else if (fileName.endsWith("avi")) {
			isMedia = true;
		} else if (fileName.endsWith("rm")) {
			isMedia = true;
		} else if (fileName.endsWith("rmvb")) {
			isMedia = true;
		} else if (fileName.endsWith("mpeg")) {
			isMedia = true;
		} else if (fileName.endsWith("mpg")) {
			isMedia = true;
		} else if (fileName.endsWith("mov")) {
			isMedia = true;
		} else if (fileName.endsWith("ts")) {
			isMedia = true;
		} else if (fileName.endsWith("aac")) {
			isMedia = true;
		} else if (fileName.endsWith("mkv")) {
			isMedia = true;
		}

		return isMedia;
	}

}
