package com.itschool.study_pod.global.base.crud;

import com.itschool.study_pod.global.base.dto.Header;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface <Req, Res> {

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Long id, Header<Req> request);

    Header<Void> delete(Long id);

    Header<List<Res>> getPaginatedList(Pageable pageable);
}
