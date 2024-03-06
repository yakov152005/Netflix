public class Series {
    private String name;
    private Episode[] episodes;

    public Series(String name, Episode[] episodes) {
        this.name = name;
        if (episodes == null) {
            episodes = new Episode[0];
        }
        this.episodes = episodes;
    }

    public Series(String name, Episode episode) {
        this.name = name;
        episodes = new Episode[1];
        this.episodes[0] = episode;
    }


    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Name: " + this.name +
                ", Episodes count: " + this.episodes.length;
    }

    public void printEpisodes() {
        for (int i = 0; i < this.episodes.length; i++) {
            System.out.println(this.episodes[i]);
        }
    }

    public boolean episodeExists(int number) {
        boolean exists = false;
        for (int i = 0; i < this.episodes.length; i++) {
            if (this.episodes[i].getNumber() == number) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void addEpisode (Episode episode) {
        Episode[] temp = new Episode[this.episodes.length + 1];
        for (int i = 0; i < this.episodes.length; i++) {
            temp[i] = this.episodes[i];
        }
        temp[temp.length - 1] = episode;
        this.episodes = temp;
    }

    public Episode getFirstEpisodeFromArray () {
        return this.episodes[0];
    }

    public int getNumberOfLastEpisode () {
        return this.episodes[this.episodes.length - 1].getNumber();
    }


}
