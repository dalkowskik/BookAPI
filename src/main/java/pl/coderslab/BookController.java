package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private MemoryBookService memoryBookService;

    @Autowired
    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @GetMapping("")
    public @ResponseBody
    List<Book> helloBooks() {
        return this.memoryBookService.getBooks();
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        memoryBookService.add(book);
    }


    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.memoryBookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not faund");
        });
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        memoryBookService.delete(id);
    }


    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        memoryBookService.update(book);
    }


}
