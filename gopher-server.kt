package kopher

import java.io.OutputStream
import java.io.File
import java.net.ServerSocket
import java.util.Scanner
import kopher.config.*
import kopher.rendering.*

// i don't know if if need this yet but it's here

fun main() {
	// just for testing purposes
	val entries = listOf(
		GopherEntry('1', "Test display string", "/"),
		GopherEntry('0', "About me", "/about-me.txt"),
		GopherEntry('1', "Look here", "/", "bitreich.org"),
		GopherEntry('i', "A comment"),
		GopherEntry('0', "A Makefile", "/Makefile")
	)

	// connect to the port and set up a way to output to said port
	val server = ServerSocket(PORT)
	while (true) {
		val client = server.accept()
		val out = client.getOutputStream()
		val sc = Scanner(client.inputStream)

		val dir = sc.nextLine()
		val file = File(CONTENT_HOME + dir)

		if (file.exists()){
			renderFile(file, out)
		} else {
			renderGopherPage(entries, out)
		}

		out.flush()
		out.close()
	}
}