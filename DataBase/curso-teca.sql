USE [master]
GO
/****** Object:  Database [CursoTeca]    Script Date: 19/05/2024 22:53:06 ******/
CREATE DATABASE [CursoTeca]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CursoTeca', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\CursoTeca.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CursoTeca_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\CursoTeca_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [CursoTeca] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CursoTeca].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CursoTeca] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CursoTeca] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CursoTeca] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CursoTeca] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CursoTeca] SET ARITHABORT OFF 
GO
ALTER DATABASE [CursoTeca] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CursoTeca] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CursoTeca] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CursoTeca] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CursoTeca] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CursoTeca] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CursoTeca] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CursoTeca] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CursoTeca] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CursoTeca] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CursoTeca] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CursoTeca] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CursoTeca] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CursoTeca] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CursoTeca] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CursoTeca] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CursoTeca] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CursoTeca] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CursoTeca] SET  MULTI_USER 
GO
ALTER DATABASE [CursoTeca] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CursoTeca] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CursoTeca] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CursoTeca] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CursoTeca] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CursoTeca] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [CursoTeca] SET QUERY_STORE = ON
GO
ALTER DATABASE [CursoTeca] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [CursoTeca]
GO
/****** Object:  Table [dbo].[blogs]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[blogs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [datetime2](6) NULL,
	[imagen] [varchar](255) NULL,
	[parrafo] [varchar](max) NULL,
	[titulo] [varchar](255) NULL,
	[categoria-id] [int] NULL,
	[descripcion] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categorias]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categorias](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[titulo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cursos]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cursos](
	[curso_id] [int] IDENTITY(1,1) NOT NULL,
	[detalles_del_curso] [varchar](max) NULL,
	[fecha_final] [datetime2](6) NULL,
	[fecha_inicio] [datetime2](6) NULL,
	[imagen] [varchar](255) NULL,
	[nombre_curso] [varchar](255) NULL,
	[profesor_id] [int] NULL,
	[tipo_id] [int] NULL,
	[precio] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[curso_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[imagenes]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[imagenes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](255) NULL,
	[path] [varchar](255) NULL,
	[tipo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[inscripciones]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[inscripciones](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[curso_id] [int] NULL,
	[usuario_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[profesores]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[profesores](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[apellido] [varchar](255) NULL,
	[especialidad] [varchar](255) NULL,
	[link_linkedin] [varchar](255) NULL,
	[link_twitter] [varchar](255) NULL,
	[nombre] [varchar](255) NULL,
	[imagen] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tipos_cursos]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tipos_cursos](
	[tipo_id] [int] IDENTITY(1,1) NOT NULL,
	[tipo_nombre] [varchar](255) NULL,
	[imagen] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[tipo_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuarios]    Script Date: 19/05/2024 22:53:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuarios](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[apellido] [varchar](255) NULL,
	[ciudad] [varchar](255) NULL,
	[codigo_postal] [int] NULL,
	[contraseña] [varchar](255) NULL,
	[direccion] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[fecha_nacimiento] [datetime2](6) NULL,
	[genero] [varchar](255) NULL,
	[movil] [int] NULL,
	[nombre] [varchar](255) NULL,
	[pais] [varchar](255) NULL,
	[provincia] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[imagen] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[blogs] ON 

INSERT [dbo].[blogs] ([id], [fecha], [imagen], [parrafo], [titulo], [categoria-id], [descripcion]) VALUES (2, CAST(N'2022-03-01T01:00:00.0000000' AS DateTime2), N'blog-3.jpg', N'El diseño de interfaces está en constante evolución. Las tendencias para 2024 incluyen el uso de colores vibrantes, microinteracciones y diseños minimalistas, enfocados en mejorar la experiencia del usuario.', N'Blog 2', 1, N'El diseño de interfaces está en constante evolución.')
INSERT [dbo].[blogs] ([id], [fecha], [imagen], [parrafo], [titulo], [categoria-id], [descripcion]) VALUES (10, CAST(N'2024-05-19T02:00:00.0000000' AS DateTime2), N'blog-3.jpg', N'El diseño de interfaces está en constante evolución. Las tendencias para 2024 incluyen el uso de colores vibrantes, microinteracciones y diseños minimalistas, enfocados en mejorar la experiencia del usuario.', N'Tendencias en Diseño de Interfaces para 2024', 1, N'El diseño de interfaces está en constante evolución.')
INSERT [dbo].[blogs] ([id], [fecha], [imagen], [parrafo], [titulo], [categoria-id], [descripcion]) VALUES (11, CAST(N'2024-06-13T02:00:00.0000000' AS DateTime2), N'blog-2.jpg', N'El contenido sigue siendo el rey en el mundo del SEO. Crear contenido de calidad y relevante no solo atrae a los usuarios, sino que también mejora significativamente el posicionamiento en los motores de búsqueda.', N'La Importancia del Contenido de Calidad en el SEO', 4, N'El contenido sigue siendo el rey en el mundo del SEO')
INSERT [dbo].[blogs] ([id], [fecha], [imagen], [parrafo], [titulo], [categoria-id], [descripcion]) VALUES (12, CAST(N'2024-06-29T02:00:00.0000000' AS DateTime2), N'', N'El marketing digital evoluciona constantemente, y 2024 no es una excepción. Con nuevas tendencias y herramientas, es crucial que las empresas adapten sus estrategias para mantenerse competitivas en un entorno digital dinámico.', N'Estrategias de Marketing Digital para 2024', 2, N'El marketing digital evoluciona constantemente')
SET IDENTITY_INSERT [dbo].[blogs] OFF
GO
SET IDENTITY_INSERT [dbo].[categorias] ON 

INSERT [dbo].[categorias] ([id], [titulo]) VALUES (1, N'Web Design')
INSERT [dbo].[categorias] ([id], [titulo]) VALUES (2, N'Online Marketing')
INSERT [dbo].[categorias] ([id], [titulo]) VALUES (4, N'SEO')
SET IDENTITY_INSERT [dbo].[categorias] OFF
GO
SET IDENTITY_INSERT [dbo].[cursos] ON 

INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (2008, N'Aprender Fluter', CAST(N'2024-06-27T02:00:00.0000000' AS DateTime2), CAST(N'2024-05-10T02:00:00.0000000' AS DateTime2), N'course-4.jpg', N'Flutter', 5023, 1, 69.99)
INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (2012, N'Aprende los conceptos básicos y la configuración de redes.', CAST(N'2024-07-11T02:00:00.0000000' AS DateTime2), CAST(N'2024-06-20T02:00:00.0000000' AS DateTime2), N'course-6.jpg', N'Fundamentos de Redes', 5024, 2, 129.65)
INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (2014, N'Spring Boot permite a los desarrolladores crear aplicaciones que simplemente se ejecutan. En concreto, permite crear aplicaciones autónomas que se ejecutan por sí solas, sin depender de un servidor web externo, integrando un servidor web como Tomcat o Netty en la app durante el proceso de inicialización.', CAST(N'2024-05-30T02:00:00.0000000' AS DateTime2), CAST(N'2024-05-09T02:00:00.0000000' AS DateTime2), N'spring-boot.jpg', N'Spring Boot', 5023, 1, 89.95)
INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (3016, N'Creación de prototipos interactivos de aplicaciones móviles.', CAST(N'2024-06-28T02:00:00.0000000' AS DateTime2), CAST(N'2024-05-30T02:00:00.0000000' AS DateTime2), N'course-1.jpg', N'Prototipado de Aplicaciones Móviles', 5025, 1009, 178)
INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (3017, N'Aprenderás los fundamentos de la programación usando Python.', CAST(N'2024-07-01T02:00:00.0000000' AS DateTime2), CAST(N'2024-06-01T02:00:00.0000000' AS DateTime2), N'python-1.jpg', N'Introducción a la Programación', 5023, 1, 289.95)
INSERT [dbo].[cursos] ([curso_id], [detalles_del_curso], [fecha_final], [fecha_inicio], [imagen], [nombre_curso], [profesor_id], [tipo_id], [precio]) VALUES (3018, N'Introducción a las herramientas y técnicas de diseño gráfico.', CAST(N'2024-06-20T02:00:00.0000000' AS DateTime2), CAST(N'2024-05-30T02:00:00.0000000' AS DateTime2), N'course-2.jpg', N'Diseño Gráfico', 5022, 4, 99.99)
SET IDENTITY_INSERT [dbo].[cursos] OFF
GO
SET IDENTITY_INSERT [dbo].[imagenes] ON 

INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (1, N'premier-league.jpg', N'D:\guardarImages\premier-league.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2, N'uefa.jpg', N'D:\guardarImages\uefa.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3, N'desny.png', N'D:\guardarImages\desny.png', N'image/png')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (4, N'roomiptv.png', N'D:\guardarImages\roomiptv.png', N'image/png')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (5, N'Amazon-Logo-PNG.png', N'D:\guardarImages\Amazon-Logo-PNG.png', N'image/png')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (6, N'europa-league.webp', N'D:\guardarImages\europa-league.webp', N'image/webp')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (7, N'Amazon-Logo-PNG.png', N'D:\guardarImages\Amazon-Logo-PNG.png', N'image/png')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (8, N'uefa-champions-league.jpeg', N'D:\guardarImages\uefa-champions-league.jpeg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (9, N'team-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (10, N'team-4.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-4.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (11, N'team-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (12, N'cat-6.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcat-6.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (13, N'Spring-Boot-Tutorial.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgSpring-Boot-Tutorial.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (14, N'spring-boot.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgspring-boot.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (15, N'testimonial-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgtestimonial-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (16, N'team-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (17, N'blog-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgblog-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (18, N'blog-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgblog-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (19, N'blog-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgblog-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (20, N'team-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (21, N'testimonial-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgtestimonial-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (22, N'testimonial-4.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgtestimonial-4.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (23, N'cat-6.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcat-6.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (24, N'course-6.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcourse-6.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (25, N'cat-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcat-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (26, N'course-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcourse-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (27, N'carousel-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcarousel-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (28, N'about.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgabout.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (29, N'blog-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgblog-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (30, N'blog-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgblog-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (31, N'course-4.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgcourse-4.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (1030, N'persona1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgpersona1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (1031, N'persona3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgpersona3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2031, N'imgabout.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgimgabout.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2032, N'imgteam-4.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgimgteam-4.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2033, N'team-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2034, N'imgteam-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgimgteam-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2035, N'team-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgteam-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2036, N'itziar.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\itziar.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2037, N'admin.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgadmin.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (2038, N'informatica.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imginformatica.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3038, N'python-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\imgpython-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3039, N'python-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\python-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3040, N'python-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\python-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3041, N'python-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\python-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3042, N'python-1.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\python-1.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3043, N'Fundamentos-de-redes.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\Fundamentos-de-redes.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3044, N'red.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\red.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3045, N'imgblog-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\imgblog-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3046, N'imgpersona3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\imgpersona3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3047, N'team-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\team-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3048, N'blog-3.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\blog-3.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3049, N'blog-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\blog-2.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3050, N'marketing-mix.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\marketing-mix.jpg', N'image/jpeg')
INSERT [dbo].[imagenes] ([id], [nombre], [path], [tipo]) VALUES (3051, N'team-2.jpg', N'D:\MyWorkspace-Angular\myworkspace\projects\curso-teca\src\assets\img\team-2.jpg', N'image/jpeg')
SET IDENTITY_INSERT [dbo].[imagenes] OFF
GO
SET IDENTITY_INSERT [dbo].[inscripciones] ON 

INSERT [dbo].[inscripciones] ([id], [curso_id], [usuario_id]) VALUES (1003, 2012, 1)
INSERT [dbo].[inscripciones] ([id], [curso_id], [usuario_id]) VALUES (2005, 2008, 1)
INSERT [dbo].[inscripciones] ([id], [curso_id], [usuario_id]) VALUES (2008, 3018, 2024)
INSERT [dbo].[inscripciones] ([id], [curso_id], [usuario_id]) VALUES (3006, 3018, 3023)
SET IDENTITY_INSERT [dbo].[inscripciones] OFF
GO
SET IDENTITY_INSERT [dbo].[profesores] ON 

INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (5022, N'Torre', N'Base de datos', N'link1', N'link1', N'Marta', N'team-3.jpg')
INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (5023, N'Chueca', N'Programación', N'link1', N'link1', N'Iñigo', N'team-4.jpg')
INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (5024, N'Bilbao', N'Sistemas', N'link1', N'link1', N'Itziar', N'team-1.jpg')
INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (5029, N'Lopez', N'UDA', N'link1', N'link1', N'Daniel', N'blog-2.jpg')
INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (5030, N'Rico', N'Redes', N'link1', N'link1', N'Javier', N'team-2.jpg')
INSERT [dbo].[profesores] ([id], [apellido], [especialidad], [link_linkedin], [link_twitter], [nombre], [imagen]) VALUES (6029, N'test', N'test', N'link1', N'link1', N'test', N'blog-2.jpg')
SET IDENTITY_INSERT [dbo].[profesores] OFF
GO
SET IDENTITY_INSERT [dbo].[tipos_cursos] ON 

INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (1, N'Informatica', N'cat-3.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (2, N'Redes', N'cat-5.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (4, N'Diseño', N'cat-1.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (5, N'SEO', N'cat-4.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (7, N'Game Design', N'cat-3.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (1007, N'Marketing', N'course-6.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (1008, N'Development', N'cat-2.jpg')
INSERT [dbo].[tipos_cursos] ([tipo_id], [tipo_nombre], [imagen]) VALUES (1009, N'Apps Design', N'course-1.jpg')
SET IDENTITY_INSERT [dbo].[tipos_cursos] OFF
GO
SET IDENTITY_INSERT [dbo].[usuarios] ON 

INSERT [dbo].[usuarios] ([id], [apellido], [ciudad], [codigo_postal], [contraseña], [direccion], [email], [fecha_nacimiento], [genero], [movil], [nombre], [pais], [provincia], [role], [imagen]) VALUES (1, N'chetti', N'Bilbao', 48012, N'$2a$10$2B0oJl8bsuYo1tdkiz8ejuXJ4zFCadqKZ/6.BnCr5BCnCPJogj99S', NULL, N'hassen@gmail.com', CAST(N'1999-01-01T01:00:00.0000000' AS DateTime2), N'male', 123456789, N'hassen', NULL, NULL, N'ADMIN', N'admin.jpg')
INSERT [dbo].[usuarios] ([id], [apellido], [ciudad], [codigo_postal], [contraseña], [direccion], [email], [fecha_nacimiento], [genero], [movil], [nombre], [pais], [provincia], [role], [imagen]) VALUES (2018, N'Chueca', NULL, NULL, N'$2a$10$K.D9oRtrc0u5vtTnCk03Fe6ymHQcGzxx9ebE8BTTFmYqqEqg5N2fq', NULL, N'inigo@gmail.com', CAST(N'1971-05-16T01:00:00.0000000' AS DateTime2), N'masculino', 123456789, N'Iñigo', NULL, NULL, N'ADMIN', N'team-4.jpg')
INSERT [dbo].[usuarios] ([id], [apellido], [ciudad], [codigo_postal], [contraseña], [direccion], [email], [fecha_nacimiento], [genero], [movil], [nombre], [pais], [provincia], [role], [imagen]) VALUES (2020, N'Torre', NULL, NULL, N'$2a$10$1L6GgngVSBduMnPcyHc4yOy3F7JexZznvZAYLnCP3ePKJSi03mGyK', NULL, N'marta@gmail.com', CAST(N'1975-05-08T02:00:00.0000000' AS DateTime2), N'masculino', 123456789, N'Marta', NULL, NULL, N'USER', N'imgteam-3.jpg')
INSERT [dbo].[usuarios] ([id], [apellido], [ciudad], [codigo_postal], [contraseña], [direccion], [email], [fecha_nacimiento], [genero], [movil], [nombre], [pais], [provincia], [role], [imagen]) VALUES (2021, N'Rico', NULL, NULL, N'$2a$10$N/og.mnToQezIAtr.4IgQ.mWa7RKfNYPFil7AocK9Ea2JC0HvM6yG', NULL, N'javier@gmail.com', CAST(N'1973-05-11T01:00:00.0000000' AS DateTime2), N'masculino', 123456789, N'Javier', NULL, NULL, N'USER', N'team-2.jpg')
INSERT [dbo].[usuarios] ([id], [apellido], [ciudad], [codigo_postal], [contraseña], [direccion], [email], [fecha_nacimiento], [genero], [movil], [nombre], [pais], [provincia], [role], [imagen]) VALUES (2022, N'Bilbao', NULL, NULL, N'$2a$10$0Fn9ce8qfsPWuPJDI/i2wO...eOC8aY6WgOe8UH0lRlgfrqsjZrju', NULL, N'itziar@gmail.com', CAST(N'1979-05-02T02:00:00.0000000' AS DateTime2), N'femenino', 123456789, N'Itziar', NULL, NULL, N'USER', N'itziar.jpg')
SET IDENTITY_INSERT [dbo].[usuarios] OFF
GO
ALTER TABLE [dbo].[blogs]  WITH CHECK ADD  CONSTRAINT [FKex54l7uphip3c2c7610mecpb3] FOREIGN KEY([categoria-id])
REFERENCES [dbo].[categorias] ([id])
GO
ALTER TABLE [dbo].[blogs] CHECK CONSTRAINT [FKex54l7uphip3c2c7610mecpb3]
GO
ALTER TABLE [dbo].[cursos]  WITH CHECK ADD  CONSTRAINT [FKiclh15gqnpuhm65e8mlbj9725] FOREIGN KEY([tipo_id])
REFERENCES [dbo].[tipos_cursos] ([tipo_id])
GO
ALTER TABLE [dbo].[cursos] CHECK CONSTRAINT [FKiclh15gqnpuhm65e8mlbj9725]
GO
ALTER TABLE [dbo].[usuarios]  WITH CHECK ADD CHECK  (([role]='USER' OR [role]='PROFESOR' OR [role]='ADMIN'))
GO
USE [master]
GO
ALTER DATABASE [CursoTeca] SET  READ_WRITE 
GO
