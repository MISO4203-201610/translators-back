# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-translationservice)
  - [Entidad Customer](#entidad-customer)
  - [Entidad Translator](#entidad-translator)
  - [Entidad Language](#entidad-language)
  - [Entidad CorrectionRequest](#entidad-correctionrequest)
  - [Entidad TranslationRequest](#entidad-translationrequest)
  - [Entidad Education](#entidad-education)
  - [Entidad Status](#entidad-status)

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /TranslationService.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación TranslationService
### Entidad Customer
#### Estructura de objeto Customer
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    picture: '' /*Tipo String*/,
    birthDate: '' /*Tipo Date*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/customers|Lista los registros de Customer (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Customer y el total de registros en la base de datos en el header X-Total-Count
**GET**|/customers/*:customersid*|Obtener los atributos de una instancia de Customer (READ)|**@PathParam customersid**: Identificador del registro||Atributos de la instancia de Customer
**POST**|/customers|Crear una nueva instancia de la entidad Customer (CREATE)||Atributos de la instancia de Customer a crear|Instancia de Customer creada, incluyendo su nuevo ID
**PUT**|/customers/*:customersid*|Actualiza una instancia de la entidad Customer (UPDATE)|**@PathParam customersid**: Identificador del registro|Objeto JSON de Customer|Instancia de Customer actualizada
**DELETE**|/customers/*:customersid*|Borra instancia de Customer en el servidor (DELETE)|**@PathParam customersid**: Identificador del registro||
**GET**|customers/*:customersid*/translationRequests|Listar registros de translationRequests (TranslationRequest) asociados a Customer|**@PathParam customersid**: Identificador de instancia de Customer||Colección de objetos JSON de translationRequests(TranslationRequest)
**GET**|customers/*:customersid*/translationRequests/*:translationRequestsid*|Obtener un registro de translationRequests (TranslationRequest) asociado a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam translationRequestsid**: Identificador de instancia de TranslationRequest||Lista de registros de translationRequests(TranslationRequest)
**POST**|customers/*:customersid*/translationRequests/*:translationRequestsid*|Asocia una instancia de TranslationRequest a una de Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam translationRequestsid**: Identificador de instancia de TranslationRequest|Registro de translationRequests(TranslationRequest) para asociar a Customer|Instancia de translationRequests(TranslationRequest) asociada a instancia de Customer
**PUT**|customers/*:customersid*/translationRequests|Actualización de instancias de translationRequests (TranslationRequest) asociadas a Customer|**@PathParam customersid**: Identificador de instancia de Customer|Colección de instancias de translationRequests(TranslationRequest) a actualizar|Colección de instancias de translationRequests(TranslationRequest) actualizados
**DELETE**|customers/*:customersid*/translationRequests/*:translationRequestsid*|Remueve asociación de instancias de translationRequests (TranslationRequest) a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam translationRequestsid**: Identificador de instancia de TranslationRequest|Colección de atributo `id` de translationRequests(TranslationRequest) a eliminar|
**GET**|customers/*:customersid*/correctionRequests|Listar registros de correctionRequests (CorrectionRequest) asociados a Customer|**@PathParam customersid**: Identificador de instancia de Customer||Colección de objetos JSON de correctionRequests(CorrectionRequest)
**GET**|customers/*:customersid*/correctionRequests/*:correctionRequestsid*|Obtener un registro de correctionRequests (CorrectionRequest) asociado a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam correctionRequestsid**: Identificador de instancia de CorrectionRequest||Lista de registros de correctionRequests(CorrectionRequest)
**POST**|customers/*:customersid*/correctionRequests/*:correctionRequestsid*|Asocia una instancia de CorrectionRequest a una de Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam correctionRequestsid**: Identificador de instancia de CorrectionRequest|Registro de correctionRequests(CorrectionRequest) para asociar a Customer|Instancia de correctionRequests(CorrectionRequest) asociada a instancia de Customer
**PUT**|customers/*:customersid*/correctionRequests|Actualización de instancias de correctionRequests (CorrectionRequest) asociadas a Customer|**@PathParam customersid**: Identificador de instancia de Customer|Colección de instancias de correctionRequests(CorrectionRequest) a actualizar|Colección de instancias de correctionRequests(CorrectionRequest) actualizados
**DELETE**|customers/*:customersid*/correctionRequests/*:correctionRequestsid*|Remueve asociación de instancias de correctionRequests (CorrectionRequest) a Customer|**@PathParam customersid**: Identificador de instancia de Customer<br><br>**@PathParam correctionRequestsid**: Identificador de instancia de CorrectionRequest|Colección de atributo `id` de correctionRequests(CorrectionRequest) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Translator
#### Estructura de objeto Translator
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    picture: '' /*Tipo String*/,
    birthDate: '' /*Tipo Date*/,
    education: [] /*Colección de registros de Education*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/translators|Lista los registros de Translator (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Translator y el total de registros en la base de datos en el header X-Total-Count
**GET**|/translators/*:translatorsid*|Obtener los atributos de una instancia de Translator (READ)|**@PathParam translatorsid**: Identificador del registro||Atributos de la instancia de Translator
**POST**|/translators|Crear una nueva instancia de la entidad Translator (CREATE)||Atributos de la instancia de Translator a crear|Instancia de Translator creada, incluyendo su nuevo ID
**PUT**|/translators/*:translatorsid*|Actualiza una instancia de la entidad Translator (UPDATE)|**@PathParam translatorsid**: Identificador del registro|Objeto JSON de Translator|Instancia de Translator actualizada
**DELETE**|/translators/*:translatorsid*|Borra instancia de Translator en el servidor (DELETE)|**@PathParam translatorsid**: Identificador del registro||
**GET**|translators/*:translatorsid*/languages|Listar registros de languages (Language) asociados a Translator|**@PathParam translatorsid**: Identificador de instancia de Translator||Colección de objetos JSON de languages(Language)
**GET**|translators/*:translatorsid*/languages/*:languagesid*|Obtener un registro de languages (Language) asociado a Translator|**@PathParam translatorsid**: Identificador de instancia de Translator<br><br>**@PathParam languagesid**: Identificador de instancia de Language||Lista de registros de languages(Language)
**POST**|translators/*:translatorsid*/languages/*:languagesid*|Asocia una instancia de Language a una de Translator|**@PathParam translatorsid**: Identificador de instancia de Translator<br><br>**@PathParam languagesid**: Identificador de instancia de Language|Registro de languages(Language) para asociar a Translator|Instancia de languages(Language) asociada a instancia de Translator
**PUT**|translators/*:translatorsid*/languages|Actualización de instancias de languages (Language) asociadas a Translator|**@PathParam translatorsid**: Identificador de instancia de Translator|Colección de instancias de languages(Language) a actualizar|Colección de instancias de languages(Language) actualizados
**DELETE**|translators/*:translatorsid*/languages/*:languagesid*|Remueve asociación de instancias de languages (Language) a Translator|**@PathParam translatorsid**: Identificador de instancia de Translator<br><br>**@PathParam languagesid**: Identificador de instancia de Language|Colección de atributo `id` de languages(Language) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Language
#### Estructura de objeto Language
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/languages|Lista los registros de Language (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Language y el total de registros en la base de datos en el header X-Total-Count
**GET**|/languages/*:languagesid*|Obtener los atributos de una instancia de Language (READ)|**@PathParam languagesid**: Identificador del registro||Atributos de la instancia de Language
**POST**|/languages|Crear una nueva instancia de la entidad Language (CREATE)||Atributos de la instancia de Language a crear|Instancia de Language creada, incluyendo su nuevo ID
**PUT**|/languages/*:languagesid*|Actualiza una instancia de la entidad Language (UPDATE)|**@PathParam languagesid**: Identificador del registro|Objeto JSON de Language|Instancia de Language actualizada
**DELETE**|/languages/*:languagesid*|Borra instancia de Language en el servidor (DELETE)|**@PathParam languagesid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad CorrectionRequest
#### Estructura de objeto CorrectionRequest
```javascript
{
    customer: '' /*Objeto que representa instancia de Customer*/,
    language: '' /*Objeto que representa instancia de Language*/,
    creationDate: '' /*Tipo Date*/,
    dueDate: '' /*Tipo Date*/,
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    status: '' /*Objeto que representa instancia de Status*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/correctionRequests|Lista los registros de CorrectionRequest (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de CorrectionRequest y el total de registros en la base de datos en el header X-Total-Count
**GET**|/correctionRequests/*:correctionRequestsid*|Obtener los atributos de una instancia de CorrectionRequest (READ)|**@PathParam correctionRequestsid**: Identificador del registro||Atributos de la instancia de CorrectionRequest
**POST**|/correctionRequests|Crear una nueva instancia de la entidad CorrectionRequest (CREATE)||Atributos de la instancia de CorrectionRequest a crear|Instancia de CorrectionRequest creada, incluyendo su nuevo ID
**PUT**|/correctionRequests/*:correctionRequestsid*|Actualiza una instancia de la entidad CorrectionRequest (UPDATE)|**@PathParam correctionRequestsid**: Identificador del registro|Objeto JSON de CorrectionRequest|Instancia de CorrectionRequest actualizada
**DELETE**|/correctionRequests/*:correctionRequestsid*|Borra instancia de CorrectionRequest en el servidor (DELETE)|**@PathParam correctionRequestsid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad TranslationRequest
#### Estructura de objeto TranslationRequest
```javascript
{
    name: '' /*Tipo String*/,
    status: '' /*Objeto que representa instancia de Status*/,
    customer: '' /*Objeto que representa instancia de Customer*/,
    id: '' /*Tipo Long*/,
    dueDate: '' /*Tipo Date*/,
    originalLanguage: '' /*Objeto que representa instancia de Language*/,
    creationDate: '' /*Tipo Date*/,
    targetLanguage: '' /*Objeto que representa instancia de Language*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/translationRequests|Lista los registros de TranslationRequest (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de TranslationRequest y el total de registros en la base de datos en el header X-Total-Count
**GET**|/translationRequests/*:translationRequestsid*|Obtener los atributos de una instancia de TranslationRequest (READ)|**@PathParam translationRequestsid**: Identificador del registro||Atributos de la instancia de TranslationRequest
**POST**|/translationRequests|Crear una nueva instancia de la entidad TranslationRequest (CREATE)||Atributos de la instancia de TranslationRequest a crear|Instancia de TranslationRequest creada, incluyendo su nuevo ID
**PUT**|/translationRequests/*:translationRequestsid*|Actualiza una instancia de la entidad TranslationRequest (UPDATE)|**@PathParam translationRequestsid**: Identificador del registro|Objeto JSON de TranslationRequest|Instancia de TranslationRequest actualizada
**DELETE**|/translationRequests/*:translationRequestsid*|Borra instancia de TranslationRequest en el servidor (DELETE)|**@PathParam translationRequestsid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad Education
#### Estructura de objeto Education
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
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/educations|Lista los registros de Education (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Education y el total de registros en la base de datos en el header X-Total-Count
**GET**|/educations/*:educationsid*|Obtener los atributos de una instancia de Education (READ)|**@PathParam educationsid**: Identificador del registro||Atributos de la instancia de Education
**POST**|/educations|Crear una nueva instancia de la entidad Education (CREATE)||Atributos de la instancia de Education a crear|Instancia de Education creada, incluyendo su nuevo ID
**PUT**|/educations/*:educationsid*|Actualiza una instancia de la entidad Education (UPDATE)|**@PathParam educationsid**: Identificador del registro|Objeto JSON de Education|Instancia de Education actualizada
**DELETE**|/educations/*:educationsid*|Borra instancia de Education en el servidor (DELETE)|**@PathParam educationsid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad Status
#### Estructura de objeto Status
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/statuss|Lista los registros de Status (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Status y el total de registros en la base de datos en el header X-Total-Count
**GET**|/statuss/*:statussid*|Obtener los atributos de una instancia de Status (READ)|**@PathParam statussid**: Identificador del registro||Atributos de la instancia de Status
**POST**|/statuss|Crear una nueva instancia de la entidad Status (CREATE)||Atributos de la instancia de Status a crear|Instancia de Status creada, incluyendo su nuevo ID
**PUT**|/statuss/*:statussid*|Actualiza una instancia de la entidad Status (UPDATE)|**@PathParam statussid**: Identificador del registro|Objeto JSON de Status|Instancia de Status actualizada
**DELETE**|/statuss/*:statussid*|Borra instancia de Status en el servidor (DELETE)|**@PathParam statussid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

