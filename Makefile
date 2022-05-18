.PHONY: test docker-build docker-test docs

DOCKER_IMAGE := topologies

test:
	@echo "Running tests ..."
	@echo "---------------------------------------"
	mvn -q test

docker-test: docker-build
	docker run -it ${DOCKER_IMAGE}

docker-build:
	docker build -t ${DOCKER_IMAGE} .
