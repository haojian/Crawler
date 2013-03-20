import java.io.File;
import java.util.ArrayList;

import utils.IOOperator;
import utils.ParameterSetting;
import utils.URLManager;
import yelp.YelpImageExtractor;
import yelp.YelpReviewExtractor;


public class Crawler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir = IOOperator.createDir(ParameterSetting.DOWNLOADSRC);
		ArrayList<String> urllist = URLManager.getInstance().URLList;
		for(String tmp:urllist){
			YelpReviewExtractor.getInstance().DownloadReviews(tmp);
			//YelpImageExtractor.getInstance().DownloadResturantImages(tmp);
		}
	}

}
