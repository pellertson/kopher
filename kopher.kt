package kopher

import java.io.OutputStream
import java.io.File
import java.net.ServerSocket
import java.util.Scanner
import kopher.config.*
import kopher.rendering.*

fun main() {
	// connect to the port and set up a way to output to said port
	val server = ServerSocket(PORT)
	while (true) {
		val client = server.accept()
		val out = client.getOutputStream()
		val sc = Scanner(client.inputStream)

		val dir = sc.nextLine().trim()
		var file = File(CONTENT_HOME + dir)

		if (file.isFile()){
			renderFile(file, out)
		} else if (file.isDirectory()) {
			// try to find a “gophermap” file in the current directory
			file = File(file.getPath() + "/gophermap")
			if (file.isFile()) {
				renderGophermap(file, out)
			} else {
				renderDirectory(file.walk().maxDepth(1), out)
			}
		} else {
			renderErrorPage(out)
		}

		out.flush()
		out.close()
	}
}