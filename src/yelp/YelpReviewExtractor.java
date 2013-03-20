package yelp;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.IOOperator;
import utils.ParameterSetting;

import data_structure.ReviewEntry;

public class YelpReviewExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		YelpReviewExtractor.getInstance().DownloadReviews("http://www.yelp.com/biz/calafia-caf%C3%A9-and-market-a-go-go-palo-alto-2");
	}	
	
	
	
	public static YelpReviewExtractor singleton;
	public static YelpReviewExtractor getInstance() {
		if (singleton == null)
			singleton = new YelpReviewExtractor();
		return singleton;
	}
	String outputDir = ParameterSetting.DOWNLOADSRC + "/" + ParameterSetting.YELPREVTEXTSRC;
	
	public YelpReviewExtractor(){
		File dir = IOOperator.createDir(outputDir);
	}
	
	public void DownloadReviews(String restaurant_url){
		int doneReviewCounter = 0;
		int finishCheckerCounter = 0;
		doneReviewCounter += ExtractReviewsinOnePage(restaurant_url);
		String targetUrl = restaurant_url;
		while(true){
			targetUrl  =  GetNextPageURL(targetUrl);
			System.out.println(targetUrl);
			
			int nextPageCounter = ExtractReviewsinOnePage(targetUrl);
			doneReviewCounter += nextPageCounter;
			//Empty loop checker
			if(nextPageCounter == 0){
				finishCheckerCounter++;
				System.out.println(finishCheckerCounter);
			}
			if(finishCheckerCounter >=5){
				break;
			}
			System.out.println(doneReviewCounter);
			
		}
	}

	public  String GetNextPageURL(String curpageurl){
		String res = "";
		curpageurl = curpageurl.trim();
		if(curpageurl.contains("?start=")){
			int index =curpageurl.indexOf("?start=");
			int newreviewstart = Integer.valueOf(curpageurl.substring(index+7)) + 40;
			res = curpageurl.substring(0, index) + "?start=" + String.valueOf(newreviewstart);
		}
		else{
			res = curpageurl + "?start=40";
		}
		System.out.println(res);
		return res;
	}

	
	private  int ExtractReviewsinOnePage(String target_url){
		int extractedReviews = 0;
		Document doc;
		String entity_name = "error";
		int totalreviews = -1;
		String filename = "";
		String content = "";
		try {
			doc = Jsoup.connect(target_url).timeout(0).get();
			String title = doc.title();
			System.out.println(title);
			// extract restaurant name, 
			Element entityInfoDiv = doc.getElementById("bizInfoHeader");
			Element entityName = entityInfoDiv.getElementsByTag("h1").get(0);
			entity_name = entityName.text();
			System.out.println(entityName.text());
			// extract overall rating
			Element rest_rating_html = entityInfoDiv.getElementsByTag("meta").get(0);
			String rest_rating = rest_rating_html.attr("content");
			System.out.println(rest_rating);
			
			Element ele_review_count = entityInfoDiv.getElementsByAttributeValue("itemprop", "reviewCount").get(0);
			totalreviews = Integer.valueOf(ele_review_count.text());
			System.out.println(totalreviews);
			
			filename = entity_name +"_"+ String.valueOf(rest_rating) + "_" + String.valueOf(totalreviews);
			// Start to extract reviews
			Elements userreview_blocks = doc.getElementsByAttributeValue("class", "review clearfix  externalReview");
			System.out.println(userreview_blocks.size());
			extractedReviews = userreview_blocks.size();
			for(Element li : userreview_blocks ){
				ReviewEntry tmp_entry = new ReviewEntry();
				Elements username_html = li.getElementsByAttributeValue("class", "user-name");
				String username = username_html.get(0).text();
				System.out.println("username : "+ username);
				Elements useraddr_html = li.getElementsByAttributeValue("class", "reviewer_info");
				String useraddr = useraddr_html.get(0).text();
				System.out.println("useraddr : " + useraddr);
				Elements usrrating_html = li.getElementsByTag("meta");
				String userrating = usrrating_html.get(0).attr("content");
				System.out.println("userrating : " + userrating);
				String publishtime = usrrating_html.get(1).attr("content");
				System.out.println("published time: " + publishtime);
				Elements friend_counter_html = li.getElementsByAttributeValue("class", "i-wrap ig-wrap-common i-friends-orange-common-wrap");
				String friend_counter = friend_counter_html.get(0).text().split(" ")[0];
				System.out.println("friend_counter : " + friend_counter);
				
				Elements review_counter_html = li.getElementsByAttributeValue("class", "i-wrap ig-wrap-common i-user-review-count-common-wrap");
				String review_counter = review_counter_html.get(0).text().split(" ")[0];
				System.out.println("review_counter : " + review_counter);
				
				tmp_entry.setUserid(username);
				tmp_entry.setUser_address(useraddr);
				tmp_entry.setRating(Float.valueOf(userrating));
				tmp_entry.setDate(publishtime);
				tmp_entry.setUser_friends_counter(Integer.valueOf(friend_counter));
				tmp_entry.setUser_reviews_counter(Integer.valueOf(review_counter));
				
				Elements photo_associate = li.getElementsByAttributeValue("class", "i-wrap i-camera-wrap photo-count");
				if(photo_associate.size() != 0){
					String photoUrl = ParameterSetting.YELPWEBURLPREFIX + photo_associate.get(0).attr("href");
					String photoCount = photo_associate.get(0).text();
					System.out.println("photoUrl: " + photoUrl);
					System.out.println("photoCount: " + photoCount);
					tmp_entry.setAssoci_image(photoUrl);
					tmp_entry.setAssoci_image_counter(Integer.valueOf(photoCount.split(" ")[0]));
				}
				
				Elements checkin_html = li.getElementsByAttributeValue("class", "i-wrap i-checkin-irregular-wrap checkin checkin-irregular");
				if(checkin_html.size() != 0){
					int checkinCount = Integer.valueOf(checkin_html.get(0).getElementsByTag("strong").get(0).text());
					System.out.println("checkinCount: " + checkinCount);
					tmp_entry.setCheckin_counter(checkinCount);
				}
				
				Elements useful_html = li.getElementsByAttributeValue("class", "useful ufc-btn");
				String useful = useful_html.text();
				System.out.println("useful: " + useful.split(" ")[1].toString());
				Elements funny_html = li.getElementsByAttributeValue("class", "funny ufc-btn");
				String funny = funny_html.text();
				System.out.println("funny: " + funny);
				Elements cool_html = li.getElementsByAttributeValue("class", "cool ufc-btn");
				String cool = cool_html.text();
				System.out.println("Cool: " + cool);
				
				tmp_entry.setEvalu_userful_counter(useful);
				tmp_entry.setEvalu_funny_counter(funny);
				tmp_entry.setEvalu_cool_counter(cool);
				
				Elements review_html = li.getElementsByAttributeValue("class", "review_comment ieSucks");
				if(review_html.size() != 0){
					String reviews_content = review_html.get(0).text();
					tmp_entry.setTxt(reviews_content);
				}
				
				content = tmp_entry.toOutPutString();
				IOOperator.writeToFileUTF8(outputDir + "/" + filename, content, true);
				System.out.println("Output: " + content);
				content = "";
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return extractedReviews;
	}
}
