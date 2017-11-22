package alibaba;

import java.io.*;
import java.util.*;




public class AlibabaScrapper {

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("keyword.txt"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<String> keywords = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			String key = scanner.nextLine();
			keywords.add(key);
		}
		ArrayList<ScrapperThread> scrapperThreadList = new ArrayList<ScrapperThread>();
		for (String key : keywords) {
			scrapperThreadList.add(new ScrapperThread(key));
			
			
		}
		for(ScrapperThread scrapperThread: scrapperThreadList){
			
			try {
				scrapperThread.thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	

}

