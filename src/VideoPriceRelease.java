public class VideoPriceRelease extends VideoPriceBehavior {
    @Override
    double getEachCharge(double eachCharge, int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getType() {
        return Video.NEW_RELEASE;
    }
}
