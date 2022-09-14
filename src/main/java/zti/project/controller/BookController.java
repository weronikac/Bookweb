package zti.project.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.project.Utils.BookData;
import zti.project.Utils.ReviewData;
import zti.project.Utils.UserData;
import zti.project.model.Book;
import zti.project.model.Review;
import zti.project.model.User;
import zti.project.repository.BookRepository;
import zti.project.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addBook(@RequestBody BookData bookData){
        Book book = bookRepository.insert(new Book(bookData));
        return book.getId();
    }

    @GetMapping
    public List<BookData> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookData::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public  BookData getBookById(@PathVariable("id") String id){
        return new BookData(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }

    @GetMapping("/author/{author}")
    public  List<BookData> getBooksByAuthor(@PathVariable("author") String author){
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream().map(BookData::new).collect(Collectors.toList());
    }

    @GetMapping("/title/{title}")
    public  List<BookData> getBooksByTitle(@PathVariable("title") String title){
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream().map(BookData::new).collect(Collectors.toList());
    }

    @GetMapping("isbn/{isbn}")
    public  List<BookData> getBooksByIsbn(@PathVariable("isbn") String isbn){
        List<Book> books = bookRepository.findByIsbn(isbn);
        return books.stream().map(BookData::new).collect(Collectors.toList());
    }

    @PostMapping("/addReview/{userId}/{bookId}")
    public ResponseEntity<List<Review>> addReview(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId, @RequestBody ReviewData reviewData){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Review review = new Review();
        review.setUser(user.getName());
        review.setContent(reviewData.getContent());
        List<Review> reviews = book.getReviews();
        reviews.add(review);
        book.setReviews(reviews);
        bookRepository.save(book);
        return ResponseEntity.ok(reviews);
    }
}
