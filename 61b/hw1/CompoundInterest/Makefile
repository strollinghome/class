#    gmake	      # Defaults to the first target: default
#    gmake default    # Same as gmake compile.
#    gmake compile    # Compiles Java code in this directory.
#    gmake style      # Runs style checks (only on instructional machines)
#    gmake check      # Compile if needed and test using HW1Test.
#    gmake clean      # Remove extraneous or rebuildable files

# Define $(SRCS) to be a list of Java files.
SRCS = CompoundInterest.java CompoundInterestTest.java

#TODO: fix this xml
STYLE_SUPPRESS = $(MASTERDIR)/testing-files/hw1-suppress.xml

# The targets following .PHONY aren't actually files; they serve as commands.
.PHONY: compile default check clean style

# By default, compile all sources
default: compile

# To compile all source files, just bring the file YearCheck.class up to date.
compile: CompoundInterestTest.class

# Run our style checks.
style:
	if [ -f hw1-suppress.xml ]; then \
	    style61b -s hw1-suppress.xml $(SRCS); \
	else \
	    style61b -s $(STYLE_SUPPRESS) $(SRCS); \
	fi

# Test the compiled program, after first making sure it is up-to-date.
check: CompoundInterestTest.class
	java CompoundInterestTest 

# Remove extraneous or reconstructable files.
clean:
	rm -f *.class *~

CompoundInterestTest.class: $(SRCS) 
	javac -g $(SRCS)
