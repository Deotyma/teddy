package com.teddy_blue.dtos.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator
implements ConstraintValidator<FileSize, MultipartFile> {

private long max;

@Override
public void initialize(FileSize annotation) {
long value = annotation.value();
if (value <= 0) {
    throw new IllegalArgumentException(
	    String.format("value must be positive: %s", value));
}
max = value;
}

@Override
public boolean isValid(MultipartFile file,
    ConstraintValidatorContext context) {
if (file == null) {
    return true;
}
return file.getSize() <= max;
}
}
