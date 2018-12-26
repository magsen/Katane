# Java Makefile written by Jacques PEREIRA
# Project Katane - LO43

# Name of the output executable
OUT=Katane
MAIN=Katane.Launcher #Launcher.class

# Directory of the output
OUTPUT_DIR=bin

# Directory of the sources
SOURCE_DIR=src

# Name and directory of the final jar file
OUTPUT_EXEC=$(OUTPUT_DIR)/$(OUT).jar

# −−−−− FLAGS −−−−−
FLAGS_WARNING_SYNTAX=-Xlint:all
FLAGS_WARNING_DOCUMENTATION=-Xdoclint:all # This forces the documentation
FLAGS_WARNINGS=$(FLAGS_WARNING_SYNTAX) $(FLAGS_WARNING_DOCUMENTATION)
FLAGS= $(FLAGS_WARNINGS) $(DEBUG)


ifeq ($(PACKAGE_TO_COMPILE_SOURCE),) # Case there is no Package to create - It's the first run

	# List the packages directory
	PACKAGES=$(wildcard $(SOURCE_DIR)/*)

	# List of the packages output directory
	PACKAGES_OUTPUT=$(patsubst $(SOURCE_DIR)%, $(OUTPUT_DIR)%,$(PACKAGES))

	# Get the wildcard formula to access all the files in all packages to compile
	FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD=$(patsubst %, %/*.java,$(PACKAGES))
else

	# The output directory of the compilation of the package
	PACKAGE_TO_COMPILE_OUTPUT=$(patsubst $(SOURCE_DIR)%, $(OUTPUT_DIR)%,$(PACKAGE_TO_COMPILE_SOURCE))

	# Get the wildcard formula to access all the specific package to compile
	FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD=$(patsubst %, %/*.java,$(PACKAGE_TO_COMPILE_SOURCE))
endif

# Expend the wildcard
FILES_IN_PACKAGES_WITH_SOURCE=$(wildcard $(FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD))

# Removes the source directory from the files
FILES_IN_PACKAGES_WITHOUT_SOURCE=$(patsubst $(SOURCE_DIR)/%, %,$(FILES_IN_PACKAGES_WITH_SOURCE))

# Substitutes .java by .class
FILES_OUTPUT_WITHOUT_DESTINATION=$(patsubst %.java, %.class,$(FILES_IN_PACKAGES_WITHOUT_SOURCE))

# Add the compiled files (output) directory as a their prefix
FILES_OUTPUT_WITH_DESTINATION=$(patsubst %, $(OUTPUT_DIR)/%,$(FILES_OUTPUT_WITHOUT_DESTINATION))


# clean compile and compress
all: clean $(PACKAGES_OUTPUT) $(OUTPUT_EXEC) exec

compile: clean $(PACKAGES_OUTPUT) $(OUTPUT_EXEC)

exec: $(OUTPUT_EXEC)
	java -cp $(OUTPUT_DIR) -jar $<

# Compress into a .jar ( used in the main make)
$(OUTPUT_EXEC): $(FILES_OUTPUT_WITH_DESTINATION)
	(cd $(OUTPUT_DIR);jar cfe $(OUT).jar $(MAIN) $(FILES_OUTPUT_WITHOUT_DESTINATION))

# Instanciate a new submake for each package to compile it individually
$(PACKAGES_OUTPUT): $(PACKAGES)
	$(foreach PACKAGE_TO_COMPILE_SOURCE,$(PACKAGES),@echo "\n\n−−−−− COMPILE $(PACKAGE_TO_COMPILE_SOURCE) −−−−−\n\n";$(MAKE) compilePackage PACKAGE_TO_COMPILE_SOURCE=$(PACKAGE_TO_COMPILE_SOURCE))

# Compile the sources for the specified package (used in submake)
compilePackage: $(FILES_OUTPUT_WITH_DESTINATION) # This one is just to call the other properly
$(FILES_OUTPUT_WITH_DESTINATION):
	javac $(FLAGS) -cp $(OUTPUT_DIR) -d $(OUTPUT_DIR) $(FILES_IN_PACKAGES_WITH_SOURCE)


debug: clean
	@echo "Launch compilation in DEBUG mode\n--------------------------------"
	$(MAKE) DEBUG="-g" # Launch a sub-Makefile with debug


.PHONY: clean print

# Compile with debug flags on


# Clean outputs
clean:
	rm -rf $(OUTPUT_EXEC) $(FILES_OUTPUT_WITH_DESTINATION) $(PACKAGES_OUTPUT)

# Some printing to debug the Makefile
print:
	@echo "−−−−− FILES TO COMPILE - WITH SOURCE PATH - WILDCARD FORMULA −−−−−"
	@echo '$(FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD)'
	@echo "\n−−−−− FILES TO COMPILE - WITH SOURCE PATH −−−−−"
	@echo $(FILES_IN_PACKAGES_WITH_SOURCE)
	@echo "\n−−−−− FILES TO COMPILE - WITHOUT SOURCE PATH −−−−−"
	@echo $(FILES_OUTPUT_WITH_DESTINATION)
	@echo "\n−−−−− FILES OUTPUT - WITHOUT SOURCE PATH −−−−−"
	@echo $(FILES_OUTPUT_WITHOUT_DESTINATION)
	@echo "\n−−−−− FILES OUTPUT - WITH DESTINATION PATH −−−−−"
	@echo $(FILES_IN_PACKAGES_WITHOUT_SOURCE)
