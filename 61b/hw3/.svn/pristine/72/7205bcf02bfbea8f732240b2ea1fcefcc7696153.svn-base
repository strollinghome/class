
# All Java sources.  If you add more files, be sure to add them here as
# well.
SRCS = WeirdList.java WeirdListClient.java IntUnaryFunction.java WeirdListTest.java WeirdListClientTest.java

CLASSES = $(SRCS:.java=.class)

JFLAGS = -g



.PHONY: default style clean

default: $(CLASSES)

style:
	style61b $(SRCS)

clean:
	$(RM) *.class *~

check: WeirdListTest.class WeirdListClientTest.class
	java WeirdListTest WeirdListClienTest

$(CLASSES): $(SRCS)
	javac $(JFLAGS) $(SRCS)
