package com.plannet.plannet.service;

import com.plannet.plannet.dao.DiaryRepository;
import com.plannet.plannet.dao.MemberRepository;
import com.plannet.plannet.dao.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;
    private final PlanRepository planRepository;
}
