package app.eshop.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    private final List<String> allowedMimeTypes = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @Override
    public void initialize(ValidImage constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        try {
            String mimeType = file.getContentType();
            return mimeType != null && allowedMimeTypes.contains(mimeType);
        } catch (Exception e) {
            return false;
        }
    }
}

