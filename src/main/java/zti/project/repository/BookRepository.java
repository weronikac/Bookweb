package zti.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zti.project.model.Book;
import zti.project.model.User;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByIsbn(String isbn);
}
