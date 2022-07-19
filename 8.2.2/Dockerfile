FROM docker.elastic.co/elasticsearch/elasticsearch:8.2.2 AS base
FROM openjdk as jdk
ENV ELKHOME=/usr/share/elasticsearch
ENV ELKVERSION=8.2.2
COPY --from=base ${ELKHOME}/lib/elasticsearch-${ELKVERSION}.jar ${ELKHOME}/lib/elasticsearch-core-${ELKVERSION}.jar ${ELKHOME}/lib/lucene-core-9.1.0.jar ${ELKHOME}/modules/x-pack-core/x-pack-core-${ELKVERSION}.jar /
COPY LicenseVerifier.java / 
RUN javac -cp "elasticsearch-${ELKVERSION}.jar:lucene-core-8.8.0.jar:x-pack-core-${ELKVERSION}.jar:elasticsearch-core-${ELKVERSION}.jar" LicenseVerifier.java 
RUN mkdir /farhad && cp /x-pack-core-${ELKVERSION}.jar /farhad
WORKDIR /farhad
RUN jar -xvf x-pack-core-${ELKVERSION}.jar
RUN cp /LicenseVerifier.class /farhad/org/elasticsearch/license/
RUN rm -rf x-pack-core-${ELKVERSION}.jar && jar -cvf x-pack-core-${ELKVERSION}.jar .
FROM docker.elastic.co/elasticsearch/elasticsearch:8.2.2
ENV ELKHOME=/usr/share/elasticsearch
ENV ELKVERSION=8.2.2
USER root
RUN rm -rf ${ELKHOME}/modules/x-pack-core/x-pack-core-${ELKVERSION}.jar
USER elasticsearch
COPY --from=jdk /farhad/x-pack-core-${ELKVERSION}.jar ${ELKHOME}/modules/x-pack-core/
COPY license.json license.json
