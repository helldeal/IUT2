#-k si le certificat ne peut-être vérifié
#Ensemble des posts
curl -k  'https://jsonplaceholder.typicode.com/posts'

#Un post de votre choix existant
curl -k  'https://jsonplaceholder.typicode.com/posts/1'

#Un post de votre choix existant, code 404
curl -k  'https://jsonplaceholder.typicode.com/posts/-1'

#suppression d'un post inexistant, code 200
curl -k  -v -X DELETE 'https://jsonplaceholder.typicode.com/posts/10'

#suppression d'un post inexistant, code 200
curl -k  -v -X DELETE 'https://jsonplaceholder.typicode.com/posts/101'

#creation d'un article non existant, code 201
data='{"userId": 1,"id": 101,"title": "sunt aut ","body": "quia et"}'
curl -k  -v -H "Content-Type: application/json" -d $data -X POST 'https://jsonplaceholder.typicode.com/posts'

#modification d'un post existant, code 200
data='{"userId": 1,"id": 10,"title": "sunt aut ","body": "quia et"}'
curl -k  -v -H "Content-Type: application/json" -d $data -X PUT 'https://jsonplaceholder.typicode.com/posts/10'

