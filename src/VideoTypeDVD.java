public class VideoTypeDVD extends VideoTypeBehavior {
    @Override
    public int getPenalty() {
        return 3;
    }

    @Override
    public int getLimit() {
        return 2;
    }

    @Override
    int getType() {
        return Video.DVD;
    }
}
