EMPTY      :=
SPACE      := $(EMPTY) $(EMPTY)
NL         := $(EMPTY)\\n$(EMPTY)

LABS       := $(sort $(wildcard lab??-* testlib))

default:
	@echo "**** PROGC Labs ****"
	@echo "$(subst $(SPACE),$(NL),$(LABS))"
	@echo ""
	@echo "**** Prerequisites ****"
	@echo "1. Change into the testlib directory"
	@echo "       cd testlib"
	@echo "2. Build and install the library, e.g."
	@echo "       make clean"
	@echo "       make default"
	@echo "       make test"
	@echo "       make install"
	@echo "       make doc"
	@echo "   Caution: make sure the tests, installation and documentation does not produce any error."
	@echo "3. View the produced documentation, e.g."
	@echo "       firefox doc/index.html"
	@echo ""
	@echo "**** How to build and run a lab? ****"
	@echo "1. Change into the respective directory, e.g."
	@echo "       cd $(firstword $(LABS))"
	@echo "2. Build the lab, e.g."
	@echo "       make"
	@echo "   The resulting executable is located in the bin folder."
	@echo "3. Build and run the tests, e.g."
	@echo "       make test"
	@echo "4. Build the HTML documentation from the sources, e.g."
	@echo "       make doc"
	@echo "   The produced HTML documentation is located in the doc folder. Open the index.html file in a HTML browser."
	@echo "Notes:"
	@echo "- You may cleanup the builds, e.g."
	@echo "       make clean"
