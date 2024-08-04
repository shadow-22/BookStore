package com.example.services;

import com.example.models.Book;
import com.example.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookService {

    public List<Book> getBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Book> books = session.createQuery("FROM Book", Book.class).list();
        session.close();
        return books;
    }

    public Book getBook(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public Response addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
        return Response.ok(book).build();
    }

    public Response updateBook(int id, Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Book existingBook = session.get(Book.class, id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublishedDate(book.getPublishedDate());
            existingBook.setIsbn(book.getIsbn());
            session.update(existingBook);
            transaction.commit();
            session.close();
            return Response.ok(existingBook).build();
        } else {
            session.close();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response deleteBook(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            session.delete(book);
            transaction.commit();
            session.close();
            return Response.ok().build();
        } else {
            session.close();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
