// i don't know if if need this yet but it's here
class GopherEntry(
	val type: Char,
	val display: String,
	val path: String,
	val server: String,
	val port: Int)

fun renderGopherPage(entries: List<GopherEntry>) {
	for (entry in entries) {
		with (entry) {
			print("$type$display\t$path\t$server\t$port\r\n")
		}
	}
}

fun main() {
	var entries = listOf(
		GopherEntry('1', "Test display string", "/", "localhost", 70),
		GopherEntry('0', "About me", "/about-me.txt", "localhost", 70),
		GopherEntry('1', "Look here", "/", "bitreich.org", 70)
	)

	renderGopherPage(entries)
}