package com.rhb.shortviedo.common.utils1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	//ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
	public void convertorRemoveMusic(String videoInputPath, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -vcodec copy -an iron.mp4
		// -i iron.mp4 -i music.mp3 -t 10 -y result.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		command.add("-vcodec");
		command.add("copy");
		command.add("-an");

		

		command.add(videoOutputPath);
		
//		for (String c : command) {
//			System.out.print(c + " ");
//		}
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
	}
	public void convertorMerge(String videoInputPath, String mp3InputPath,
						  double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);

		command.add("-i");
		command.add(videoInputPath);

		command.add("-i");
		command.add(mp3InputPath);

		command.add("-t");
		command.add(String.valueOf(seconds));

		command.add("-y");
		command.add(videoOutputPath);

//		for (String c : command) {
//			System.out.print(c + " ");
//		}

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();

		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);

		String line = "";
		while ( (line = br.readLine()) != null ) {
		}

		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}

	}











	public static void main(String[] args) {
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertorRemoveMusic("C:\\ffmpeg\\bin\\first.MOV", "C:\\ffmpeg\\bin\\这是通过消音的视频.mp4");
			ffmpeg.convertorMerge("C:\\ffmpeg\\bin\\这是通过消音的视频.mp4","C:\\ffmpeg\\bin\\游乐场.mp3",20,"C:\\ffmpeg\\bin\\这是通过合成的视频.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
