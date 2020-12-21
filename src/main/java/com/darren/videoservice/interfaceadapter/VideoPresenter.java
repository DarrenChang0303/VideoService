package com.darren.videoservice.interfaceadapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;

public class VideoPresenter {
	public ScreenView sv;
	private final static SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss,SSS");
	private final static String stString = "00:00:00,000";

	public VideoPresenter(ScreenView sv) {
		this.sv = sv;
	}

	public void showVideos(List<Video> videos) {
		sv.setVideos(videos);
		for (Video video : videos) {
			System.out.println(video.getName());
		}
	}

	public void listSentences(List<Sentence> sentences) {
		sv.setSentences(sentences);
		for (Sentence sentence : sentences) {
			System.out.println(st.format(sentence.getStartTime()) +
					" ==> " + sentence.getContents().toString());
		}
	}

	public void playVideo(Video video) throws ParseException {
		playVideo(video, st.parse(stString));
	}

	public void playVideo(Video video, Date date) {
		System.out.println("start video " + video.getSource() + " at " + st.format(date));
	}

}
