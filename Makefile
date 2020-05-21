SRC=gopher-server.kt config.kt render.kt
DEST=gopher-server.jar
KTCARGS=-include-runtime

all: build

build:
	[ -f config.kt ] || cp config.kt.def config.kt
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm $(DEST)