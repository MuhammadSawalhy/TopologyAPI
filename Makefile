MAIN_CLASS = mastermicro.topologies.Main

.PHONY: run tests

run:
	@echo "Running ${MAIN_CLASS} ..."
	@echo "---------------------------------------"
	@mvn -e -q exec:java -Dexec.mainClass=${MAIN_CLASS}

test:
	@echo "Running tests ..."
	@echo "---------------------------------------"
	@mvn -q test
