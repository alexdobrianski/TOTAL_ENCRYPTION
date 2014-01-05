package com.example.total_encryption;

public class FileFormats {
	static final String VIDEO_3GPP = ".3gp";
	static final String VIDEO_MPEG4 = ".mp4";
	static final String VIDEO_MPEGTS = ".ts";
	static final String VIDEO_WEBM = ".webm";
	static final String VIDEO_MKV = ".mkv";

	private static String formatList[] = {VIDEO_3GPP, VIDEO_MPEG4, VIDEO_MPEGTS, VIDEO_WEBM, VIDEO_MKV};
	
	public static Boolean checkFileFormat(String format){
		for(int i = 0; i < formatList.length; ++i)
			if(format.toLowerCase().equals( formatList[i] ))
				return true;
		return false;
	}

}
