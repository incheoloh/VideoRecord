public class VideoTypeVHS extends VideoTypeBehavior {
    @Override
    public int getPenalty() {
        return 1;
    }

    @Override
    public int getLimit() {
        return 5;
    }

    @Override
    int getType() {
        return Video.VHS;
    }
}
