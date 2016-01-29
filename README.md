#Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-translationservice)
  - [Entidad CorrectionRequest](#entidad-correctionrequest)
  - [Entidad Customer](#entidad-customer)
  - [Entidad Education](#entidad-education)
  - [Entidad Language](#entidad-language)
  - [Entidad Status](#entidad-status)
  - [Entidad TranslationRequest](#entidad-translationrequest)
  - [Entidad Translator](#entidad-translator)

#API Rest
##Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /TranslationService.api/webresources/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

###CRUD Básico
Para los servicios de CRUD Básico, Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
{
    totalRecords: 0, //cantidad de registros en la base de datos
    records: [] //collección con los datos solicitados. cada objeto tiene la estructura de la entidad.
}
```

##API de la aplicación TranslationService
###Entidad CorrectionRequest
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad CorrectionRequest, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto CorrectionRequest
```javascript
{
    language: '' /*Objeto que representa instancia de Language*/,
    id: '' /*Tipo Long*/,
    status: '' /*Objeto que representa instancia de Status*/,
    customer: '' /*Objeto que representa instancia de Customer*/,
    creationDate: '' /*Tipo Date*/,
    dueDate: '' /*Tipo Date*/,
    name: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/correctionRequests|Obtener todos los objetos JSON de CorrectionRequest (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON CorrectionRequest y el total de registros en la base de datos en el header X-Total-Count
**GET**|/correctionRequests/:id|Obtener los atributos de una instancia de CorrectionRequest en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de CorrectionRequest
**POST**|/correctionRequests|Crear una nueva instancia de la entidad CorrectionRequest (CREATE)||Objeto JSON de CorrectionRequest a crear|Objeto JSON de CorrectionRequest creado
**PUT**|/correctionRequests/:id|Actualiza una instancia de la entidad CorrectionRequest (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de CorrectionRequest|Objeto JSON de CorrectionRequest actualizado
**DELETE**|/correctionRequests/:id|Borra instancia de CorrectionRequest en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad Customer
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Customer, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Customer
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/customers|Obtener todos los objetos JSON de Customer (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Customer y el total de registros en la base de datos en el header X-Total-Count
**GET**|/customers/:id|Obtener los atributos de una instancia de Customer en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Customer
**POST**|/customers|Crear una nueva instancia de la entidad Customer (CREATE)||Objeto JSON de Customer a crear|Objeto JSON de Customer creado
**PUT**|/customers/:id|Actualiza una instancia de la entidad Customer (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Customer|Objeto JSON de Customer actualizado
**DELETE**|/customers/:id|Borra instancia de Customer en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

####Maestros Detalle de Customer
#####Servicios
A diferencia del API para CRUD Básico, el API de Maestro/Detalle no provee un servicio para listar los registros de la entidad maestra. Los servicios generados para el componente Maestro/Detalle de Customer son los siguientes:


######Relaciones Shared

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|customers/:id/translationRequests|Obtener instancias de translationRequests(TranslationRequest) asociados con Customer|**@PathParam id**: `id` de instancia de Customer||Colección de `id` de translationRequests(TranslationRequest) asociados con Customer
**PUT**|customers/:id/translationRequests|Actualización de referencias a translationRequests(TranslationRequest) desde Customer|**@PathParam id**: `id` de instancia de Customer|Colección de `id` de translationRequests(TranslationRequest) a asociar|Colección de objetos JSON de translationRequests(TranslationRequest) asociados
**GET**|customers/:id/correctionRequests|Obtener instancias de correctionRequests(CorrectionRequest) asociados con Customer|**@PathParam id**: `id` de instancia de Customer||Colección de `id` de correctionRequests(CorrectionRequest) asociados con Customer
**PUT**|customers/:id/correctionRequests|Actualización de referencias a correctionRequests(CorrectionRequest) desde Customer|**@PathParam id**: `id` de instancia de Customer|Colección de `id` de correctionRequests(CorrectionRequest) a asociar|Colección de objetos JSON de correctionRequests(CorrectionRequest) asociados
[Volver arriba](#tabla-de-contenidos)

###Entidad Education
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Education, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Education
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    startDate: '' /*Tipo Date*/,
    endDate: '' /*Tipo Date*/,
    institution: '' /*Tipo String*/,
    title: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/educations|Obtener todos los objetos JSON de Education (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Education y el total de registros en la base de datos en el header X-Total-Count
**GET**|/educations/:id|Obtener los atributos de una instancia de Education en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Education
**POST**|/educations|Crear una nueva instancia de la entidad Education (CREATE)||Objeto JSON de Education a crear|Objeto JSON de Education creado
**PUT**|/educations/:id|Actualiza una instancia de la entidad Education (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Education|Objeto JSON de Education actualizado
**DELETE**|/educations/:id|Borra instancia de Education en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad Language
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Language, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Language
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/languages|Obtener todos los objetos JSON de Language (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Language y el total de registros en la base de datos en el header X-Total-Count
**GET**|/languages/:id|Obtener los atributos de una instancia de Language en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Language
**POST**|/languages|Crear una nueva instancia de la entidad Language (CREATE)||Objeto JSON de Language a crear|Objeto JSON de Language creado
**PUT**|/languages/:id|Actualiza una instancia de la entidad Language (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Language|Objeto JSON de Language actualizado
**DELETE**|/languages/:id|Borra instancia de Language en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad Status
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Status, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Status
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/statuss|Obtener todos los objetos JSON de Status (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Status y el total de registros en la base de datos en el header X-Total-Count
**GET**|/statuss/:id|Obtener los atributos de una instancia de Status en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Status
**POST**|/statuss|Crear una nueva instancia de la entidad Status (CREATE)||Objeto JSON de Status a crear|Objeto JSON de Status creado
**PUT**|/statuss/:id|Actualiza una instancia de la entidad Status (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Status|Objeto JSON de Status actualizado
**DELETE**|/statuss/:id|Borra instancia de Status en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad TranslationRequest
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad TranslationRequest, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto TranslationRequest
```javascript
{
    customer: '' /*Objeto que representa instancia de Customer*/,
    status: '' /*Objeto que representa instancia de Status*/,
    id: '' /*Tipo Long*/,
    originalLanguage: '' /*Objeto que representa instancia de Language*/,
    dueDate: '' /*Tipo Date*/,
    name: '' /*Tipo String*/,
    creationDate: '' /*Tipo Date*/,
    targetLanguage: '' /*Objeto que representa instancia de Language*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/translationRequests|Obtener todos los objetos JSON de TranslationRequest (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON TranslationRequest y el total de registros en la base de datos en el header X-Total-Count
**GET**|/translationRequests/:id|Obtener los atributos de una instancia de TranslationRequest en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de TranslationRequest
**POST**|/translationRequests|Crear una nueva instancia de la entidad TranslationRequest (CREATE)||Objeto JSON de TranslationRequest a crear|Objeto JSON de TranslationRequest creado
**PUT**|/translationRequests/:id|Actualiza una instancia de la entidad TranslationRequest (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de TranslationRequest|Objeto JSON de TranslationRequest actualizado
**DELETE**|/translationRequests/:id|Borra instancia de TranslationRequest en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

###Entidad Translator
####CRUD Básico
En la siguiente tabla se detalla los servicios REST generados para la entidad Translator, la estructura del objeto que intercambian y sus respectivas funciones.

#####Estructura de objeto Translator
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#####Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/translators|Obtener todos los objetos JSON de Translator (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de objetos JSON Translator y el total de registros en la base de datos en el header X-Total-Count
**GET**|/translators/:id|Obtener los atributos de una instancia de Translator en formato JSON(READ)|**@PathParam id**: Identificador del registro||Objeto JSON con detalle de la instancia de Translator
**POST**|/translators|Crear una nueva instancia de la entidad Translator (CREATE)||Objeto JSON de Translator a crear|Objeto JSON de Translator creado
**PUT**|/translators/:id|Actualiza una instancia de la entidad Translator (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de Translator|Objeto JSON de Translator actualizado
**DELETE**|/translators/:id|Borra instancia de Translator en el servidor (DELETE)|<strong>@PathParam id</strong>: Identificador del registro||

####Maestros Detalle de Translator
#####Servicios
A diferencia del API para CRUD Básico, el API de Maestro/Detalle no provee un servicio para listar los registros de la entidad maestra. Los servicios generados para el componente Maestro/Detalle de Translator son los siguientes:

######Relaciones Composite

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|translators/:id/education|Obtener Objetos JSON de education(Education) dependientes de Translator|**@PathParam id**: `id` de instancia de Translator||Colección de objetos JSON de education(Education)
**POST**|translators/:id/education|Creación de instancias de education(Education) dependientes de Translator|**@PathParam id**: `id` de instancia de Translator|Colección de objetos JSON de education(Education) a crear|Colección de objetos JSON de education(Education) creados con sus respectivos ID
**PUT**|translators/:id/education|Actualización de instancias de education(Education) dependientes de Translator|**@PathParam id**: `id` de instancia de Translator|Colección de objetos JSON de education(Education) a actualizar|Colección de objetos JSON de education(Education) actualizados
**DELETE**|translators/:id/education|Eliminación de instancias de education(Education) dependientes de Translator|**@PathParam id**: `id` de instancia de Translator|Colección de atributo `id` de education(Education) a eliminar|

######Relaciones Shared

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|translators/:id/languages|Obtener instancias de languages(Language) asociados con Translator|**@PathParam id**: `id` de instancia de Translator||Colección de `id` de languages(Language) asociados con Translator
**PUT**|translators/:id/languages|Actualización de referencias a languages(Language) desde Translator|**@PathParam id**: `id` de instancia de Translator|Colección de `id` de languages(Language) a asociar|Colección de objetos JSON de languages(Language) asociados
[Volver arriba](#tabla-de-contenidos)

