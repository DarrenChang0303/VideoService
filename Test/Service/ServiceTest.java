package Service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.darren.videoservice.entity.Video;
import com.darren.videoservice.service.VideoService;
import com.google.common.collect.Lists;

public class ServiceTest {

	private String path = "D:\\CS\\Paper\\resource\\video\\";
	private List<Video> output;
	private VideoService vs = new VideoService(path);

	@Test
	public void testGetVideosByKeyword() {
		String key = "right";
		output = vs.getVideosByKeyword(key);

		System.out.println("If the keyword is 'right'");
		System.out.println("---------------------------");

		System.out.println("The number of videos contain the keyword");
		System.out.println("Excepted result: 24");
		System.out.println("Test result:     " + output.size());
		assertEquals(24, output.size());
		System.out.println("---------------------------");

		System.out.println("The 7th video name in this list");
		System.out.println("Excepted result: EMMA WATSON_ Gender Equality");
		System.out.println("Test result:     " + output.get(7).getName());
		assertEquals("EMMA WATSON_ Gender Equality", output.get(7).getName());
		System.out.println("---------------------------");

		System.out.println("The last video name in this list");
		System.out.println("Excepted result: Whatever Happened to Tom's Hoodie");
		System.out.println("Test result:     " + output.get(23).getName());
	}

}
