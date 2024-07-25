package com.chill.mallang.domain.area.repository;

import com.chill.mallang.domain.area.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
//    Area findByAreaId(Long areaId);
}
