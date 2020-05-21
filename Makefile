SRC=gopher-server.kt config.kt
DEST=gopher-server.jar
KTCARGS=-include-runtime -opt

all: build

build:
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm $(DEST)