import java.util.Random;
import java.util.Scanner;

public class Netflix {
    public static final int CHOICE_SIGN_UP = 1;
    public static final int CHOICE_LOGIN = 2;

    public static final int LIST_ALL_SERIES = 1;
    public static final int LIST_SERIES_STARTED_WATCHING = 2;
    public static final int CHOOSE_SERIES = 4;
    public static final int LOGOUT = 5;

    private User[] users;
    private Series[] series;
    public static final String[] USERNAMES = {
            "Michael", "Ayelet", "Gal", "Oshrit", "Orin", "Liel"
    };

    private void addUserToArray(User user) {
        User[] temp = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            temp[i] = this.users[i];
        }
        temp[temp.length - 1] = user;
        this.users = temp;

    }

    private Episode[] createEpisodes(int count) {
        Episode[] episodes = new Episode[count];
        for (int i = 0; i < count; i++) {
            episodes[i] = new Episode(i + 1, new Random().nextInt(20, 30));
        }
        return episodes;
    }

    public Netflix() {
        this.users = new User[0];
        for (int j = 0; j < 3; j++) {
            User user = createRandomUser();
            this.addUserToArray(user);
        }
        this.series = new Series[3];
        this.series[0] = new Series("S1", createEpisodes(10));
        this.series[1] = new Series("S2", createEpisodes(20));
        this.series[2] = new Series("S3", createEpisodes(21));
        for (int i = 0; i < this.users.length; i++) {
            System.out.println(this.users[i]);
        }

        while (true) {
            System.out.println("What do you want to do? ");
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case CHOICE_SIGN_UP -> {
                    User user = this.signUp();
                    this.addUserToArray(user);


                }
                case CHOICE_LOGIN -> {
                    User user = login();
                    if (user != null) {
                        while (userChoice != LOGOUT) {
                            this.printInnerMenu();
                            userChoice = scanner.nextInt();
                            switch (userChoice) {
                                case LOGOUT -> {

                                }
                                case LIST_ALL_SERIES -> {
                                    printSeries();
                                }
                                case LIST_SERIES_STARTED_WATCHING -> {
                                    user.printWatchedSeries();
                                }
                                case CHOOSE_SERIES -> {
                                    System.out.println("Enter the name to the tv show: ");
                                    String name = new Scanner(System.in).nextLine();
                                    Series found = this.findSeriesByName(name);
                                    if (found != null) {
                                        int number;
                                        found.printEpisodes();
                                        do {
                                            System.out.println("Choose episode: ");
                                            number = scanner.nextInt();
                                        } while (!found.episodeExists(number));


                                        Episode episode = new Episode(number, 0);
                                        Series toAdd = new Series(found.getName(), episode);
                                        user.addWatchedEpisode(toAdd);
                                    } else {
                                        System.out.println("No such tv show!");
                                    }
                                }
                            }
                        }

                    } else {
                        System.out.println("No such user!");
                    }
                }
                default -> {
                    System.out.println("Invalid option!");
                }
            }
        }

    }

    private Series findSeriesByName(String name) {
        Series found = null;
        for (int i = 0; i < this.series.length; i++) {
            if (this.series[i].getName().equals(name)) {
                found = this.series[i];
            }
        }
        return found;
    }

    private void printSeries() {
        for (int i = 0; i < this.series.length; i++) {
            System.out.println(this.series[i]);
        }
    }

    private void printInnerMenu() {
        System.out.println("1 - List all series");
        System.out.println("2 - List series you started watching");
        System.out.println("3 - Print subscription details");
        System.out.println("4 - Choose series");
        System.out.println("5 - Logout");
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        User found = null;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].checkCredentials(username, password)) {
                found = this.users[i];
                break;
            }
        }
        return found;
    }

    private User signUp() {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        do {
            System.out.println("Enter a username: ");
            username = scanner.nextLine();
        } while (!isAvailableUsername(username));
        String password = null;
        do {
            System.out.println("Enter a password: ");
            password = scanner.nextLine();
        } while (!isStrongPassword(password));
        User user = new User(username, password);
        return user;
    }

      private boolean isStrongPassword(String password) {
        return password.length() >= 6 && containsDigit(password) && containsEnglishLetter(password);
    }

    private boolean containsDigit (String password) {
        boolean contains = false;
        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);
            for (int digit = 0; digit <=9 ; digit++) {
                if (currentChar - 'a' == digit) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    private boolean containsEnglishLetter (String password) {
        boolean exists = false;
        final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G"};
        for (int i = 0; i < LETTERS.length; i++) {
            if (password.contains(LETTERS[i])) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private User createRandomUser() {
        String username = getRandomUsername();
        while (!isAvailableUsername(username)) {
            username = getRandomUsername();
        }
        String password = generateRandomPassword();
        User user = new User(username, password);
        return user;
    }

    private String generateRandomPassword() {
        String password = "";
        final String[] LETTERS = {"_", "&", "X", "T", "A", "P", "A", "$"};
        int passwordLength = new Random().nextInt(6, 8);
        for (int i = 0; i < passwordLength; i++) {
            if (i % 2 == 0) {
                password += new Random().nextInt(10);
            } else {
                password += LETTERS[new Random().nextInt(LETTERS.length)];
            }
        }
        return password;
    }

    public boolean isAvailableUsername(String username) {
        boolean available = true;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUsername().equals(username)) {
                available = false;
                break;
            }
        }
        return available;
    }

    private String getRandomUsername() {
        Random random = new Random();
        int index = random.nextInt(USERNAMES.length);
        return USERNAMES[index];

    }
}
