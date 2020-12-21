package InterfaceAdapter;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.darren.videoservice.entity.Sentence;
import com.darren.videoservice.entity.Video;
import com.darren.videoservice.interfaceadapter.VideoSource;

public class InterfaceAdapterTest {

	private String path = "D:\\CS\\Paper\\resource\\video\\";
	private String key = "receive";
	private List<Video> output = null;
	private VideoSource vs = new VideoSource();
	private SimpleDateFormat ss = new SimpleDateFormat("HH:mm:ss,SSS");
	private List<String> listContentTest = new ArrayList<>();
	private List<String> listKeyTest = new ArrayList<>();

	@Test
	public void test() {
		listContentTest.add("receive these rights.");
		listKeyTest.add("receive");
		listKeyTest.add("these");
		listKeyTest.add("rights");
		output = vs.getAllVideosFromFolder(path);

		System.out.println("The data from video");
		System.out.println("---------------------------------------");
		System.out.println("1. The number of video sources:");
		System.out.println("Excepted result: 30 ");
		System.out.println("Test result:     " + output.size());
		assertEquals(30, output.size());
		System.out.println("---------------------------------------");

		System.out.println("2. The video name :");
		System.out.println("Excepted result: EMMA WATSON_ Gender Equality ");
		System.out.println("Test result:     " + output.get(7).getName());
		assertEquals("EMMA WATSON_ Gender Equality", output.get(7).getName());
		System.out.println("---------------------------------------");

		System.out.println("3. The video path :");
		System.out.println("Excepted result: D:\\CS\\Paper\\resource\\video\\EMMA WATSON_ Gender Equality.mp4 ");
		System.out.println("Test result:     " + output.get(7).getSource());
		assertEquals("D:\\CS\\Paper\\resource\\video\\EMMA WATSON_ Gender Equality.mp4", output.get(7).getSource());
		System.out.println("---------------------------------------");

		System.out.println("4. The number of sentences in this video:");
		System.out.println("Excepted result: 103 ");
		System.out.println("Test result:     " + output.get(7).getSentence().size());
		assertEquals(103, output.get(7).getSentence().size());
		System.out.println("---------------------------------------");

		System.out.println("The data from SRT files:");
		System.out.println("---------------------------------------");
		System.out.println("5. The number of sentences contain the keyword in this video:");
		System.out.println("Excepted result: 2 ");
		System.out.println("Test result:     " + output.get(7).getSentenceByKey(key).size());
		assertEquals(2, output.get(7).getSentenceByKey(key).size());
		System.out.println("---------------------------------------");

		System.out.println("6. The tag of the first sentence:");
		System.out.println("Excepted result: 30");
		System.out.println("Test result:     " + output.get(7).getSentenceByKey(key).get(0).getTag());
		assertEquals("30", output.get(7).getSentenceByKey(key).get(0).getTag());
		System.out.println("---------------------------------------");

		System.out.println("7. The content of the first sentence:");
		System.out.println("Excepted result: " + listContentTest.toString());
		System.out.println("Test result:     " + output.get(7).getSentenceByKey(key).get(0).getContents().toString());
		assertEquals(listContentTest.toString(), output.get(7).getSentenceByKey(key).get(0).getContents().toString());
		System.out.println("---------------------------------------");

		System.out.println("8. The words of this content:");
		System.out.println("Excepted result: " + listKeyTest.toString());
		System.out.println("Test result:     " + output.get(7).getSentenceByKey(key).get(0).getKeys().toString());
		assertEquals(listKeyTest.toString(), output.get(7).getSentenceByKey(key).get(0).getKeys().toString());
		System.out.println("---------------------------------------");

		System.out.println("9. The start time of this sentence in the video:");
		System.out.println("Excepted result: 00:03:20,310");
		System.out.println("Test result:     " + ss.format(output.get(7).getSentenceByKey(key).get(0).getStartTime()));
		assertEquals("00:03:20,310", ss.format(output.get(7).getSentenceByKey(key).get(0).getStartTime()));
		System.out.println("---------------------------------------");

		System.out.println("10. The end time of this sentence in the video:");
		System.out.println("Excepted result: 00:03:23,540");
		System.out.println("Test result:     " + ss.format(output.get(7).getSentenceByKey(key).get(0).getEndTime()));
		assertEquals("00:03:23,540", ss.format(output.get(7).getSentenceByKey(key).get(0).getEndTime()));

	}

}
