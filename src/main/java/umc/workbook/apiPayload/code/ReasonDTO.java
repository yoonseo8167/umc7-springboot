package umc.workbook.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDTO {

    private HttpStatus httpStatus;      // HTTP 상태 코드

    private final boolean isSuccess;    // 성공인지 아닌지 알려주는 필드
    private final String code;          // 세부적인 응답 상황
    private final String message;       // 추가적으로 문자로 상황 알려주는 필드

    public boolean getIsSuccess() {return isSuccess;}
}
