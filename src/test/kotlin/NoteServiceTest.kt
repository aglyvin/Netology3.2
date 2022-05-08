import exceptions.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*
private val NoteService = NoteService()

class NoteServiceTest {

    @Test
    fun add() {
        val note = NoteService.add(0, "Test note", "Lorem ipsum...")
        assertNotEquals(0, note.id)
    }

    @Test
    fun createComment() {
        val note = NoteService.add(0, "Test note", "Lorem ipsum...")
        NoteService.createComment(note.id, "Comment")
        assertEquals(NoteService.getComments(note.id).count(), 1)
    }

    @Test
    fun deleteComment() {
        val note = NoteService.add(0, "Test note", "Lorem ipsum...")
        val comment = NoteService.createComment(note.id, "Test comment")

        NoteService.deleteComment(comment.id)
        assertTrue(comment.deleted)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getById() {
        NoteService.getById(5)
    }

    @Test
    fun delete() {
        val note = NoteService.add(0, "Test note", "Lorem ipsum...")
        NoteService.delete(note.id)
        assertTrue(note.deleted)
    }

    @Test
    fun edit() {
        val note1 = NoteService.add(0, "Test note", "Lorem ipsum...")
        val note2 = Note(note1.id, 0, "New note", "Text changed")
        NoteService.edit(note2)
        assertEquals(note1.text, "Text changed")

    }

    @Test
    fun get() {
        val note1 = NoteService.add(0, "Test note", "Lorem ipsum...")
        val note2 = NoteService.add(0, "Test note", "Lorem ipsum...")
        val note3 = NoteService.add(0, "Test note", "Lorem ipsum...")

        val notes = NoteService.get(listOf<Int>(note1.id, note2.id, note3.id), 0, 0, 2)
        assertEquals(2, notes.count())

    }

    @Test
    fun getComments() {
        val note = NoteService.add(0, "Test note", "Lorem ipsum...")
        NoteService.createComment(note.id, "Test comment 1")
        NoteService.createComment(note.id, "Test comment 2")
        NoteService.createComment(note.id, "Test comment 3")
        assertEquals(3, NoteService.getComments(note.id).count())
    }
}