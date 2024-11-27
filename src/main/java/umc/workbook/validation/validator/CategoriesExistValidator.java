package umc.workbook.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.workbook.apiPayload.code.status.ErrorStatus;
import umc.workbook.repository.FoodCategoryRepository;
import umc.workbook.validation.annotation.ExistCategories;

import java.util.List;

@Component
@RequiredArgsConstructor
// ConstraintValidator 인터페이스에 대한 구체화 클래스
// ExistCategories 어노테이션에 대한 로직을 담을 것이며 검증 대상이 List<Long>임
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        // 검증 대상인 List<Long> 의 값을 가진 카테고리가
        // 모두 데이터베이스에 있는 지를 판단하고 없을 경우 false를 반환
        boolean isValid = values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }

}
