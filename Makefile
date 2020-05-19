SRC=gopher-server.kt
DEST=gopher-server.jar
KTCARGS=-include-runtime

all: build

build:
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm -r *.class *.kexe META-INF $(DEST)