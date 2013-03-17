package yelp;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.IOOperator;
import utils.ParameterSetting;

public class YelpImageExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static YelpImageExtractor singleton;
	public static YelpImageExtractor getInstance() {
		if (singleton == null)
			singleton = new YelpImageExtractor();
		return singleton;
	}
	
	int curimg_index = 0;
	
	public void DownloadResturantImages(String restaurant_url){
		curimg_index = 0;
		String[] rest_img_info =  ExtractRestImgInfo(restaurant_url);
		if(rest_img_info == null || rest_img_info.length != 2)
			return;
		File dir = IOOperator.createDir(rest_img_info[1]);
		int totalDoneImages = ExtractImgInonePage(rest_img_info[0], rest_img_info[1]);
		int emptyloop_counter = 0;
		while(true){
			rest_img_info[0] = GetNextImgPageURL(rest_img_info[0]);
			int curPageImageCounter = ExtractImgInonePage(rest_img_info[0], rest_img_info[1]);
			curimg_index += curPageImageCounter;
			if(curPageImageCounter == 0){
				emptyloop_counter++;
				System.out.println(emptyloop_counter);
			}
			if(emptyloop_counter >= 5){
				break;
			}
			System.out.println(curimg_index);
		}
	}
	
	
	private String[] ExtractRestImgInfo(String restaurant_url){
		Document doc;
		try{
			doc = Jsoup.connect(restaurant_url).timeout(0).get();
			String title = doc.title();
			System.out.println(title);
			Elements imageEntries = doc.getElementsByAttributeValue("class", "photo-box biz-photo-box pb-ms");
			Element imageUrl;
			if(imageEntries == null || imageEntries.size() == 0){
				imageUrl = doc.getElementById("slide-viewer-all");
			}
			else
				imageUrl = imageEntries.get(0).getElementsByTag("a").get(0);
			String url = ParameterSetting.YELPWEBURLPREFIX + imageUrl.attr("href");
			Element entityInfoDiv = doc.getElementById("bizInfoHeader");
			Element entityName = entityInfoDiv.getElementsByTag("h1").get(0);
			String entity_name = entityName.text();
			System.out.println(entityName.text());
			
			String[] res = new String[2];
			res[0] = url;
			res[1] = entity_name;
			return res;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public String GetNextImgPageURL(String curpageurl){
		String res = "";
		if(curpageurl.contains("?start=")){
			int index =curpageurl.indexOf("?start=");
			int newreviewstart = Integer.valueOf(curpageurl.substring(index+7)) + 100;
			res = curpageurl.substring(0, index) + "?start=" + String.valueOf(newreviewstart);
		}
		else{
			int index = curpageurl.indexOf("?");
			if(index > 0)
				curpageurl = curpageurl.substring(0, index);
			res = curpageurl + "?start=100";
		}
		System.out.println(res);
		return res;
	}
	
	
	private int ExtractImgInonePage(String url, String entity_name){
		int extracted_counter = 0;
		Document doc;
		try {
			String dirName = entity_name;
			doc = Jsoup.connect(url).timeout(0).get();
			Elements thumbEntries = doc.getElementsByAttributeValue("class", "thumb-wrap");
			Element thumbDiv = doc.getElementById("photo-thumbnails");
			Elements authorEntries = thumbDiv.getElementsByAttributeValue("class", "caption");
			int i =0;
			for(i = 0; i<thumbEntries.size(); i++){
				Element thumbImageUrl = thumbEntries.get(i).getElementsByTag("img").get(0);
				String thumbUrl = thumbImageUrl.attr("src");
				String largeUrl = thumbUrl.replace("ms.jpg", "l.jpg");
				System.out.println(largeUrl);
				
				Elements authorInfo = authorEntries.get(i).getElementsByTag("a");
				String authorId;
				if(authorInfo == null || authorInfo.size() == 0){
					authorId = "null";
				}
				else
					authorId = authorInfo.get(0).text();
				System.out.println(authorId);
				
				Elements comments = authorEntries.get(i).getElementsByAttributeValue("class", "smaller");
				String commentfromUser;
				if(comments.size() == 2){
					commentfromUser = comments.get(1).text();
				}else if(comments.size() == 1){
					if(!comments.get(0).text().contains("From")){
						commentfromUser = comments.get(0).text();
					}
					else
						commentfromUser = "";
				}else {
					commentfromUser = "";
				}
				
				//String tmp_filename = String.valueOf(i) + "_" + authorId + "_" + URLEncoder.encode(commentfromUser)  + ".jpg";
				String tmp_filename = String.valueOf(curimg_index + i)  + ".jpg";
				
				download_image(largeUrl, entity_name, tmp_filename);
					
				
				String content = String.valueOf(curimg_index + i) + "\t" + authorId + "\t" + commentfromUser + "\n";
				String logFileName= entity_name + "/"+ "log.txt";
				IOOperator.writeToFile(logFileName, content, true);
				extracted_counter++;
				System.out.println(curimg_index + i);
			}
			

			// Start to extract reviews
			

			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return extracted_counter;
	}
	


	private void download_image(String img_url, String dir_name, String fileName){
		String imageUrl = img_url;
		File dir = IOOperator.createDir(dir_name);
		String destinationFile = dir_name + "/" + fileName;
		System.out.println(destinationFile);
		try {
			IOOperator.saveImage(imageUrl, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
