# Mqtt Server on localhost

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
