include config.mk

all: build

build:
	kotlinc $(SRC) $(KTCARGS) -d $(DEST)

clean:
	rm -r *.class *.kexe META-INF $(DEST)