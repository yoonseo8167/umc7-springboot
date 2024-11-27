package umc.workbook.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook.apiPayload.code.status.ErrorStatus;
import umc.workbook.apiPayload.exception.handler.FoodCategoryHandler;
import umc.workbook.converter.MemberConverter;
import umc.workbook.converter.MemberPreferConverter;
import umc.workbook.domain.FoodCategory;
import umc.workbook.domain.Member;
import umc.workbook.domain.mapping.MemberPrefer;
import umc.workbook.repository.FoodCategoryRepository;
import umc.workbook.repository.MemberRepository;
import umc.workbook.web.dto.MemberRequestDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
