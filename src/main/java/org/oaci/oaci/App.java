package org.oaci.oaci;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Hello world!
 *
 */
public class App {

	private Map<Integer, OaciAbstract> mapBlocks = new HashMap<Integer, OaciAbstract>();
	private Integer index = 0;
	private String filename = null;

	public App(final String filename) {
		super();
		this.filename = filename;
	}

	public void decode() {

		try {
			readRessources();
			readMessage();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void readRessources() throws JsonProcessingException, IOException {
		OaciRessources.getInstance().getOaciDic();
	}

	private void readMessage() {

		try (Scanner scanner = new Scanner(new File(filename))) {

			// read two lines of header ???
			readheader(scanner);

			while (scanner.hasNext()) {
				readOaciBlock(scanner);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readheader(Scanner scanner) {
		scanner.nextLine();
		scanner.nextLine();
	}

	private void readOaciBlock(Scanner scanner) {
		OaciAbstract oaci = null;
		// StringBuffer buffer = null;

		String curline = scanner.nextLine();
		// check if TAF/METAR is identified from first line
		oaci = checkFormat(curline);

		// if start format (TAF/METAR) and = are on same line
		if (!isEnd(curline)) {

			// if oaci is null record followings lines
			// if (oaci == null) {
			// buffer = new StringBuffer(curline);
			// }

			curline = scanner.nextLine();
			while (scanner.hasNext() && !isEnd(curline)) {
				// check if TAF/METAR is identified from second line ??
				if (null != checkFormat(curline)) {
					oaci = checkFormat(curline);
				}
				// append intermediate line
				oaci.append(curline);
				// buffer.append(curline);
				curline = scanner.nextLine();
			}

			// If end line is founded
			if (isEnd(curline)) {
				oaci.append(curline);
				oaci.decode();
				mapBlocks.put(index++, oaci);
			}
		} else {
			oaci.decode();
			mapBlocks.put(index++, oaci);
		}
		oaci = null;
	}

	private boolean isEnd(final String curline) {
		return (curline.trim().endsWith("="));
	}

	private boolean isTaf(final String curline) {
		return (curline.trim().startsWith("TAF"));
	}

	private boolean isMetar(final String curline) {
		return (curline.trim().startsWith("METAR"));
	}

	private OaciAbstract checkFormat(String curline) {
		OaciAbstract oaci = null;
		if (isMetar(curline)) {
			oaci = new OaciMetar();
			oaci.append(curline);
		} else if (isTaf(curline)) {
			oaci = new OaciTaf();
			oaci.append(curline);
		} else {
			// System.out.println("Undefined format");
		}
		return oaci;
	}

	public Map<Integer, OaciAbstract> getMapBlocks() {
		return mapBlocks;
	}

	public Integer getIndex() {
		return index;
	}

}
