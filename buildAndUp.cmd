echo "generating image build"
docker build -t vuttr .

echo "starting containers"
docker-compose up -d