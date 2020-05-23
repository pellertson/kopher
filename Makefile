SRC=kopher.kt config.kt render.kt
DEST=kopher.jar
KTCARGS=-include-runtime

all: build

build:
	[ -f config.kt ] || cp config.kt.def config.kt
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm $(DEST)