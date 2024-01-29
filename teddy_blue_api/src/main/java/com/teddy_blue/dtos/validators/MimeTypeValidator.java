package com.teddy_blue.dtos.validators;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MimeTypeValidator
	implements ConstraintValidator<MimeType, MultipartFile> {

    private String[] mimeTypes;

    @Override
    public void initialize(MimeType annotation) {
	mimeTypes = annotation.value();
    }

    @Override
    public boolean isValid(MultipartFile file,
	    ConstraintValidatorContext context) {
	if (file == null) {
	    return true;
	}
	String contentType = file.getContentType();
	return Arrays.stream(mimeTypes)
		.anyMatch((type) -> type.equals(MediaType.ALL_VALUE)
			|| type.equals(contentType));
    }
}
