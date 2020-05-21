package kopher

import java.io.OutputStream
import java.io.File
import java.net.ServerSocket
import java.util.Scanner
import kopher.config.*

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

	val rendered: String
	get() = "$type$display\t$path\t$server\t$port\r\n"
}

fun renderGopherPage(entries: List<GopherEntry>, out: OutputStream) {
	for (entry in entries) {
		out.write(entry.rendered.toByteArray())
	}
}

fun renderGopherPage(entry: GopherEntry, out: OutputStream) {
	out.write(entry.rendered.toByteArray())
}

fun main() {
	// just for testing purposes
	val entries = listOf(
		GopherEntry('1', "Test display string", "/"),
		GopherEntry('0', "About me", "/about-me.txt"),
		GopherEntry('1', "Look here", "/", "bitreich.org"),
		GopherEntry('i', "A comment")
	)

	// connect to the port and set up a way to output to said port
	val server = ServerSocket(PORT)
	val client = server.accept()
	val out = client.getOutputStream()
	val sc = Scanner(client.inputStream)

	val dir = sc.nextLine()
	val file = File(CONTENT_HOME + dir)

	if (!file.exists()){
		renderGopherPage(entries, out)
	} else {
		renderGopherPage(listOf(GopherEntry('3', "Error")), out)
	}

	out.flush()
	out.close()
}