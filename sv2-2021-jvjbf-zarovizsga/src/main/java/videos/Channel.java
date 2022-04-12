package videos;

public class Channel {

    // --- attributes ---------------------------------------------------------

    private String channelName;
    private int numberOfSubscribers;
    private int numberOfVideos;

    // --- constructors -------------------------------------------------------

    public Channel(String channelName, int numberOfSubscribers, int numberOfVideos) {
        this.channelName = channelName;
        this.numberOfSubscribers = numberOfSubscribers;
        this.numberOfVideos = numberOfVideos;
    }

    // --- getters and setters ------------------------------------------------

    public String getChannelName() { return channelName; }
    public int getNumberOfSubscribers() { return numberOfSubscribers; }
    public int getNumberOfVideos() { return numberOfVideos; }
}