package com.example.resources;

import com.example.models.Book;
import com.example.services.BookService;
import com.example.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    BookService bookService = new BookService();

    @GET
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") int id) {
        return bookService.getBook(id);
    }

    @POST
    public Response addBook(Book book) {
        return bookService.addBook(book);
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book book) {
        return bookService.updateBook(id, book);
    }

    @GET
    @Path("/search")
    public List<Book> searchBooks(@QueryParam("name") String name) {
        return bookService.searchBooks(name);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        return bookService.deleteBook(id);
    }


}
