MONEY MENTOR - IHC - 2025-2
BACKEND
INFORMACIÓN DEL PROYECTO

MoneyMentor es una plataforma web integral orientada a la administración financiera de jóvenes emprendedores y jóvenes que laboran para una organización. Desde el enfoque backend, el proyecto se centra en construir la lógica principal del sistema, permitiendo procesar, almacenar y gestionar información financiera relacionada con usuarios, operaciones, balances, ahorros, recursos educativos, roles e impuestos.

Este proyecto es desarrollado por la startup SmartSpend, una empresa dedicada a crear soluciones digitales que capacitan a los jóvenes para gestionar sus finanzas de manera efectiva y autónoma.

AUTORES

• Stefano Lucarelly Sanchez Heredia
• Angie Sthefania Rosa Apaza Macotela
• Mauricio Suwells Antonio Ascue
• Omar Alessandro Collahua Huaranga
• Orlando Gabriel Soto Ccopa

SEGMENTOS OBJETIVOS

• Jóvenes Emprendedores con su Propio Negocio: Incluye a quienes trabajan de manera independiente o tienen un negocio propio. Desde el backend, este segmento requiere funcionalidades que permitan registrar ingresos variables, controlar gastos, gestionar ahorros, calcular balances y asociar impuestos a sus operaciones financieras.

• Jóvenes que Laboran para una Organización: Dirigido a quienes cuentan con ingresos más estables. Desde el backend, este segmento necesita servicios que permitan registrar operaciones financieras, consultar balances mensuales, controlar metas de ahorro y acceder a recursos financieros que ayuden a mejorar su planificación económica.

PRINCIPALES CARACTERÍSTICAS DEL BACKEND

MoneyMentor cuenta con una arquitectura backend que permite gestionar la información principal del sistema mediante una API REST:

Gestión de Usuarios: Permite registrar, listar, consultar y eliminar usuarios dentro del sistema.

Gestión de Roles: Permite asignar roles a los usuarios para controlar sus permisos y accesos.

Gestión de Operaciones: Permite registrar, actualizar, eliminar y consultar operaciones financieras como ingresos o egresos.

Gestión de Balance: Permite registrar y consultar balances financieros, incluyendo filtros por mes y sumas totales.

Gestión de Ahorros: Permite administrar metas de ahorro, consultar ahorros por rango, periodo o ID.

Gestión de Impuestos: Permite registrar, listar, editar y eliminar impuestos asociados a operaciones.

Gestión de Recursos: Permite administrar recursos educativos o financieros, filtrarlos por autor o fecha.

Seguridad del Sistema: Permite implementar inicio y cierre de sesión para proteger el acceso a la plataforma.

Consultas Avanzadas: Permite generar reportes y métricas financieras básicas para que el usuario comprenda mejor su situación económica.

DESCRIPCIÓN DEL BACKEND

El backend de MoneyMentor corresponde a la capa lógica y funcional del sistema. Su función principal es recibir las solicitudes del frontend, procesar la información financiera del usuario, aplicar reglas de negocio y conectar la plataforma con la base de datos.

Esta capa está desarrollada como una API REST utilizando Spring Boot y PostgreSQL. A través de sus endpoints, el sistema permite gestionar módulos como Usuario, Roles, Operación, Balance, Ahorros, Recurso, Usuario-Recurso, Impuesto e Impuesto-Operación.

El backend permite realizar operaciones CRUD, es decir, crear, listar, actualizar y eliminar registros. Además, incorpora consultas avanzadas como búsqueda de balances por mes, ahorros por periodo, suma de operaciones por usuario, búsqueda de recursos por autor o fecha y cálculo de promedios de impuestos.

En conjunto, el backend garantiza que MoneyMentor funcione de manera ordenada, segura y escalable, permitiendo que los datos financieros sean almacenados, procesados y consultados correctamente.
