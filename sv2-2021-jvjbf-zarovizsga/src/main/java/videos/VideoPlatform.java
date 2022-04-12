package videos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    // --- attributes ---------------------------------------------------------

    private List<Channel> channels;

    // --- constructors -------------------------------------------------------

    public VideoPlatform() {
        channels = new ArrayList<>();
    }

    // --- getters and setters ------------------------------------------------

    public List<Channel> getChannels() { return channels; }

    // --- public methods -----------------------------------------------------

    public void readDataFromFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                updateChannels(line);
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Cannot open file for read!", exception);
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
            .mapToInt(Channel::getNumberOfVideos)
            .sum();
    }

    // --- private methods ----------------------------------------------------

    private void updateChannels(String line) {
        String[] lineData = line.split(";");
        String channelName = lineData[0];
        int numberOfSubscribers = Integer.parseInt(lineData[1]);
        int numberOfVideos = Integer.parseInt(lineData[2]);
        channels.add(new Channel(channelName, numberOfSubscribers, numberOfVideos));
    }
}