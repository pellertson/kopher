import java.io.OutputStream
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.net.ServerSocket

const val PORT = 70
const val HOST = "localhost"

// i don't know if if need this yet but it's here
class GopherEntry(
		val type: Char,
		val display: String,
		val path: String,
		val server: String,
		val port: Int) {

	constructor(type: Char, display: String):
		this(type, display, "")

	constructor(type: Char, display: String, path: String) :
		this(type, display, path, HOST, PORT)

	constructor(type: Char, display: String, path: String, host: String):
		this(type, display, path, host, PORT)
}

val GopherEntry.rendered: String
	get() = "$type$display\t$path\t$server\t$port\r\n"

fun renderGopherPage(entries: List<GopherEntry>, out: OutputStream) {
	for (entry in entries) {
		out.write(entry.rendered.toByteArray())
	}
	out.flush()
	out.close()
}

fun main() {
	var entries = listOf(
		GopherEntry('1', "Test display string", "/"),
		GopherEntry('0', "About me", "/about-me.txt"),
		GopherEntry('1', "Look here", "/", "bitreich.org"),
		GopherEntry('i', "A comment")
	)
	renderGopherPage(entries, FileOutputStream(FileDescriptor.out))
}