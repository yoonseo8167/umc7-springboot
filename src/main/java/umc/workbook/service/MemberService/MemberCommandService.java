package umc.workbook.service.MemberService;

import umc.workbook.domain.Member;
import umc.workbook.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
