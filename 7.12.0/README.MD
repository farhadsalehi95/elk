docker build -t (your_image_name) .

docker run -itd --name elasticsearch -p 9200:9200 -p 9300:9300 -e discovery.type=single-node -e ES_JAVA_OPTS="-Xms512m -Xmx512m" (your_image_name)


docker exec elasticsearch curl -XPUT 'http://localhost:9200/_license' -H "Content-Type: application/json" -d @lic.json

enjoyed :)
