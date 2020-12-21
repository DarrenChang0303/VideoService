package com.darren.videoservice.interfaceadapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;

public class VideoSource {
	public List<Video> getAllVideosFromFolder(String path) {
		Pattern tagPattern = Pattern.compile("^\\d{1,4}$");
		Pattern timeDurationPattern = Pattern
				.compile("^(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})$");

		List<Video> videos = new LinkedList<Video>();
		File file = new File(path);
		File[] list = file.listFiles();

		List<File> videoFiles = new ArrayList<File>();

		for (File f : list) {
			if (f.getName().contains(".mp4")) {

				videoFiles.add(f);
			}
		}

		try {
			for (int i = 0; i < videoFiles.size(); i++) {
				for (File f : list) {
					if (f.getName().contains(fileNameWithoutExtension(videoFiles.get(i)))
							&& f.getName().endsWith("srt")) {

						BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));

						List<Sentence> sentences = new LinkedList<Sentence>();
						String strLine = "";
						Matcher m;

						String tag = "";
						String startTime = "";
						String endTime = "";
						List<String> contents = new LinkedList<String>();

						while ((strLine = br.readLine()) != null) {
							if (tagPattern.matcher(strLine).matches()) {

								tag = strLine;
							} else if ((m = timeDurationPattern.matcher(strLine)).matches()) {

								startTime = m.group(1);

								endTime = m.group(2);
							} else if (!strLine.trim().isEmpty()) {

								contents.add(strLine);
							} else {

								if (!contents.isEmpty()) {
									sentences.add(new Sentence(tag, startTime, endTime, contents));
									tag = "";
									startTime = "";
									endTime = "";

									contents = new LinkedList<String>();
								}
							}
						}
						videos.add(new Video(fileNameWithoutExtension(videoFiles.get(i)),
								videoFiles.get(i).getAbsolutePath(), sentences));
						br.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videos;
	}

	private String fileNameWithoutExtension(File file) {
		int i = file.getName().indexOf(".");
		return file.getName().substring(0, i);
	}
}
