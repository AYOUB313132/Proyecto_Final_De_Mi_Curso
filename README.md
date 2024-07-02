# Proyecto: Curso Teca
## Descripción
Curso Teca es una plataforma educativa diseñada para facilitar la inscripción en cursos. La página web cuenta con varias subpáginas que incluyen información sobre cursos, profesores del colegio y blogs. La plataforma ofrece acceso diferenciado para administradores, profesores y usuarios, con distintas capacidades y permisos según el rol asignado.

### Funcionalidades y Roles de Usuarios
Administrador: Tiene permisos completos para gestionar la plataforma. Puede añadir y borrar profesores, crear subadministradores y realizar cualquier acción administrativa.
Profesor: Puede añadir y modificar cursos, así como publicar y editar blogs.
Usuario: Puede inscribirse y desinscribirse de los cursos, ver la lista de profesores y acceder a la información detallada de los cursos una vez logueado.
Visitante (sin login): Puede ver la lista de cursos y blogs, pero no tiene acceso a la información de los profesores ni a los detalles de los cursos.
## Control de Acceso
JWT y Angular Guards: La seguridad y el control de acceso se gestionan utilizando JSON Web Tokens (JWT) y Angular Guards, lo que asegura que solo los usuarios autorizados puedan acceder a determinadas rutas y funcionalidades de la plataforma.
## Tecnologías Utilizadas
### Front End
HTML, CSS, Bootstrap: Para la estructura, diseño y estilo de la interfaz de usuario.
Angular: Framework principal para el desarrollo del front end.
Angular Material-UI: Para componentes de interfaz de usuario modernos y responsivos.
Manejo de comunicación asíncrona: Utilicé Promise y async/await para gestionar eficientemente las solicitudes HTTP y obtener datos del REST API.
Servicio de Autenticación: Maneja el proceso de login y registro, gestionando la autenticación de usuarios.
Servicio de Local Storage: Gestiona el almacenamiento de tokens de autenticación para mantener el estado de login.
Servicio de Upload de Imágenes: Permite la carga de imágenes al servidor, facilitando la gestión de contenido multimedia.
Pipes e Interfaces: Para manejar y estructurar los datos.
Angular Guards: Para controlar las rutas y permisos de acceso.
### Back End
Java y Spring Boot: Para la creación de servicios RESTful.
Arquitectura de Microservicios: Para una estructura modular y escalable.
Spring Gateway: Para balancear las peticiones entre los microservicios.
Eureka Server: Para el registro y descubrimiento de servicios.
Spring Security con JWT: Para la generación y validación de tokens de autenticación.
### Base de Datos:
 Para la gestión de datos de la plataforma, utilizando una imagen de MySQL personalizada en Docker para un despliegue y administración eficiente.Descripción Breve
Plataforma educativa para la inscripción en cursos con roles diferenciados y control de acceso mediante JWT y Angular Guards.
