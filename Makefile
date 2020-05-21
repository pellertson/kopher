SRC=gopher-server.kt config.kt render.kt
DEST=gopher-server.jar
KTCARGS=-include-runtime

all: build

build:
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm $(DEST)