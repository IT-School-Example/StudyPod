package com.itschool.study_pod.global.address.repository;

import com.itschool.study_pod.global.address.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SidoRepository extends JpaRepository<Sido, String> {
}
