<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Book Library</h1>
    <div id="book-list">
        <h2>Book List</h2>
        <ul id="books"></ul>
    </div>
    <div id="add-book">
        <h2>Add Book</h2>
        <form id="book-form">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
            <label for="author">Author:</label>
            <input type="text" id="author" name="author" required>
            <label for="publishedDate">Published Date:</label>
            <input type="date" id="publishedDate" name="publishedDate">
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" name="isbn">
            <button type="submit">Add Book</button>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>
