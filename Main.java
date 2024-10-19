import java.util.*;

class Book {
    String title;
    List<String> genres;

    public Book(String title, List<String> genres) {
        this.title = title;
        this.genres = genres;
    }
}

class RecommendationSystem {
    private List<Book> books;

    public RecommendationSystem(List<Book> books) {
        this.books = books;
    }

    public List<Book> recommendBooks(List<String> userGenres) {
        Map<Book, Integer> scoreMap = new HashMap<>();

        for (Book book : books) {
            int score = 0;
            for (String genre : userGenres) {
                if (book.genres.contains(genre)) {
                    score++;
                }
            }
            scoreMap.put(book, score);
        }

        // Sort books by score in descending order
        List<Map.Entry<Book, Integer>> sortedEntries = new ArrayList<>(scoreMap.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Collect top recommendations
        List<Book> recommendations = new ArrayList<>();
        for (Map.Entry<Book, Integer> entry : sortedEntries) {
            if (entry.getValue() > 0) {
                recommendations.add(entry.getKey());
            }
        }

        return recommendations;
    }
}

public class Main {
    public static void main(String[] args) {
        // Sample books
        List<Book> books = Arrays.asList(
            new Book("The Great Gatsby", Arrays.asList("Classic", "Fiction")),
            new Book("To Kill a Mockingbird", Arrays.asList("Classic", "Drama")),
            new Book("1984", Arrays.asList("Dystopian", "Fiction")),
            new Book("The Catcher in the Rye", Arrays.asList("Classic", "Fiction")),
            new Book("Brave New World", Arrays.asList("Dystopian", "Fiction")),
            new Book("The Alchemist", Arrays.asList("Fiction", "Adventure"))
        );

        RecommendationSystem system = new RecommendationSystem(books);

        // User's preferred genres
        List<String> userGenres = Arrays.asList("Fiction", "Classic");

        // Get recommendations
        List<Book> recommendations = system.recommendBooks(userGenres);

        // Display recommendations
        System.out.println("Recommended Books:");
        for (Book book : recommendations) {
            System.out.println("- " + book.title);
        }
    }
}
