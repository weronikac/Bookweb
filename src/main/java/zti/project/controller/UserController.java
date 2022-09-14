package zti.project.controller;


import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zti.project.Utils.UserCredentials;
import zti.project.Utils.UserData;
import zti.project.model.Book;
import zti.project.model.User;
import zti.project.repository.BookRepository;
import zti.project.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


//    @GetMapping
//    public List<GetUserDto> getUsers() {
//        List<User> users = userRepository.findAll();
//        // convert List<User> to List<GetUserDto>
//        return users.stream().map(GetUserDto::new).collect(Collectors.toList());
//    }


//    @GetMapping("/{id}")
//    public GetUserDto getUser(@PathVariable("id") String id) {
//        return new GetUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id)));
//    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable("id") String id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @GetMapping("/{name}")
    public UserData getUserByName(@PathVariable("name") String name){
        return new UserData(userRepository.findByName(name));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserCredentials userCred) {
        System.out.println(userCred.getName());
        userCred.setPassword(passwordEncoder.encode(userCred.getPassword()));
        User user = userRepository.insert(new User(userCred));
        return user.getId();
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserCredentials userCred) {
        User user = userRepository.findByEmail(userCred.getEmail());
        if (passwordEncoder.matches(userCred.getPassword(), user.getPassword()))
            return ResponseEntity.ok(user.getId());
        else
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }


    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<List<Book>> addBookToList(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        List<Book> readBooks = user.getReadBooks();
        readBooks.add(book);
        user.setReadBooks(readBooks);
        userRepository.save(user);
        return ResponseEntity.ok(readBooks);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<List<Book>> removeBookFromList(@PathVariable("userId") String userId, @PathVariable("bookId") String bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        List<Book> readBooks = user.getReadBooks();
        readBooks.remove(book);
        user.setReadBooks(readBooks);
        userRepository.save(user);
        return ResponseEntity.ok(readBooks);
    }
}
