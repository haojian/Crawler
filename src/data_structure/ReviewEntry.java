package data_structure;

public class ReviewEntry {
	private String txt;
	private float rating;
	private String date;
	private String userid;
	private String user_address;
	private String evalu_userful_counter;
	private String evalu_funny_counter;
	private String evalu_cool_counter;
	private int user_friends_counter;
	private int user_reviews_counter;
	private String iselite;
	private String associ_image;
	private int associ_image_counter;
	private int checkin_counter;

	public ReviewEntry() {
		/*
		 * txt = ""; rating = 0; date = ""; userid = ""; user_address = "";
		 * evalu_userful_counter = 0; evalu_funny_counter = 0;
		 * evalu_cool_counter = 0; user_friends_counter = 0;
		 * user_reviews_counter = 0; iselite = "False"; associ_image = "";
		 * associ_image_counter = 0; checkin_counter = 0;
		 */
	}

	public ReviewEntry(String _txt, float _rating, String _date,
			String _userid, String _evalu_userful_counter,
			String _evalu_funny_counter, String _evalu_cool_counter,
			int _user_friends_counter, int _user_reviews_counter,
			String _iselite) {
		this.setTxt(_txt);
		this.setRating(_rating);
		this.setDate(_date);
		this.setUserid(_userid);

		this.setEvalu_userful_counter(_evalu_userful_counter);
		this.setEvalu_funny_counter(_evalu_funny_counter);
		this.setEvalu_cool_counter(_evalu_cool_counter);
		this.setUser_friends_counter(_user_friends_counter);
		this.setUser_reviews_counter(_user_reviews_counter);
		this.setIselite(_iselite);
	}

	public ReviewEntry(String _txt, float _rating, String _date,
			String _userid, String _evalu_userful_counter,
			String _evalu_funny_counter, String _evalu_cool_counter,
			int _user_friends_counter, int _user_reviews_counter,
			String _iselite, int _checkin_counter, String _associ_image,
			String _user_address) {
		this.setTxt(_txt);
		this.setRating(_rating);
		this.setDate(_date);
		this.setUserid(_userid);

		this.setEvalu_userful_counter(_evalu_userful_counter);
		this.setEvalu_funny_counter(_evalu_funny_counter);
		this.setEvalu_cool_counter(_evalu_cool_counter);
		this.setUser_friends_counter(_user_friends_counter);
		this.setUser_reviews_counter(_user_reviews_counter);
		this.setIselite(_iselite);
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEvalu_userful_counter() {
		return evalu_userful_counter;
	}

	public void setEvalu_userful_counter(String evalu_userful_counter) {
		this.evalu_userful_counter = evalu_userful_counter;
	}

	public String getEvalu_funny_counter() {
		return evalu_funny_counter;
	}

	public void setEvalu_funny_counter(String evalu_funny_counter) {
		this.evalu_funny_counter = evalu_funny_counter;
	}

	public String getEvalu_cool_counter() {
		return evalu_cool_counter;
	}

	public void setEvalu_cool_counter(String evalu_cool_counter) {
		this.evalu_cool_counter = evalu_cool_counter;
	}

	public int getUser_friends_counter() {
		return user_friends_counter;
	}

	public void setUser_friends_counter(int user_friends_counter) {
		this.user_friends_counter = user_friends_counter;
	}

	public int getUser_reviews_counter() {
		return user_reviews_counter;
	}

	public void setUser_reviews_counter(int user_reviews_counter) {
		this.user_reviews_counter = user_reviews_counter;
	}

	public String getIselite() {
		return iselite;
	}

	public void setIselite(String iselite) {
		this.iselite = iselite;
	}

	public String getAssoci_image() {
		return associ_image;
	}

	public void setAssoci_image(String associ_image) {
		this.associ_image = associ_image;
	}

	public int getCheckin_counter() {
		return checkin_counter;
	}

	public void setCheckin_counter(int checkin_counter) {
		this.checkin_counter = checkin_counter;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public int getAssoci_image_counter() {
		return associ_image_counter;
	}

	public void setAssoci_image_counter(int associ_image_counter) {
		this.associ_image_counter = associ_image_counter;
	}

	public String toOutPutString() {
		String res = "";

		res += "###" + userid + "\t" + user_address + "\t" + date + "\t"
				+ rating + "\t" + user_friends_counter + "\t"
				+ user_reviews_counter + "\t" + iselite + "\t" + associ_image
				+ "\t" + associ_image_counter + "\t" + checkin_counter + "\t"
				+ evalu_userful_counter + "\t" + evalu_funny_counter + "\t"
				+ evalu_cool_counter + "\t";
		res += "\n" + txt + "\n";
		return res;
	}
}
