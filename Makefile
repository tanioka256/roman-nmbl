.PHONY: clean
.SUFFIXES: .class .java

.java.class:
	javac -classpath /usr/share/java/itext5.jar $<

all: RomanNmbl.class

clean:
	${RM} RomanNmbl.class
