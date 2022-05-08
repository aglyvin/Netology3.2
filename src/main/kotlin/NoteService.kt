import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException

class NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()

    private var nextId: Int = 1

    fun add(userId: Int, title: String, text: String): Note {
        val note = Note(nextId++, userId, title, text)
        notes += note
        return note
    }

    fun createComment(noteId: Int, message: String): Comment {
        val comment = Comment(nextId++, noteId, message)
        comments += comment
        return comment
    }

    fun deleteComment(id: Int) {
        getCommentById(id).deleted = true
    }

    fun getById(id: Int): Note {
        return notes.firstOrNull{it.id == id} ?:throw NoteNotFoundException()
    }

    private fun getCommentById(id: Int): Comment {
        return comments.firstOrNull{it.id == id} ?: throw CommentNotFoundException()
    }

    fun delete(id: Int) {
        val foundPost = getById(id)
        foundPost.deleted = true
    }

    fun edit(note: Note) {
        val foundPost = getById(note.id)
        foundPost.text = note.text
        foundPost.title = note.title
    }

    fun get(noteIds: List<Int>, userId: Int, offset: Int = 0, count: Int = 20): List<Note> {
        val res = mutableListOf<Note>()
        var offSetCount = offset
        for(note in notes) {
            if (noteIds.contains(note.id) && (userId == note.userId)) {
                if (offSetCount > 0) offSetCount-- else res += note
            }
            if (res.count() == count) return res
            }
        return res
    }

    fun getComments(noteId: Int): List<Comment> {
        val res = mutableListOf<Comment>()
        for(comment in comments) {
            if (comment.noteId == noteId) {
                res += comment
            }
        }
        return res
    }
}