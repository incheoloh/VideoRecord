import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
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
		if ( status == 1 ) {
			this.status = 1;
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
		int daysRented = getDaysRented();
		if ( daysRented <= 2) return limit ;

		limit = getVideo().getDaysRentedLimit();

		return limit ;
	}

    int getDaysRented() {
    	int daysRented;
    	if (getStatus() == 1) { // returned Video
    		long diff = getReturnDate().getTime() - getRentDate().getTime();
    		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
    	} else { // not yet returned
    		long diff = new Date().getTime() - getRentDate().getTime();
    		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
    	}
    	return daysRented;
    }

    double getCharge(int daysRented) {
    	return getVideo().getEachCharge(0, daysRented);
    }

    int getPoint(int daysRented) {
    	int eachPoint = 1;
    
    	if ((getVideo().getPriceCode() == Video.NEW_RELEASE) )
    		eachPoint++;
    
    	if ( daysRented > getDaysRentedLimit() )
    		eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty()) ;
    	return eachPoint;
    }
}
