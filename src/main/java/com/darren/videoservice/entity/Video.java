package com.darren.videoservice.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Video {

	private String name;
	private List<Sentence> sentences;
	private Map<String, List<Sentence>> keyIndex;
	private String source;

	public Video(String name, String source, List<Sentence> sentences) {
		this.name = name;
		this.source = source;
		this.sentences = sentences;
		makeIndex();
	}

	private void makeIndex() {
		keyIndex = Maps.newHashMap();
		for (Sentence sentence : sentences) {
			for (String key : sentence.getKeys()) {
				List<Sentence> keySentences = keyIndex.get(key);
				if (keySentences == null) {
					keySentences = Lists.newLinkedList();
					keyIndex.put(key, keySentences);
				}
				keySentences.add(sentence);
			}
		}
	}

	public List<Sentence> getSentence() {
		return sentences;
	}

	public List<Sentence> getSentenceByKey(String key) {
		return keyIndex.get(key);
	}

	public String getSource() {
		return source;
	}

	public Set<String> getKeywords() {
		return keyIndex.keySet();
	}

	public String getName() {
		return name;
	}
}
