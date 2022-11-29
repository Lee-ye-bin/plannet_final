package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Diary;
import com.plannet.plannet.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserIdAndDiaryDate(Member userID, LocalDate date);
//    void deleteByUserId(Member id);
}
