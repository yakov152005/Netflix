public class Episode {
    private int number;
    private String description;
    private int length;

    public Episode (int number, int length) {
        this.number = number;
        this.length = length;
        this.description = "Episode number " + this.number;
    }

    public String toString () {
        return "Number: " + this.number + ", Description: " + this.description
                + ", Length: " + this.length;
    }

    public int getNumber () {
        return this.number;
    }

}
