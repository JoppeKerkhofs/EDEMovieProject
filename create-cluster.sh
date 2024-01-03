#!/bin/bash

red=`tput setaf 1`
green=`tput setaf 2`
magenta=`tput setaf 5`
reset=`tput sgr0`

echo "${magenta}---------------- Start Minikube cluster ----------------${reset}"
# Start Minikube cluster
echo "${green}minikube start --no-vtx-check --image-repository=auto --kubernetes-version=v1.25.4${reset}"
minikube start --no-vtx-check --image-repository=auto --kubernetes-version=v1.25.4

echo "${magenta}---------------- Apply the Kubernetes manifest to deploy your services ----------------${reset}"
# Apply the Kubernetes manifest to deploy your services
echo "${green}kubectl apply -f kubernetes/appManifests/${reset}"
kubectl apply -f kubernetes/appManifests/

echo "${magenta}---------------- Setup kube state metrics ----------------${reset}"
# Setup kube state metrics
echo "${green}kubectl apply -f kubectl apply -f kubernetes/kubeStateMetricsConfigs/${reset}"
kubectl apply -f kubernetes/kubeStateMetricsConfigs/

echo "${magenta}---------------- Add Prometheus monitoring ----------------${reset}"
# create the monitoring namespace
echo "${green}kubectl create namespace monitoring${reset}"
kubectl create namespace monitoring
# create the needed cluster role from clusterRole.yaml
echo "${green}kubectl apply -f kubernetes/prometheusConfigs/clusterRole.yaml${reset}"
kubectl apply -f kubernetes/prometheusConfigs/clusterRole.yaml
# create the config map from config-map.yaml
echo "${green}kubectl apply -f kubernetes/prometheusConfigs/config-map.yaml${reset}"
kubectl apply -f kubernetes/prometheusConfigs/config-map.yaml
# create the Prometheus deployment from prometheus-deployment.yaml
echo "${green}kubectl apply -f kubernetes/prometheusConfigs/prometheus-deployment.yaml -n monitoring${reset}"
kubectl apply -f kubernetes/prometheusConfigs/prometheus-deployment.yaml -n monitoring
# create the Prometheus service from prometheus-service.yaml
echo "${green}kubectl apply -f kubernetes/prometheusConfigs/prometheus-service.yaml -n monitoring${reset}"
kubectl apply -f kubernetes/prometheusConfigs/prometheus-service.yaml -n monitoring

echo "${magenta}---------------- Add Grafana monitoring ----------------${reset}"
# apply the grafana datasource config
echo "${green}kubectl apply -f kubernetes/grafanaConfigs/grafana-datasource-config.yaml${reset}"
kubectl apply -f kubernetes/grafanaConfigs/grafana-datasource-config.yaml
# apply the grafana deployment
echo "${green}kubectl apply -f kubernetes/grafanaConfigs/grafanaDeployment.yaml${reset}"
kubectl apply -f kubernetes/grafanaConfigs/grafanaDeployment.yaml
# apply the grafana service
echo "${green}kubectl apply -f kubernetes/grafanaConfigs/grafanaService.yaml${reset}"
kubectl apply -f kubernetes/grafanaConfigs/grafanaService.yaml

echo "${magenta}---------------- Wait for the services to be ready before accessing them ----------------${reset}"
# Wait for the services to be ready before accessing them
echo "${green}kubectl wait --for=condition=available deployment/actor-service --timeout=120s${reset}"
kubectl wait --for=condition=available deployment/actor-service --timeout=120s
echo "${green}kubectl wait --for=condition=available deployment/movie-service --timeout=120s${reset}"
kubectl wait --for=condition=available deployment/movie-service --timeout=120s
echo "${green}kubectl wait --for=condition=available deployment/cinema-service --timeout=120s${reset}"
kubectl wait --for=condition=available deployment/cinema-service --timeout=120s
echo "${green}kubectl wait --for=condition=available deployment/rating-service --timeout=120s${reset}"
kubectl wait --for=condition=available deployment/rating-service --timeout=120s
echo "${green}kubectl wait --for=condition=available deployment/api-gateway --timeout=120s${reset}"
kubectl wait --for=condition=available deployment/api-gateway --timeout=120s

# echo "${magenta}---------------- Open Minikube Dashboard ----------------${reset}"
# # Open Minikube Dashboard
# echo "${green}minikube dashboard${reset}"
# minikube dashboard

# echo "${magenta}---------------- Open the Prometheus service in the browser ----------------${reset}"
# # Open the Prometheus service in the browser
# echo "${green}minikube service prometheus-service -n monitoring${reset}"
# minikube service prometheus-service -n monitoring

# echo "${magenta}---------------- Open the Grafana service in the browser ----------------${reset}"
# # Open the Grafana service in the browser
# echo "${green}minikube service grafana -n monitoring${reset}"
# minikube service grafana -n monitoring

echo "${magenta}---------------- Open the API Gateway service in the browser ----------------${reset}"
# Open the API Gateway service in the browser
echo "${green}kubectl port-forward service/api-gateway 8084:8084${reset}"
kubectl port-forward service/api-gateway 8084:8084