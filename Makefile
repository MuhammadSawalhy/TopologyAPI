.PHONY: test docker-build docker-test docs site analysis

DOCKER_IMAGE := topologies

test:
	mvn test

site:
	mvn site

analysis:
	mvn checkstyle:checkstyle

docs:
	mvn javadoc:javadoc

docker-test: docker-build
	docker run -it ${DOCKER_IMAGE}

docker-build:
	docker build -t ${DOCKER_IMAGE} .


