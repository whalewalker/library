package com.library.library.service;

import com.cloudinary.utils.ObjectUtils;
import com.library.library.domain.dto.BookDto;
import com.library.library.domain.models.Book;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.domain.repository.BookRepository;
import com.library.library.domain.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudStorageService cloudStorageService;


    @Override
    public Optional<Book> getBookByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book uploadBook(BookDto bookDto) {

        return null;
    }

    @Override
    public Book updateBook(String bookId, BookDto bookDto) {
        if (bookDto  == null) throw new NullPointerException("Book cannot be null");
        Book book = new Book();

        if (bookDto.getCoverImage() != null && !bookDto.getCoverImage().isEmpty()){
            try {
                Map<?, ?> uploadResult = cloudStorageService.uploadImage(bookDto.getCoverImage(), ObjectUtils.asMap(
                        "library/coverImages" + extractFileName(Objects.requireNonNull(bookDto.getCoverImage().getOriginalFilename()))
                ));

                book.setCoverImage(String.valueOf(uploadResult.get("url")));
                log.info("Image url from cloudinary --> {}", uploadResult.get("url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        book.setBookUrl("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");
        book.setPublisher("Impact");

       try {
           return bookRepository.save(book);
       }catch (DataIntegrityViolationException err){
           log.info("Exception occurred --> {}", err.getMessage());
           throw err;
       }
    }

    private String extractFileName(String filename){
        return filename.split("\\.")[0];
    }

    @Override
    public Book deleteBookById(String bookId) {
        return null;
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        return null;
    }

    @Override
    public List<Book> getBookByCategory(String categoryName) {
        return null;
    }
}
