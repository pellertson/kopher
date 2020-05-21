package kopher.rendering

import java.io.OutputStream
import kopher.config.HOST
import kopher.config.PORT

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

	val rendered: String
	get() = "$type$display\t$path\t$server\t$port\n"
}

fun renderGopherPage(entries: List<GopherEntry>, out: OutputStream) {
	for (entry in entries) {
		out.write(entry.rendered.toByteArray())
	}

	out.write(".\n".toByteArray())
}

fun renderGopherPage(entry: GopherEntry, out: OutputStream) =
	renderGopherPage(listOf(entry), out)
