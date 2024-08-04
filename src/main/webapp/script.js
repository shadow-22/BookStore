document.addEventListener('DOMContentLoaded', () => {
    const bookForm = document.getElementById('book-form');
    const bookList = document.getElementById('books');
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');
    const searchResults = document.getElementById('searchResults');

    // Fetch and display books
    function fetchBooks() {
        fetch('http://localhost:9090/jaxrs-api/api/books')
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

    // Search a book
    function searchBooks(name) {
        fetch(`http://localhost:9090/jaxrs-api/api/books/search?name=${encodeURIComponent(name)}`)
            .then(response => response.json())
            .then(data => {
                console.log("Search results:", data);
                searchResults.innerHTML = '';
                data.forEach(book => {
                    const li = document.createElement('li');
                    li.textContent = `${book.title} by ${book.author}`;
                    searchResults.appendChild(li);
                });
            })
            .catch(error => {
                console.error("Error searching books:", error);
            });
    }

    function performSearch() {
        const name = document.getElementById('searchInput').value;
        searchBooks(name);
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

        fetch('http://localhost:9090/jaxrs-api/api/books', {
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

    // Event listener for the search button
    searchButton.addEventListener('click', performSearch);
});
