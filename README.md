# Swetha-food-ordering-system

## Installation Guide

### Prepare the Application 
This app is a java springboot + mysql application using java language.

Use maven tool to package and produce an executable jar.

```aidl
mvn clean install
```

You can skip this step, because I already have produced this jar in target directory.

### Prepare the App Docker Image
Dockerfile for build app image at project root directory.

Run this command at project root directory.

```aidl
docker build -t swetha-food-ordering-system:1.0.0 .
```

### Prepare App Helm Chart

Chart at project root directory.

First add helm repo binami and install mysql dependency.

Run this command at chart/swetha-food-ordering-system directory.
```aidl
helm repo add binami https://charts.bitnami.com/bitnami

helm dependency build
```

Second install this chart

```aidl
helm install swetha-food-ordering-system . --create-namespace -n looped
```

Then, waiting for app and mysql pod running. 
```aidl
kubectl get pods -w
```

### Visit App Website

After app pod and mysql pod are running healthly,

Find out swetha-food-ordering-system pod name 
```aidl
kubectl get pods -n looped
```

Then, run
```aidl
kubectl port-forward ${AppPodName} 8080:8080 -n looped
```
real exmaple 
```aidl
kubectl port-forward swetha-food-ordering-system-584f857f5f-gxcl2 8080:8080 -n looped
```

Now open any internet Browser, hit localost:8080

The END