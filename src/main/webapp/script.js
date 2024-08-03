document.addEventListener('DOMContentLoaded', () => {
    const bookForm = document.getElementById('book-form');
    const bookList = document.getElementById('books');

    // Fetch and display books
    function fetchBooks() {
        fetch('/api/books')
            .then(response => response.json())
            .then(books => {
                bookList.innerHTML = '';
                books.forEach(book => {
                    const li = document.createElement('li');
                    li.textContent = `${book.title} by ${book.author}`;
                    bookList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching books:', error));
    }

    // Add a new book
    bookForm.addEventListener('submit', event => {
        event.preventDefault();
        const formData = new FormData(bookForm);
        const book = {
            title: formData.get('title'),
            author: formData.get('author'),
            publishedDate: formData.get('publishedDate'),
            isbn: formData.get('isbn')
        };

        fetch('/api/books', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
            .then(response => response.json())
            .then(newBook => {
                fetchBooks();
                bookForm.reset();
            })
            .catch(error => console.error('Error adding book:', error));
    });

    fetchBooks();
});