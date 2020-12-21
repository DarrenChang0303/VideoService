package com.darren.videoservice.service;

import java.util.List;

import com.darren.videoservice.entity.Video;
import com.darren.videoservice.interfaceadapter.VideoSource;
import com.google.common.collect.Lists;

public class VideoService {

	private List<Video> videos;

	public VideoService(String path) {
		VideoSource vs = new VideoSource();
		this.videos = vs.getAllVideosFromFolder(path);
	}

	public List<Video> getVideosByKeyword(String key) {
		List<Video> keywordVideos = Lists.newLinkedList();

		for (Video video : videos) {
			if (video.getKeywords().contains(key)) {
				keywordVideos.add(video);
			}
		}
		return keywordVideos;
	}
}
