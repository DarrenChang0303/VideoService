package com.darren.videoservice.ui;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import com.darren.videoservice.controller.VideoController;
import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;
import com.darren.videoservice.interfaceadapter.ScreenView;
import com.darren.videoservice.service.VideoService;

public class VideoServiceCLI implements ScreenView {

	public List<Video> videos;
	public List<Sentence> sentences;

	public static void main(String[] args) throws ParseException {

		VideoServiceCLI vsCLI = new VideoServiceCLI();
		Scanner Input = new Scanner(System.in);
		Input.useDelimiter("\r\n");

		VideoController vc = new VideoController(vsCLI, "D:\\CS\\Paper\\resource\\video\\");
		System.out.println("search videos contain the keywords:");

		String key = Input.next();
		vc.getVideosBy(key);

		System.out.println("--------------------------");
		System.out.println("choose a video and play:");
		String videoname = Input.next();
		vc.playVideo(vsCLI.getVideoByName(videoname), key);

		System.out.println("--------------------------");
		System.out.println("choose sentence to return the clip:");
		String sentence = Input.next();

		vc.playVideo(vsCLI.getVideoByName(videoname), vsCLI.getSentenceByContent(sentence));
	}

	@Override
	public void setVideos(List<Video> videos) {
		this.videos = videos;

	}

	public Video getVideoByName(String videoName) {

		for (Video video : videos) {

			if (video.getName().equalsIgnoreCase(videoName)) {
				return video;
			}
		}

		return null;
	}

	@Override
	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public Sentence getSentenceByContent(String content) {

		for (Sentence sentence : sentences) {

			for (String contentInSentence : sentence.getContents()) {

				if (contentInSentence.equals(content)) {
					return sentence;
				}
			}

		}
		return null;
	}

}
