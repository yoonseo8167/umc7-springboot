package umc.workbook.apiPayload.exception.handler;

import lombok.Getter;
import umc.workbook.apiPayload.code.BaseErrorCode;

@Getter
public class FoodCategoryHandler extends RuntimeException {
    private final BaseErrorCode errorCode;

    public FoodCategoryHandler(BaseErrorCode errorCode) {

        super(errorCode.toString());
        this.errorCode = errorCode;
    }
}
