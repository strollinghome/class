#defines where all source files are
SRCS := $(wildcard HashSet/*.java) $(wildcard Sorting/*.java) $(wildcard Intervals/*.java)

CLASSES = $(SRCS:.java=.class)

JFLAGS = -g

default: elephant\ in\ Cairo

elephant\ in\ Cairo: $(CLASSES)

clean:
	$(RM) *.class HashSet/*.class Sorting/*.class Intervals/*.class

style:
	style61b $(SRCS)

$(CLASSES):
	javac $(JFLAGS) $(SRCS)
	touch elephant\ in\ Cairo

.PHONY: default elephant\ in\ Cairo
