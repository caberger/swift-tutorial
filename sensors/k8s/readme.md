# Mqtt Server on localhost

## Install kubernetes

```bash
brew install podman
brew install kubectl
brew install minikube
```

## create a cluster
```bash
podman machine init --cpus 2
podman machine set --rootful
podman machine start
minikube --driver=podman start
minikube addmin enable dashboard
minikube addons enable metrics-server
minikube dashboard
```

To stop the cluster run:
```bash
minikube stop
podman machine stop
```

## Deployment

After starting your [minikube](https://minikube.sigs.k8s.io/docs/) you can install your mqtt-server by runnig:
```bash
kubectl apply -f mosquitto.yaml
kubectl get pods
kubectl port-forward mosquitto-XXX  1883:1883
```

where mosquitto-XXX is the name of the pod from the
- ```kubectl get pods```
command.

## Exploring the values sent

Your an view the results with [MQTT-Explorer](https://mqtt-explorer.com/)
