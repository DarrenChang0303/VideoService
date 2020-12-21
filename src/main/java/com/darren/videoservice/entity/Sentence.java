package com.darren.videoservice.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class Sentence {
	private String tag;
	private Date startTime;
	private Date endTime;
	private List<String> contents;
	private List<String> keys;
	private String letter = "[a-z|| ||'||A-Z]";

	public Sentence(String tag, String startTime, String endTime, List<String> contents) {
		SimpleDateFormat ss = new SimpleDateFormat("HH:mm:ss,SSS");
		this.tag = tag;
		try {
			this.startTime = ss.parse(startTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			this.endTime = ss.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.contents = contents;
		makeKeyIndex();
	}

	private void makeKeyIndex() {
		keys = Lists.newArrayList();
		for (String words : contents) {
			words = matchResult(Pattern.compile(letter), words);
			String[] wordArray = words.split(" |,|!|\\?|'");

			for (String key : wordArray) {
				keys.add(key);

			}

		}

	}

	public static String matchResult(Pattern p, String str) {
		StringBuilder sb = new StringBuilder();
		Matcher m = p.matcher(str);
		while (m.find())
			for (int i = 0; i <= m.groupCount(); i++) {
				sb.append(m.group());
			}
		return sb.toString();
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getTag() {
		return tag;
	}

	public List<String> getContents() {
		return contents;
	}

	public List<String> getKeys() {
		return keys;
	}
}
