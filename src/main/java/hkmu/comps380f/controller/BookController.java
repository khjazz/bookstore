package hkmu.comps380f.controller;

import hkmu.comps380f.dao.BookService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Photo;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("books", bookService.getBooks());
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "ticketForm", new bookForm());
    }

    public static class bookForm {
        private String name;
        private String author;
        private String description;
        private double price;
        private boolean availability;
        private MultipartFile photo;

        // Getters and Setters of name, author, description, price, availability, and photo
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isAvailability() {
            return availability;
        }

        public void setAvailability(boolean availability) {
            this.availability = availability;
        }

        public MultipartFile getPhoto() {
            return photo;
        }

        public void setPhoto(MultipartFile photo) {
            this.photo = photo;
        }

    }

    public static class commentForm {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @PostMapping("/create")
    public View create(bookForm form) throws IOException {
        long ticketId = bookService.createBook(form.getName(), form.getAuthor(),
                form.getDescription(), form.getPrice(),
                form.isAvailability(), form.getPhoto());
        return new RedirectView("/book/view/" + ticketId, true);
    }

    @GetMapping("/view/{bookId}")
    public ModelAndView view(@PathVariable("bookId") long bookId,
                       ModelMap model)
            throws BookNotFound {
        Book book = bookService.getBook(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return new ModelAndView("view", "commentForm", new commentForm());
    }

    @PostMapping("/{bookId}/comment")
    public String addComment(@PathVariable("bookId") long bookId,
                         commentForm form)
            throws BookNotFound {
        String content = form.getContent();
        bookService.addComment(bookId, content);
        return "redirect:/book/view/" + bookId;
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") long bookId)
            throws BookNotFound {
        bookService.delete(bookId);
        return "redirect:/book/list";
    }

    @GetMapping("/{bookId}/delete/{comment:.+}")
    public String deleteComment(@PathVariable("bookId") long bookId,
                                @PathVariable("comment") UUID commentId)
            throws BookNotFound, CommentNotFound {
        bookService.deleteComment(bookId, commentId);
        return "redirect:/book/view/" + bookId;
    }

    @GetMapping("/{bookId}/photo")
    public View getPhoto(@PathVariable("bookId") long bookId)
        throws BookNotFound, PhotoNotFound {
        Photo photo = bookService.getPhoto(bookId);

        return new DownloadingView(photo.getName(),
                photo.getMimeContentType(), photo.getContents());
    }


    @ExceptionHandler({BookNotFound.class, CommentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
