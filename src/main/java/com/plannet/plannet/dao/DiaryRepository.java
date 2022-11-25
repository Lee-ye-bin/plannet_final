package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserIdAndDiaryDate(String userId, LocalDate date);
}
