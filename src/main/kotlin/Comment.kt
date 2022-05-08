class Comment(
    val id: Int,
    val noteId: Int,
    var message: String,
    var deleted: Boolean = false
)