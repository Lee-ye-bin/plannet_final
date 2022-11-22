package com.plannet.plannet.dao;

import com.plannet.plannet.entity.Diary;
import com.plannet.plannet.entity.DiaryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository  extends JpaRepository<Diary, DiaryPK> {
}