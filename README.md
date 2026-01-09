# Study-Notes
Study Notes App:
A simple Android app to create, view, and manage study notes. Written in Java using SQLite for storage.

-Features:
Add new notes with title and content.
View all notes in a list.
Data is stored in SQLite database.
Typed notes are preserved during rotation or configuration changes.

Project Structure:
MainActivity.java → Displays all notes in a ListView.
NoteActivity.java → Add a new note.
DBHelper.java → Handles SQLite database operations.
res/layout/activity_main.xml → Main screen layout.
res/layout/activity_note.xml → Add note screen layout.
res/layout/item_note.xml → Layout for a single note in the list.
