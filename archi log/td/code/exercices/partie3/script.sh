#!/bin/bash

# Get all posts
curl https://jsonplaceholder.typicode.com/posts

# Get post with id 10
curl https://jsonplaceholder.typicode.com/posts/10

# Get post with id -1 (non-existing post)
response=$(curl --write-out %{http_code} --silent --output /dev/null https://jsonplaceholder.typicode.com/posts/-1)
echo "Response code for non-existing post: $response"

# Simulate deleting an existing post
curl -X DELETE https://jsonplaceholder.typicode.com/posts/10

# Simulate deleting a non-existing post
response=$(curl -X DELETE --write-out %{http_code} --silent --output /dev/null https://jsonplaceholder.typicode.com/posts/-1)
echo "Response code for deleting non-existing post: $response"

# Simulate updating an existing post
curl -X PUT https://jsonplaceholder.typicode.com/posts/10 -H "Content-Type: application/json" -d '{"title": "Updated title", "body": "Updated body"}'
