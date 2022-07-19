docker build -t (your_image_name) .

create container from your image .

docker exec (your_container_name) url -XPUT 'http://localhost:9200/_license' -H "Content-Type: application/json" -d @lic.json

enjoyed :)
