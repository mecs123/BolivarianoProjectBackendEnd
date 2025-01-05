    package com.bolivariano.teacher.client;

    import com.bolivariano.teacher.constans.ApiConstants;
    import com.bolivariano.teacher.constans.URLs;
    import com.bolivariano.teacher.exception.BussinesRuleException;
    import com.fasterxml.jackson.databind.JsonNode;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Component;
    import org.springframework.web.reactive.function.client.WebClient;
    import org.springframework.web.reactive.function.client.WebClientResponseException;

    @Component
    public class TeacherClient {

        @Autowired
        private WebClient.Builder webClientBuilder;

        /**
         * Obtiene el nombre del Subject por ID.
         *
         * @param id Identificador del Subject.
         * @return Nombre del Subject.
         */
        public String getSubjectName(long id) throws BussinesRuleException {
            String endpoint = "lb://subject-service/subject" + "/" + id;
            return fetchData(endpoint, URLs.NAME_SUBJECT, String.format(ApiConstants.ERROR_MSG_SUBJECT_NOT_FOUND, id));
        }

        /**
         * Obtiene el nombre del Course por ID.
         *
         * @param id Identificador del Course.
         * @return Nombre del Course.
         */
        public String getCourseName(long id) throws BussinesRuleException {
            String endpoint = "lb://courses-service/courses" + "/" + id;
            return fetchData(
                    endpoint,URLs.NAME_COURSE, String.format(ApiConstants.ERROR_MSG_COURSE_NOT_FOUND, id));
        }

        /**
         * Método genérico para realizar una solicitud GET y extraer un campo específico de la respuesta.
         *
         * @param url      URL del servicio externo.
         * @param field    Campo esperado en la respuesta.
         * @param errorMsg Mensaje de error en caso de que no se encuentre el campo.
         * @return Valor del campo solicitado.
         */
        private String fetchData(
                String url,
                String field,
                String errorMsg
        ) throws BussinesRuleException {
            try {
                WebClient client = webClientBuilder.baseUrl("").build();
                JsonNode response = client.method(HttpMethod.GET)
                        .uri(url)
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .block();
                if (response == null || response.get(field) == null) {
                    throw new EntityNotFoundException(errorMsg);
                }

                return response.get(field).asText();

            } catch (WebClientResponseException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    return ApiConstants.EMPTY_STRING;
                } else {
                    throw new BussinesRuleException(
                            ApiConstants.WEBCLIENT_ERROR_CODE,
                            ApiConstants.WEBCLIENT_ERROR_MESSAGE,
                            HttpStatus.BAD_REQUEST
                    );
                }
            }
        }
    }
