# Compiler and flags
JCC = javac
JFLAGS = -g

# Java files to compile
JAVA_FILES = Restaurant6431.java Diner.java Utils.java

# .class files corresponding to the Java files
CLASS_FILES = $(JAVA_FILES:.java=.class)

# Default target
all: $(CLASS_FILES)

# Compile java files to class files
%.class: %.java
	$(JCC) $(JFLAGS) $<

# Test the project with an input file
test: all
ifeq ($(words $(MAKECMDGOALS)),2)
	@echo "Running the program with $(filter-out $@,$(MAKECMDGOALS))..."
	java Restaurant6431 < $(filter-out $@,$(MAKECMDGOALS))
else
	@echo "Please specify an input file. Usage: make test yourfile.txt"
endif

# Trick to accept filename as command argument
%:
	@:

# Clean up
clean:
	$(RM) $(CLASS_FILES)

