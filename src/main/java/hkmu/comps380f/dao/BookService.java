package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Resource
    private BookRepository bookRepo;

    @Resource
    private CommentRepository commentRepo;

    @Resource
    private PhotoRepository photoRepo;

    @Transactional
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @Transactional
    public Book getBook(long id)
            throws BookNotFound {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFound(id);
        }
        return book;
    }

    @Transactional
    public Photo getPhoto(long bookId)
            throws BookNotFound, PhotoNotFound {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        Photo photo = book.getPhoto();

        return photo;
    }

    @Transactional(rollbackFor = BookNotFound.class)
    public void delete(long id) throws BookNotFound {
        Book deletedBook = bookRepo.findById(id).orElse(null);
        if (deletedBook == null) {
            throw new BookNotFound(id);
        }
        bookRepo.delete(deletedBook);
    }

    @Transactional(rollbackFor = CommentNotFound.class)
    public void deleteComment(long bookId, UUID commentId)
            throws BookNotFound, CommentNotFound {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        for (Comment comment : book.getComments()) {
            if (comment.getId().equals(commentId)) {
                book.deleteComment(comment);
                bookRepo.save(book);
                return;
            }
        }
        throw new CommentNotFound(commentId);
    }

    @Transactional
    public long createBook(String name, String author, String description,
                           double price, boolean availability, MultipartFile filePart)
            throws IOException {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        book.setAvailability(availability);

        Photo photo = new Photo();
        photo.setName(filePart.getOriginalFilename());
        photo.setMimeContentType(filePart.getContentType());
        photo.setContents(filePart.getBytes());

        if (photo.getName() != null && photo.getName().length() > 0
                && photo.getContents() != null
                && photo.getContents().length > 0) {
            photo.setBook(book);
            book.setPhoto(photo);
        }

        Book savedBook = bookRepo.save(book);
        return savedBook.getId();
    }

    @Transactional
    public void addComment(long bookId, String content)
        throws BookNotFound {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setContent(content);

        commentRepo.save(comment);
    }

}
