SRCS = *.java

CLASSES = $(SRCS:.java=.class)

sentinel: $(CLASSES)

clean:
	$(RM) *~ *.class

$(CLASSES):
	javac $(SRCS)
