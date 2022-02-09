public class Video {
	private String title ;

	public static final int REGULAR = 1 ;
	public static final int NEW_RELEASE =2 ;

	private VideoPriceBehavior priceBehavior;

	public static final int VHS = 1 ;
	public static final int CD = 2 ;
	public static final int DVD = 3 ;

	private VideoTypeBehavior typeBehavior;

	private boolean rented ;

	public Video(String title, int videoType, int priceCode) {
		this.setTitle(title) ;
		createVideoTypeBehavior(videoType);
		createVideoPriceCodeBehavior(priceCode);
	}

	private void createVideoPriceCodeBehavior(int priceCode) {
		switch (priceCode) {
			case REGULAR: this.priceBehavior = new VideoPriceRegular() ; break ;
			case NEW_RELEASE: this.priceBehavior = new VideoPriceRelease() ; break ;
		}
	}

	private void createVideoTypeBehavior(int videoType) {
		switch (videoType) {
			case VHS: this.typeBehavior = new VideoTypeVHS() ; break ;
			case CD: this.typeBehavior = new VideoTypeCD() ; break ;
			case DVD: this.typeBehavior = new VideoTypeDVD() ; break ;
		}
	}

	public int getLateReturnPointPenalty() {
		return typeBehavior.getPenalty();
	}

	public int getDaysRentedLimit() {
		return typeBehavior.getLimit();
	}

	public int getPriceCode() {
		return priceBehavior.getType();
	}

	public double getEachCharge(double eachCharge, int daysRented) {
		return priceBehavior.getEachCharge(eachCharge, daysRented);
	}

	public int getVideoType() {
		return typeBehavior.getType();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public int getPoint(int point) {
		return priceBehavior.getType() == Video.NEW_RELEASE ? point++:point;
	}
}
