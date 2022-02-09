public class VideoPriceRegular extends VideoPriceBehavior {
    public double getEachCharge(double eachCharge, int daysRented) {
        eachCharge += 2;
        if (daysRented > 2)
            eachCharge += (daysRented - 2) * 1.5;
        return eachCharge;
    }

    @Override
    int getType() {
        return Video.REGULAR;
    }
}
