package jaeik.growfarm.repository.farm;

import jaeik.growfarm.entity.crop.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 작물 Repository
 * 작물 관련 데이터베이스 작업을 수행하는 Repository
 * 수정일 : 2025-05-03
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByUsersId(Long userId);

}
