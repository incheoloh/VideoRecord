public class VideoTypeCD extends VideoTypeBehavior {
    @Override
    public int getPenalty() {
        return 2;
    }

    @Override
    public int getLimit() {
        return 3;
    }

    @Override
    int getType() {
        return Video.CD;
    }
}
