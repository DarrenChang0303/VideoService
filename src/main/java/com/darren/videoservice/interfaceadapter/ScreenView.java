package com.darren.videoservice.interfaceadapter;

import java.util.List;

import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;

public interface ScreenView {
	public void setVideos(List<Video> videos);

	public void setSentences(List<Sentence> sentences);
}
