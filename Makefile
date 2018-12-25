# Java Makefile written by Jacques PEREIRA
# Project Katane - LO43

# Name of the output executable
OUT=Katane
MAIN=Katane

# Directory of the output
OUTPUT_DIR=bin

# Directory of the sources
SOURCE_DIR=src

# Name and directory of the final jar file
OUTPUT_EXEC=$(OUTPUT_DIR)/$(OUT).jar

# Flags
FLAGS_WARNING_SYNTAX=-Xlint:all
FLAGS_WARNING_DOCUMENTATION=-Xdoclint:all
FLAGS_WARNINGS=$(FLAGS_WARNING_SYNTAX) #$(FLAGS_WARNING_DOCUMENTATION)
FLAGS= $(FLAGS_WARNINGS)






ifeq ($(PACKAGE_TO_COMPILE_SOURCE),)
	# Case there is no Package to create - It's the first run
	PACKAGES=$(wildcard $(SOURCE_DIR)/*)

	PACKAGES_OUTPUT=$(patsubst $(SOURCE_DIR)%, $(OUTPUT_DIR)%,$(PACKAGES))
else

	# The output directory of the compilation of the package
	PACKAGE_TO_COMPILE_OUTPUT=$(patsubst $(SOURCE_DIR)%, $(OUTPUT_DIR)%,$(PACKAGE_TO_COMPILE_SOURCE))

	# Get the wildcard formula to access all the files to compile
	FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD=$(patsubst %, %/*.java,$(PACKAGE_TO_COMPILE_SOURCE))

	# Expend the wildcard
	FILES_IN_PACKAGES_WITH_SOURCE=$(wildcard $(FILES_IN_PACKAGES_WITH_SOURCE_WITH_WILDCARD))

	# Removes the source directory from the files
	FILES_IN_PACKAGES_WITHOUT_SOURCE=$(patsubst $(SOURCE_DIR)/%, %,$(FILES_IN_PACKAGES_WITH_SOURCE))

	# Substitutes .java by .class
	FILES_OUTPUT_WITHOUT_DESTINATION=$(patsubst %.java, %.class,$(FILES_IN_PACKAGES_WITHOUT_SOURCE))

	# Add the compiled files (output) directory as a their prefix
	FILES_OUTPUT_WITH_DESTINATION=$(patsubst %, $(OUTPUT_DIR)/%,$(FILES_OUTPUT_WITHOUT_DESTINATION))
endif


all: clean $(PACKAGES_OUTPUT) $(OUTPUT_EXEC)

$(OUTPUT_EXEC): $(FILES_OUTPUT_WITH_DESTINATION)
	jar cfe $@ $(MAIN) *.class


# # compile the sources .java files into the destination files .class
# $(OUTPUT_DIR)/%.class: $(SOURCE_DIR)/%.java
# 	@mkdir -p $(@D)
# 	javac $(FLAGS) -cp `echo "$<" | grep -P -o ".*\/"` -d `echo "$@" | grep -P -o ".*\/"` $<


# # Compile a package ???
# $(OUTPUT_DIR)/%: $(SOURCE_DIR)/%
# 	@echo $<
# 	$(MAKE) compilePackage PACKAGE_TO_COMPILE=$<

$(PACKAGES_OUTPUT): $(PACKAGES)
	$(foreach PACKAGE_TO_COMPILE_SOURCE,$(PACKAGES),@echo $(PACKAGE_TO_COMPILE_SOURCE);$(MAKE) compilePackage PACKAGE_TO_COMPILE_SOURCE=$(PACKAGE_TO_COMPILE_SOURCE))

compilePackage: $(FILES_OUTPUT_WITH_DESTINATION)
	@echo $<
$(FILES_OUTPUT_WITH_DESTINATION):
	@mkdir -p $(PACKAGE_TO_COMPILE_OUTPUT)
	javac $(FLAGS) -cp $(PACKAGE_TO_COMPILE_SOURCE) -d $(OUTPUT_DIR)/ $(FILES_IN_PACKAGES_WITH_SOURCE)




.PHONY: clean


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
