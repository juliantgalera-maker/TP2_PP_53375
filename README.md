# TP2_PP_53375
# TP2 - Sistema de Gestión de Eventos Universitarios

Proyecto correspondiente al **Trabajo Práctico N° 2**, desarrollado en Java orientado a objetos con arquitectura en capas y manejo de paquetes.

## 📐 Contenido del Proyecto

- **Ejercicio 1:** Modelado de entidades (`EventoUniversitario`, `Actividad`, `Estudiante`, `Sala`, `Inscripcion`), manejo de excepciones personalizadas (`CupoExcedidoException`) y persistencia mediante serialización en archivos `.dat`.
- **Ejercicio 2 y 3:** Interfaz `Certificable` e implementación de métodos genéricos (`<T extends Actividad>`) para filtrado de actividades por tipo.
- **Ejercicio 4:** Implementación de clases anidadas (`TicketDeAcceso`) y programación concurrente con la clase `EnvioTicketsThread`.

## 📁 Estructura de Paquetes

```text
src/
├── certificacion/
│   └── Certificable.java
├── excepciones/
│   └── CupoExcedidoException.java
├── hilos/
│   └── EnvioTicketsThread.java
├── modelo/
│   ├── actividades/
│   │   ├── Actividad.java
│   │   ├── Charla.java
│   │   ├── Curso.java
│   │   └── Taller.java
│   ├── Estudiante.java
│   ├── EventoUniversitario.java
│   ├── Inscripcion.java
│   └── Sala.java
├── Utilidades.java
└── App.java