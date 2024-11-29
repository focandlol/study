docker network create redis-net

docker run --name fintect-redis \
-p 6378:6379 \
--network redis-net \
-d redis:latest