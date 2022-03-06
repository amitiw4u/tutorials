package com.baeldung.cloud.openfeign.retreivemessage.config;

import java.io.IOException;
import java.io.InputStream;

import com.baeldung.cloud.openfeign.exception.BadRequestException;
import com.baeldung.cloud.openfeign.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	private ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		String message = null;
		try (InputStream bodyIs = response.body().asInputStream()) {
			ObjectMapper mapper = new ObjectMapper();
			message = mapper.readValue(bodyIs, ExceptionMessage.class).getMessage();
		} catch (IOException e) {
			return new Exception(e.getMessage());
		}
		switch (response.status()) {
		case 400:
			return new BadRequestException(message != null ? message : "Bad Request");
		case 404:
			return new NotFoundException(message != null ? message : "Not found");
		default:
			return errorDecoder.decode(methodKey, response);
		}
	}
}
