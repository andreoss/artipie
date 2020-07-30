set -x
set -e

# Start artipie.
docker run --name artipie -d -it -v $(pwd)/artipie.yaml:/etc/artipie.yml -v $(pwd):/var/artipie -p 8080:80 artipie/artipie:latest

# Wait for container to be ready for new connections.
sleep 5

# Build and upload python project to Artipie
cd sample-project
python3 -m pip install --user --upgrade setuptools wheel
python3 setup.py sdist bdist_wheel
python3 -m pip install --user --upgrade twine
python3 -m twine upload --repository-url http://localhost:8080/my-pypi -u username -p 123 --verbose dist/*
cd ..

curl -v http://localhost:8080/my-pypi/

# Install earlier uploaded python package from artipie.
python3 -m pip install -v --index-url http://localhost:8080/my-pypi --no-deps sample_project

# Remove container.
docker stop artipie
