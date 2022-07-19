FROM docker.elastic.co/elasticsearch/elasticsearch:7.12.0 AS base
FROM openjdk:8-jdk as jdk
ENV ELKHOME=/usr/share/elasticsearch
ENV ELKVERSION=7.12.0
COPY --from=base ${ELKHOME}/lib/elasticsearch-${ELKVERSION}.jar ${ELKHOME}/lib/elasticsearch-core-${ELKVERSION}.jar ${ELKHOME}/lib/lucene-core-8.8.0.jar ${ELKHOME}/modules/x-pack-core/x-pack-core-${ELKVERSION}.jar /
COPY XPackBuild.java LicenseVerifier.java / 
RUN javac -cp "elasticsearch-${ELKVERSION}.jar:lucene-core-8.8.0.jar:x-pack-core-${ELKVERSION}.jar:elasticsearch-core-${ELKVERSION}.jar" LicenseVerifier.java && javac -cp "elasticsearch-${ELKVERSION}.jar:lucene-core-8.8.0.jar:x-pack-core-${ELKVERSION}.jar:elasticsearch-core-${ELKVERSION}.jar" XPackBuild.java
RUN mkdir /farhad && cp /x-pack-core-${ELKVERSION}.jar /farhad
WORKDIR /farhad
RUN jar -xvf x-pack-core-${ELKVERSION}.jar
RUN cp /XPackBuild.class /farhad/org/elasticsearch/xpack/core/ && cp /LicenseVerifier.class /farhad/org/elasticsearch/license/
RUN rm -rf x-pack-core-${ELKVERSION}.jar && jar -cvf x-pack-core-${ELKVERSION}.jar .
FROM docker.elastic.co/elasticsearch/elasticsearch:7.12.0
ENV ELKHOME=/usr/share/elasticsearch
ENV ELKVERSION=7.12.0
RUN rm -rf ${ELKHOME}/modules/x-pack-core/x-pack-core-${ELKVERSION}.jar
COPY --from=jdk /farhad/x-pack-core-${ELKVERSION}.jar ${ELKHOME}/modules/x-pack-core/
COPY lic.json lic.json
