# 🏨 Hotel Citas - Sistema de Agendamiento Reactivo

Microservicio reactivo desarrollado con **Spring WebFlux** y **MongoDB** para gestionar citas de servicios en un hotel. Desplegado en **Kubernetes** con persistencia duradera, configuracion externalizada y comunicacion multi-componente.

## 🏛 Arquitectura General

```
                    [ Cliente / Postman / curl ]
                              |
                    [ NodePort Service :30080 ]
                              |
                 [ Backend: Spring WebFlux Pod ]
                    envFrom: ConfigMap + Secret
                              |
                   [ ClusterIP Service :27017 ]
                              |
                   [ MongoDB Pod + PVC (1Gi) ]
```

## 📁 Estructura del Repositorio

```
/
├── k8s/
│   ├── 00-namespace.yaml         # Namespace: practica-s11
│   ├── 01-mongo-pvc.yaml         # PersistentVolumeClaim para MongoDB
│   ├── 02-app-configmap.yaml     # ConfigMap: DB_HOST, DB_PORT, DB_NAME, APP_PORT
│   ├── 03-db-secret.yaml         # Secret: credenciales MongoDB en Base64
│   ├── 04-mongo-stack.yaml       # Deployment + ClusterIP Service de MongoDB
│   └── 05-backend-stack.yaml     # Deployment + NodePort Service del Backend
├── src/                          # Codigo fuente Spring WebFlux
└── README.md
```

## 🚀 Endpoints de la API REST

| Metodo | Endpoint                        | Descripcion                        |
|--------|---------------------------------|------------------------------------|
| POST   | `/api/citas`                    | Crear nueva cita                   |
| GET    | `/api/citas`                    | Listar todas las citas             |
| GET    | `/api/citas/{id}`               | Obtener cita por ID                |
| GET    | `/api/citas/estado/{estado}`    | Filtrar por estado                 |
| GET    | `/api/citas/email/{email}`      | Buscar citas de un huesped         |
| GET    | `/api/citas/servicio/{tipo}`    | Filtrar por tipo de servicio       |
| PUT    | `/api/citas/{id}`               | Actualizar cita completa           |
| PATCH  | `/api/citas/{id}/estado`        | Cambiar estado de la cita          |
| DELETE | `/api/citas/{id}`               | Eliminar cita                      |
| GET    | `/actuator/health`              | Health check (Kubernetes Probes)   |

## 📋 Tipos de Servicio Disponibles

- `SPA` - Tratamientos y relajacion
- `RESTAURANTE` - Reservas de mesa
- `SALA_CONFERENCIAS` - Eventos y reuniones
- `CHECK_IN` - Registro de entrada
- `CHECK_OUT` - Registro de salida
- `GIMNASIO` - Sesiones de entrenamiento
- `PISCINA` - Reserva de area de piscina

## 📋 Estados de una Cita

| Estado      | Descripcion                        |
|-------------|------------------------------------|
| PENDIENTE   | Recien creada, sin confirmar       |
| CONFIRMADA  | Aprobada por el hotel              |
| CANCELADA   | Cancelada por huesped o hotel      |
| COMPLETADA  | Servicio realizado exitosamente    |

## 🔧 Ejemplo de Cuerpo JSON para Crear una Cita

```json
{
  "nombreHuesped": "Ana Garcia",
  "email": "ana.garcia@email.com",
  "telefono": "+51 999 888 777",
  "tipoServicio": "SPA",
  "numeroHabitacion": "305",
  "fechaHora": "2026-11-20T10:00:00",
  "duracionMinutos": 60,
  "notas": "Preferencia por masaje de relajacion"
}
```

## ⚙️ Variables de Entorno (inyectadas por Kubernetes)

| Variable                      | Fuente     | Descripcion                  |
|-------------------------------|------------|------------------------------|
| `DB_HOST`                     | ConfigMap  | Host del servicio MongoDB    |
| `DB_PORT`                     | ConfigMap  | Puerto MongoDB (27017)       |
| `DB_NAME`                     | ConfigMap  | Nombre de la base de datos   |
| `APP_PORT`                    | ConfigMap  | Puerto del backend           |
| `MONGO_INITDB_ROOT_USERNAME`  | Secret     | Usuario root de MongoDB      |
| `MONGO_INITDB_ROOT_PASSWORD`  | Secret     | Contrasena root de MongoDB   |

## 🛠️ Despliegue en Kubernetes

```bash
# 1. Aplicar todos los manifiestos en orden
kubectl apply -f k8s/00-namespace.yaml
kubectl apply -f k8s/01-mongo-pvc.yaml
kubectl apply -f k8s/02-app-configmap.yaml
kubectl apply -f k8s/03-db-secret.yaml
kubectl apply -f k8s/04-mongo-stack.yaml
kubectl apply -f k8s/05-backend-stack.yaml

# 2. Verificar estado del cluster
kubectl get all,pvc,configmaps,secrets -n practica-s11

# 3. Ver IP del nodo para acceder via NodePort
kubectl get nodes -o wide
```
