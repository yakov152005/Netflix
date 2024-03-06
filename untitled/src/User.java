import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date creationDate;
    private Series[] watchedEpisodes;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        this.creationDate = new Date();
        this.watchedEpisodes = new Series[0];
    }

    public String getUsername () {
        return this.username;
    }

    public String toString () {
        return "Username: " + this.username + ", Password: " +
                this.password +", Creation Date: " + this.creationDate;
    }

    public boolean checkCredentials (String username, String password) {
        boolean success = false;
        if (this.username.equals(username) && this.password.equals(password)) {
            success = true;
        }
        return success;
    }

    public void addWatchedEpisode (Series series) {
        Series found = this.isSeriesExist(series);
        if (found != null) {
            found.addEpisode(series.getFirstEpisodeFromArray());
        } else {
            Series[] temp = new Series[this.watchedEpisodes.length + 1];
            for (int i = 0; i < this.watchedEpisodes.length; i++) {
                temp[i] = this.watchedEpisodes[i];
            }
            temp[temp.length - 1] = series;
            this.watchedEpisodes = temp;
        }
    }

    private Series isSeriesExist (Series series) {
        Series found = null;
        for (int i = 0; i < this.watchedEpisodes.length; i++) {
            if (this.watchedEpisodes[i].getName().equals(series.getName())) {
                found = this.watchedEpisodes[i];
                break;
            }
        }
        return found;
    }

    public void printWatchedSeries () {
        System.out.println("Watched Series: ");
        for (int i = 0; i < this.watchedEpisodes.length; i++) {
            Series series = this.watchedEpisodes[i];
            System.out.println("Series name: " + series.getName() + ", last episode: " + series.getNumberOfLastEpisode());
        }
    }






}
