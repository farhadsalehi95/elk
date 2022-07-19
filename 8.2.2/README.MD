docker build -t (your_image_name) .

docker run -itd --name elasticsearch -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e discovery.type="single-node" (your_image_name)

docker exec -it elasticsearch bin/elasticsearch-reset-password -u elastic

docker exec elasticsearch curl -u elastic:(your_password) -k -XPUT 'https://localhost:9200/_license' -H 'Content-Type: application/json' -d @license.json


enjoyed :)
