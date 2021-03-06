import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private final int RENTED = 0;
	private final int RETURNED = 1;

	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = RENTED ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == RETURNED ) {
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;
		if (getStatus() == RETURNED) { // returned Video
			long diff = returnDate.getTime() - rentDate.getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - rentDate.getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
		if ( daysRented <= 2) return limit ;

		return video.getDaysRentedLimit() ;
	}

	public int getRentalDaysLeft()
	{
		int daysRented = 0;

		if (getStatus() == RETURNED) { // returned Video
			long diff = getReturnDate().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
		return daysRented;
	}

	public double getRentalCharge(int days)
	{
		double eachCharge = 0;
		return getVideo().getEachCharge(eachCharge, days);
	}

	public int getRentalPoints(int days)
	{
		int eachPoint = 1;

		eachPoint = video.getPoint(eachPoint);

		if ( days > getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty()) ;

		return eachPoint;
	}

	public String getVideoTitle()
	{
		return getVideo().getTitle();
	}
}
