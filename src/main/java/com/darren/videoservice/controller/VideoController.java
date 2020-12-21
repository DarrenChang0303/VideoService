package com.darren.videoservice.controller;

import java.text.ParseException;
import java.util.List;

import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;
import com.darren.videoservice.interfaceadapter.ScreenView;
import com.darren.videoservice.interfaceadapter.VideoPresenter;
import com.darren.videoservice.service.VideoService;


public class VideoController {
	private VideoService vs ; 
	private VideoPresenter vp;

	public VideoController(ScreenView sv, String path) {
		vp = new VideoPresenter(sv);
		vs = new VideoService(path);
	}

	public void getVideosBy(String key) { 
		List<Video> videos = vs.getVideosByKeyword(key);

		vp.showVideos(videos);

	}

	public void playVideo(Video video, String key) throws ParseException { 
		vp.playVideo(video);
		List<Sentence> sentences = video.getSentenceByKey(key);
		vp.listSentences(sentences);
	}

	public void playVideo(Video video, Sentence sentence) {
		vp.playVideo(video, sentence.getStartTime());
	}
}