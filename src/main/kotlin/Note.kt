class Note(
    val id: Int,
    val userId: Int,
    var title: String,
    var text: String,
    var deleted: Boolean = false
)